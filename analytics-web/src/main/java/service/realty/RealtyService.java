package service.realty;

import common.CommonUtils;
import converter.realty.RealtyConverter;
import converter.realty.RealtyMapper;
import converter.realty.RealtyMapperImpl;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import db.entity.parser.view.VNoticeEntity;
import db.entity.realty.CityEntity;
import db.entity.realty.DistrictEntity;
import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import db.entity.realty.NoticeCategoryEntity;
import db.entity.realty.ShortDistrictEntity;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import dto.common.SimpleResultDto;
import dto.geocode.CommonCoordsDto;
import dto.realty.*;
import dto.realty.giszkh.StreetResponseDtoDto;
import dto.report.NoticeWrapper;
import dto.report.RealtyConfigurationDto;
import dto.report.RequestReportDto;
import dto.twogis.HouseResultDto;
import dto.twogis.ItemDto;
import dto.twogis.ItemDto__3;
import dto.twogis.KeyValue;
import dto.twogis.SearchHouseDto;
import dto.twogis.auth.AuthResult;
import enums.EDirectionName;
import enums.report.EBalconParam;
import enums.report.EFloor;
import enums.report.EHouseBuildYear;
import enums.report.EHouseFloor;
import enums.report.EHouseType;
import enums.report.ERealtyConfigType;
import enums.report.ERoomsCount;
import exceptions.NoCoordsInCityException;
import gnu.trove.map.hash.TLongObjectHashMap;
import helper.report.ReportClassifier;
import interfaces.report.ITitled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.geocoder.GeocodeHttpRepository;
import repository.geocoder.IGeocoder;
import repository.geocoder.yandex.YandexHttpRepository;
import repository.twogis.TwoGisRepository;
import service.AbstractParser;
import service.report.ReportService;
import utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RealtyService extends AbstractParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyService.class);
    private static final RealtyService instance = new RealtyService();

    public static RealtyService getInstance() {
        return instance;
    }

    private final RealtyDao realtyDao = RealtyDao.getInstance();

    private final ParserDao parserDao = ParserDao.getInstance();

    private final GeocodeHttpRepository geocodeHttpRepository = GeocodeHttpRepository.getInstance();

    private final RealtyConverter realtyConverter = RealtyConverter.getInstance();

    private final ReportService reportService = ReportService.getInstance();

    private final RealtyMapper realtyMapper = new RealtyMapperImpl();

    // private final GisZkhRepository gisZkhRepository = GisZkhRepository.getInstance();

    private final TwoGisRepository twoGisRepository = TwoGisRepository.getInstance();


    private RealtyService() {
    }

    private List<DistrictWrapper> getDistrictTree(Connection connection, long cityId) {
        // Отсортировано по уровню.
        List<DistrictEntity> districtEntityList = realtyDao.getDistrictListByCity(connection, cityId);

        TLongObjectHashMap<List<DistrictWrapper>> districtMap = new TLongObjectHashMap<>();

        List<DistrictWrapper> result = new ArrayList<>();

        for (int i = 0; i < districtEntityList.size(); i++) {
            DistrictEntity districtEntity = districtEntityList.get(i);
            DistrictWrapper wrapper = DistrictWrapper.create(districtEntity);

            if (districtEntity.getCoords() == null || districtEntity.getCoords().length < 1) {
                continue;
            }

            if (districtEntity.getParentDistrictId() == null) {
                result.add(wrapper);
            } else {
                districtMap.get(districtEntity.getParentDistrictId()).add(wrapper);
            }

            districtMap.put(districtEntity.getId(), wrapper.getDistrictWrapperList());
        }

        return result;
    }

    private boolean isPointInRect(CoordPoint point, BoundsDto boundRect) {

        CoordPoint upper = boundRect.getUpper();
        CoordPoint lower = boundRect.getLower();

        if (upper.isNorthWested(point) && lower.isSouthernEastern(point)) {
            return true;
        }

        return false;
    }

    private boolean isPointInPolygonArray(CoordPoint point, DistrictWrapper districtWrapper) {
        DistrictCoords[] districtCoordsArr = districtWrapper.getDistrictEntity().getCoords();

        for (int j = 0; j < districtCoordsArr.length; j++) {
            DistrictCoords districtCoords = districtCoordsArr[j];

            BoundsDto boundRect = districtCoords.getBound();

            if (!isPointInRect(point, boundRect)) {
                continue;
            }

            CoordPoint[] coordPoints = districtCoords.getPoligonBounds();

            if (coordPoints.length < 3) {
                continue;
            }

            CoordPoint first = coordPoints[0];

            int countMatches = 0;

            for (int i = 1; i < coordPoints.length - 1; i++) {
                CoordPoint second = coordPoints[i];
                CoordPoint third = coordPoints[i + 1];

                if (point.inTriangle(first, second, third)) {
                    countMatches++;
                }
            }

            if (countMatches % 2 == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Обнаружение наиболее точного района из предоставленных.
     *
     * @param houseCoords - Координаты дома
     * @param districtWrapperList - Дерево микрорайонов в пределах города (деревни)
     * @return Точка должна входить только в один полигон.
     * Если их будет несколько, будет возвращён первый.
     */
    private DistrictEntity getExactlyDistrict(HouseCoords houseCoords, List<DistrictWrapper> districtWrapperList) {

        CoordPoint point = houseCoords.getCenter();

        for (int i = 0; i < districtWrapperList.size(); i++) {
            DistrictWrapper districtWrapper = districtWrapperList.get(i);

            if (isPointInPolygonArray(point, districtWrapper)) {
                if (!districtWrapper.getDistrictWrapperList().isEmpty()) {
                    DistrictEntity innerDistrict = getExactlyDistrict(houseCoords, districtWrapper.getDistrictWrapperList());

                    if (innerDistrict != null) {
                        return innerDistrict;
                    } else {
                        return districtWrapper.getDistrictEntity();
                    }
                } else {
                    return districtWrapper.getDistrictEntity();
                }
            }

        }

        return null;
    }

    private void saveEmptyAddress(Connection connection, VNoticeEntity noticeEntity) throws SQLException {
        parserDao.saveHouseNubmerToNotice(connection, -1, noticeEntity.getId());
        connection.commit();
    }

    public HouseDto getHouseAddr(String address, long noticeId) {
        address = address.trim();

        String street = null;
        String houseNum = null;
        int strtIdx = -1;

        String[] addrParts = address.split(",");

        for (int i = 0; i < addrParts.length; i++) {
            if (addrParts[i].contains("ул.") || addrParts[i].contains("ул.")) {
                street = addrParts[i].trim();
                strtIdx = i;
                break;
            }
        }

        if (street != null && strtIdx < addrParts.length - 1) {
            houseNum = addrParts[strtIdx + 1].trim();

            int lastSpace = houseNum.lastIndexOf(" ");

            if (lastSpace != -1) {
                houseNum = houseNum.substring(lastSpace + 1);
            }
        }

        if (CommonUtils.isNullOrEmpty(street) || CommonUtils.isNullOrEmpty(houseNum)) {
            // Старый способ
            int lastSpace = address.lastIndexOf(" ");
            int lastComma = address.lastIndexOf(",");
            int preLastComma = address.lastIndexOf(",", lastComma - 1);

            if (preLastComma < 0) {
                preLastComma = 0;
            }

            if (lastComma < 0 || lastSpace < 0) {
                throw new RuntimeException("Адрес записан в неверном формате: " + address + ". noticeId: " + noticeId);
            }

            houseNum = address.substring(lastSpace + 1).trim();
            // ул. Малкова ш. Космонавтов

            if (preLastComma > 0) {
                street = address.substring(preLastComma + 1, lastComma);
            } else {
                street = address.substring(0, lastComma);
            }
        }

        street = street.replaceAll("ул\\.|улица|ш\\.|б-р|пр-т|пр\\.", "").trim();

        return new HouseDto(street, houseNum);
    }

    private void determineAddress(Connection connection, VNoticeEntity noticeEntity, IGeocoder forcedGeoRepo) {
        // ул. Космонавта Леонова, д. 68Б
        String address = noticeEntity.getAddress();

        if (CommonUtils.isNullOrEmpty(address)) {
            return;
        }

        HouseDto houseDto = getHouseAddr(address, noticeEntity.getId());
        String street = houseDto.getStreet();
        String houseNum = houseDto.getHouseNum();

        CityEntity cityEntity = realtyDao.getCityById(connection, noticeEntity.getCityId());

        try {
            // Проверить, есть ли адрес в БД.
            HouseEntity houseEntity = realtyDao.getHouseByAddress(connection, street, houseNum, noticeEntity.getCityId());

            if (houseEntity == null) {
                // Сейчас нужно получить координаты по адресу.
                List<CommonCoordsDto> rawCoordinatesDto = (forcedGeoRepo == null) ? geocodeHttpRepository
                        .getHouseByAddress(noticeEntity.getCityName(), street, houseNum)
                        : forcedGeoRepo.getHouseByAddress(noticeEntity.getCityName(), street, houseNum);

                if (CommonUtils.isNullOrEmpty(rawCoordinatesDto)) {
                    if (!address.contains(noticeEntity.getCityName())) {
                        LOGGER.error("Адрес не был найден... noticeId: " + noticeEntity.getId() + ". " + noticeEntity.getAddress());
                        saveEmptyAddress(connection, noticeEntity);
                        return;
                    }

                    throw new RuntimeException("Адрес не был найден... noticeId: " + noticeEntity.getId() + ". " + noticeEntity.getAddress());
                }

                List<CommonCoordsDto> coordinatesDto = new ArrayList<>();
                // Проверить вхождение в город...
                for (CommonCoordsDto commonCoordsDto : rawCoordinatesDto) {
                    if (isPointInCity(commonCoordsDto, cityEntity)) {
                        coordinatesDto.add(commonCoordsDto);
                    }
                }

                if (coordinatesDto.isEmpty()) {
                    if (!address.contains(noticeEntity.getCityName())) {
                        saveEmptyAddress(connection, noticeEntity);
                        return;
                    }

                    throw new NoCoordsInCityException("Найденный адрес не входит в " + noticeEntity.getCityName() + ". noticeId: " + noticeEntity.getId() + ". "
                                                       + noticeEntity.getCityName() + " " + street + " " + houseNum);
                }

                HouseCoords houseCoords = coordinatesDto.get(0).getHouseCoords();

                List<DistrictWrapper> districtWrapperList = getDistrictTree(connection, noticeEntity.getCityId());

                DistrictEntity houseDistrict = getExactlyDistrict(houseCoords, districtWrapperList);

                if (houseDistrict == null) {
                    if (!address.contains(noticeEntity.getCityName())) {
                        saveEmptyAddress(connection, noticeEntity);
                        return;
                    }

                    throw new RuntimeException("Не удалось определить район. noticeId: " + noticeEntity.getId() + ". "
                                                       + noticeEntity.getCityName() + " " + street + " " + houseNum);
                }

                houseEntity = new HouseEntity();
                houseEntity.setHouseNum(houseNum);
                houseEntity.setCityId(noticeEntity.getCityId());
                houseEntity.setStreet(street);
                houseEntity.setDistrictId(houseDistrict.getId());
                houseEntity.setCoords(houseCoords);

                if (houseEntity.getId() != -1) {
                    // Сохранить новый дом в БД.
                    realtyDao.saveHouse(connection, houseEntity);
                }

            }

            // Сохранить адрес нового дома в БД.
            parserDao.saveHouseNubmerToNotice(connection, houseEntity.getId(), noticeEntity.getId());

            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при сохранении в БД. ", ex);
        } catch (NoCoordsInCityException ex) {
            if (forcedGeoRepo != null) {
                throw ex;
            }

            determineAddress(connection, noticeEntity, YandexHttpRepository.getInstance());
        }
    }

    private boolean isPointInCity(CommonCoordsDto commonCoordsDto, CityEntity cityEntity) {

        CoordPoint point = commonCoordsDto.getHouseCoords().getCenter();
        DistrictCoords[] territory = cityEntity.getCoords().getTerritory();

        DistrictEntity districtEntity = new DistrictEntity();
        districtEntity.setCoords(territory);

        return isPointInPolygonArray(point, DistrictWrapper.create(districtEntity));
    }

    public FullDistrictDto getDistrictById(RequestHelper requestHelper, String id) {
        long districtId = Long.parseLong(id);
        DistrictEntity districtEntity = realtyDao.getDistrictById(requestHelper.getConnection(), districtId);

        return realtyMapper.toFullDistrictDto(districtEntity);
    }

    public void determineAddresses(Connection connection, EDirectionName directionName) {

        List<VNoticeEntity> noticeEntityList = realtyDao.getNoticesWithoutAddressByDirection(connection, directionName);

        for (VNoticeEntity noticeEntity : noticeEntityList) {
            determineAddress(connection, noticeEntity, null);
        }

    }

    public List<CityDto> getCityList(RequestHelper requestHelper) {
        List<CityEntity> cityEntityList = realtyDao.getCityEntityList(requestHelper.getConnection());

        List<CityDto> result = new ArrayList<>();

        for (CityEntity cityEntity : cityEntityList) {
            result.add(new CityDto(cityEntity.getId(), cityEntity.getName()));
        }

        return result;
    }

    public void findDistrictForHouses(RequestHelper requestHelper) {
        Connection connection = requestHelper.getConnection();

        List<HouseEntity> houseEntityList = realtyDao.getHousesWithoutDistrict(connection);

        List<DistrictWrapper> districtWrapperList = getDistrictTree(connection, 1);
        try {
            for (HouseEntity houseEntity : houseEntityList) {

                DistrictEntity houseDistrict = getExactlyDistrict(houseEntity.getCoords(), districtWrapperList);

                if (houseDistrict == null) {
                    throw new RuntimeException("Не удалось определить район. houseId: " + houseEntity.getId() + ". "
                                                       + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
                }

                realtyDao.saveHouseDistrict(connection, houseEntity.getId(), houseDistrict.getId());

                connection.commit();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<DistrictDto> getPrimaryDistricts(RequestHelper requestHelper, CityRequestDto cityRequestDto) {
        List<ShortDistrictEntity> districtEntityList =
                realtyDao.getMicroDistricts(requestHelper.getConnection(), cityRequestDto.getCityId(), 0, 0);

        return districtEntityList.stream().map((elm) -> {
            return new DistrictDto(elm.getId(), elm.getName(), elm.getParentId());
        }).collect(Collectors.toList());
    }

    public List<DistrictDto> getMicroDistricts(RequestHelper requestHelper, CityDistrictDto cityDto) {
        List<ShortDistrictEntity> districtEntityList =
                realtyDao.getMicroDistricts(requestHelper.getConnection(), cityDto.getCityId(), cityDto.getStartLevel(), 100);

        return districtEntityList.stream().map((elm) -> {
            return new DistrictDto(elm.getId(), elm.getName(), elm.getParentId());
        }).collect(Collectors.toList());
    }

    private NoticeCategoryEntity toNoticeCategory(List<ITitled> titledList, long noticeId) {
        NoticeCategoryEntity result = new NoticeCategoryEntity();
        result.setNoticeId(noticeId);

        for (ITitled titled : titledList) {
            if (titled instanceof EFloor) {
                result.setFloor((EFloor)titled);
            }
            if (titled instanceof EBalconParam) {
                result.setBalcon((EBalconParam) titled);
            }
            if (titled instanceof EHouseBuildYear) {
                result.setHouseBuildYear((EHouseBuildYear)titled);
            }
            if (titled instanceof EHouseFloor) {
                result.setHouseFloor((EHouseFloor)titled);
            }
            if (titled instanceof EHouseType) {
                result.setHouseType((EHouseType)titled);
            }
            if (titled instanceof ERoomsCount) {
                result.setRoomsCount((ERoomsCount)titled);
            }
        }

        return result;
    }

    public void createOrUpdateAllNoticeIndex(RequestHelper requestHelper) {

        Connection connection = requestHelper.getConnection();

        List<NoticeWrapper> noticeInfoList = reportService.getAllActiveNotices(connection);
        RealtyConfigurationDto realtyConfigurationDto = ERealtyConfigType.COMPLEX.buildConfiguration();

        RequestReportDto requestReportDto = new RequestReportDto();
        // Perm
        requestReportDto.setCityId(1L);

        // Создаём классифаер.
        ReportClassifier reportClassifier = reportService.createReportClassifier(connection, realtyConfigurationDto, requestReportDto);

        try {
            for (NoticeWrapper noticeWrapper : noticeInfoList) {
                List<ITitled> titledList = reportClassifier.getNoticeInfo(noticeWrapper);
                int position = reportClassifier.getNoticePosition(noticeWrapper);

                NoticeCategoryEntity noticeCategoryEntity = toNoticeCategory(titledList, noticeWrapper.getNoticeEntity().getId());
                noticeCategoryEntity.setCanonTypeNumber(position);
                noticeCategoryEntity.setSquareValue(noticeWrapper.getNoticeSquare());

                realtyDao.saveOrUpdateNoticeCategory(connection, noticeCategoryEntity);

                connection.commit();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при сохранении NoticeCategoryEntity ", ex);
        }
    }

    public List<VNoticeInfoWithAvgPriceDto> getNoticesByFilter(RequestHelper requestHelper, NoticeInfoFilter filter) {

        Connection connection = requestHelper.getConnection();

        List<VNoticeInfoWithAvgPriceEntity> noticeInfoWithAvgPriceEntityList =
                realtyDao.getNoticesInfoWithAvgPrice(connection, filter.getDistrictIdArr(), filter.getRoomsCount(),
                                                        filter.getFloor(), filter.getHouseFloor(), filter.getHouseType(),
                                                        filter.getHouseBuildYear(), filter.getBalcon());

        List<VNoticeInfoWithAvgPriceDto> result = new ArrayList<>();

        for (VNoticeInfoWithAvgPriceEntity notice : noticeInfoWithAvgPriceEntityList) {
            // notice.setAverageSum(Math.round(notice.getAverageSum(), 2));
            result.add(realtyMapper.toNoticeInfoWithAvgPriceDto(notice));
        }

        return result;
    }

    public SimpleResultDto saveDistrict(RequestHelper requestHelper, FullDistrictDto fullDistrictDto) {

        SimpleResultDto result = new SimpleResultDto();

        if (fullDistrictDto.getCoords() == null || fullDistrictDto.getCoords().length < 1) {
            throw new RuntimeException("Нужно ввести границы района");
        }

        if (CommonUtils.isNullOrEmpty(fullDistrictDto.getName())) {
            throw new RuntimeException("Нужно ввести название района");
        }

        Connection connection = requestHelper.getConnection();

        DistrictEntity districtEntity = realtyMapper.toDistrictEntity(fullDistrictDto);
        DistrictEntity parentDistrict = null;

        if (districtEntity.getParentDistrictId() != null) {
            parentDistrict = realtyDao.getDistrictById(connection, districtEntity.getParentDistrictId());

            districtEntity.setLevel(parentDistrict.getLevel() + 1);

            if (CommonUtils.isNotEmpty(parentDistrict.getParents())) {
                String parents = parentDistrict.getParents().substring(0, parentDistrict.getParents().length() - 1) +
                        ", " + parentDistrict.getId() + "}";
            } else {
                districtEntity.setParents("{" + parentDistrict.getId() + "}");
            }
        }

        try {
            if (districtEntity.getId() < 0) {
                realtyDao.createNewDistrict(connection, districtEntity);

                connection.commit();
                result.setSuccess(true);
                return result;
            }

            realtyDao.updateDistrict(connection, districtEntity);

            connection.commit();
            result.setSuccess(true);
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public SimpleResultDto deleteDistrictById(RequestHelper requestHelper, long districtId) {

        Connection connection = requestHelper.getConnection();

        DistrictEntity districtEntity = realtyDao.getDistrictById(connection, districtId);

        Long childParentId = districtEntity.getParentDistrictId();

        realtyDao.deleteDistrictById(connection, districtId, childParentId);

        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return new SimpleResultDto(true);
    }

    private StreetResponseDtoDto chooseStreet(StreetResponseDtoDto[] streetResponseDtoArr, String streetName) {
        String lowerStreetname = streetName.toLowerCase();

        Arrays.sort(streetResponseDtoArr, (a, b) -> {
            return a.getFormalName().length() - b.getFormalName().length();
        });

        for (int i = 0; i < streetResponseDtoArr.length; i++) {
            StreetResponseDtoDto streetResponseDtoDto = streetResponseDtoArr[i];

            if (!"ул".equals(streetResponseDtoDto.getShortName()) &&
                !"пр-кт".equals(streetResponseDtoDto.getShortName()) &&
                !"б-р".equals(streetResponseDtoDto.getShortName())) {
                continue;
            }

            if (!streetResponseDtoDto.getFormalName().toLowerCase().contains(lowerStreetname)) {
                continue;
            }

            return streetResponseDtoDto;
        }

        return streetResponseDtoArr[0];
    }

    private EHouseType getHouseType(String wallType) {

        if (CommonUtils.isNullOrEmpty(wallType)) {
            return EHouseType.UNKNOWN;
        }

        if (wallType.contains("кирпич")) {
            return EHouseType.BRICK;
        }

        if (wallType.contains("Панельные") || wallType.contains("панел") || wallType.contains("Панели")) {
            return EHouseType.PANEL;
        }

        if (wallType.contains("онолитн")) {
            return EHouseType.MONOLIT;
        }

        if (wallType.contains("Блоки из ячеистого бетона")) {
            return EHouseType.MONOLIT;
        }

        if (wallType.contains("Гипсобетонные")) {
            return EHouseType.MONOLIT;
        }

        if (wallType.contains("Железобетонные")) {
            return EHouseType.MONOLIT;
        }

        if (wallType.contains("Пенобетон") || wallType.contains("Газобетон")) {
            return EHouseType.MONOLIT;
        }

        return null;
    }

    private HouseAddInfoEntity createHouseAddinfoEntity(long houseId, int buildingYear, Integer deterioration, String wallMateria,
                                                        int floorsCount) {
        HouseAddInfoEntity houseAddInfoEntity = new HouseAddInfoEntity();
        houseAddInfoEntity.setHouseId(houseId);
        houseAddInfoEntity.setBuildYear(buildingYear);
        houseAddInfoEntity.setDeterioration(deterioration);
        houseAddInfoEntity.setHouseType(getHouseType(wallMateria));
        houseAddInfoEntity.setFloorsCount(floorsCount);

        return houseAddInfoEntity;
    }

    private int getFluursCount(String maxFloorsCount) {
        int minusIdx = maxFloorsCount.indexOf("-");

        if (minusIdx > -1) {
            maxFloorsCount = maxFloorsCount.substring(minusIdx + 1);
        }

        return Integer.parseInt(maxFloorsCount);
    }

    private String prepareStreet(String street) {
        if (street.contains("ё")) {
            street = street.replace('ё', 'е');
        }

        return street;
    }

    private void fillHouseInfoByNotices(Connection connection, HouseAddInfoEntity houseAddInfoEntity) {
        List<NoticeWrapper> noticeWrapperList = reportService.getQllNoticeByHouse(connection, houseAddInfoEntity.getHouseId());

        EHouseType finalHouseType = null;
        int maxTypeInDb = 0;
        int maxFloorsCount = 0;
        int[] houseTypePop = new int[EHouseType.getValues().length];
        houseTypePop[houseAddInfoEntity.getHouseType().getId()] += 2;

        for (NoticeWrapper noticeWrapper : noticeWrapperList) {
            EHouseType houseType = noticeWrapper.getHouseType();
            int floorsCouknt = noticeWrapper.getFloorsCount();

            /*if (finalHouseType == null) {
                finalHouseType = houseType;
            }*/

            houseTypePop[houseType.getId()] += 1;

            /*if (finalHouseType != houseType) {
                throw new RuntimeException("Тип дома не сходится по объявлениям: " + houseType + " " + finalHouseType);
            }*/

            if (maxFloorsCount < floorsCouknt) {
                maxFloorsCount = floorsCouknt;
            }
        }

        for (int i = 0; i < houseTypePop.length; i++) {
            if (maxTypeInDb < houseTypePop[i]) {
                maxTypeInDb = houseTypePop[i];
                finalHouseType = EHouseType.getByOrdinal(i);
            }
        }

        if (finalHouseType != EHouseType.UNKNOWN) {
            houseAddInfoEntity.setHouseType(finalHouseType);
        }

        houseAddInfoEntity.setFloorsCount(maxFloorsCount);
    }

    private List<KeyValue> createParamsList(String id, String view1, String view2, AuthResult authResult) {
        List<KeyValue> keyValues = new ArrayList<>();

        keyValues.add(new KeyValue("fields", "items.locale,items.flags,search_attributes,items.adm_div,items.city_alias,items.region_id,items.segment_id,items.reviews,items.point,request_type,context_rubrics,query_context,items.links,items.name_ex,items.org,items.group,items.dates,items.external_content,items.contact_groups,items.comment,items.ads.options,items.email_for_sending.allowed,items.stat,items.stop_factors,items.description,items.geometry.centroid,items.geometry.selection,items.geometry.style,items.timezone_offset,items.context,items.level_count,items.address,items.is_paid,items.access,items.access_comment,items.for_trucks,items.is_incentive,items.paving_type,items.capacity,items.schedule,items.floors,ad,items.rubrics,items.routes,items.platforms,items.directions,items.barrier,items.reply_rate,items.purpose,items.attribute_groups,items.route_logo,items.has_goods,items.has_apartments_info,items.has_pinned_goods,items.has_realty,items.has_exchange,items.has_payments,items.has_dynamic_congestion,items.is_promoted,items.congestion,items.delivery,items.order_with_cart,search_type,items.has_discount,items.metarubrics,broadcast,items.detailed_subtype,items.temporary_unavailable_atm_services,items.poi_category,items.structure_info.material,items.structure_info.floor_type,items.structure_info.gas_type,items.structure_info.year_of_construction,items.structure_info.elevators_count,items.structure_info.is_in_emergency_state,items.structure_info.project_type"));
        keyValues.add(new KeyValue("id", id));
        // keyValues.add(new KeyValue("key", "rurbbn3446"));
        keyValues.add(new KeyValue("key", "ruhjvy3379"));
        keyValues.add(new KeyValue("locale", "ru_RU"));
        keyValues.add(new KeyValue("viewpoint1", view1));
        keyValues.add(new KeyValue("viewpoint2", view2));
        keyValues.add(new KeyValue("stat[sid]", authResult.getSessionId()));
        keyValues.add(new KeyValue("stat[user]", authResult.getUserId()));
        keyValues.add(new KeyValue("shv", authResult.getApiVers()));

        return keyValues;
    }

    private AuthResult getAuthResult(String mainPageHtmlString) {
        AuthResult result = new AuthResult();

        int cfgIdx = mainPageHtmlString.indexOf("__customcfg");

        int sessionIdIdx = mainPageHtmlString.indexOf("sessionId", cfgIdx);
        int userIdIdx = mainPageHtmlString.indexOf("userId", cfgIdx);
        int commitIsoDateIdx = mainPageHtmlString.indexOf("commitIsoDate", cfgIdx);

        if (cfgIdx < 0 || sessionIdIdx < 0 || userIdIdx < 0 || commitIsoDateIdx < 0) {
            throw new RuntimeException("Не удалось авторизоваться в 2gis");
        }

        int sessionIdValQuoteIdx = mainPageHtmlString.indexOf(":", sessionIdIdx + "sessionId".length() + 1);
        int userIdValQuoteIdx = mainPageHtmlString.indexOf(":", userIdIdx + "userId".length() + 1);
        int commitIsoDateValQuoteIdx = mainPageHtmlString.indexOf(":", commitIsoDateIdx + "commitIsoDate".length() + 1);

        String sessionId = mainPageHtmlString.substring(sessionIdValQuoteIdx + 2, sessionIdValQuoteIdx + 2 + 36);
        String userId = mainPageHtmlString.substring(userIdValQuoteIdx + 2, userIdValQuoteIdx + 2 + 36);
        String commitIsoDateStr = mainPageHtmlString.substring(commitIsoDateValQuoteIdx + 2, commitIsoDateValQuoteIdx + 2 + 19);
        LocalDateTime commitIsoDate = LocalDateTime.parse(commitIsoDateStr, DateUtils.ISO_WITHOUT_Z);

        result.setSessionId(sessionId);
        result.setUserId(userId);
        result.setApiVers(commitIsoDate.format(DateUtils.JS_WITH_H));

        return result;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> result = new HashMap<>();//super.getHeaders();
        result.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        result.put("accept-encoding", "gzip, deflate, br");
        result.put("accept-language", "ru-RU,ru;q=0.9");
        result.put("sec-ch-ua", "\"Google Chrome\";v=\"105\", \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"105\"");
        result.put("sec-ch-ua-mobile", "?0");
        result.put("sec-ch-ua-platform", "Windows");
        result.put("sec-fetch-dest", "document");
        result.put("sec-fetch-mode", "navigate");
        result.put("sec-fetch-site", "none");
        result.put("sec-fetch-user", "?1");
        result.put("upgrade-insecure-requests", "1");
        result.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
        result.put("cache-control", "max-age=0");

        return result;
    }

    private CoordPoint getCoordsByCentroid(String centroid) {
        int squareOp = centroid.indexOf("(");
        int squareCl = centroid.indexOf(")");
        int space = centroid.indexOf(" ", squareOp);

        return new CoordPoint(Double.parseDouble(centroid.substring(squareOp + 1, space)), Double.parseDouble(centroid.substring(space + 1, squareCl)));
    }

    private ItemDto getExactlyHouse(List<ItemDto> houseList, HouseEntity houseEntity) {

        CoordPoint upperCoords = houseEntity.getCoords().getBound().getUpper();
        CoordPoint lowerCoords = houseEntity.getCoords().getBound().getLower();

        double lowerX = Math.min(upperCoords.getLongitude(), lowerCoords.getLongitude());
        double upperX = Math.max(upperCoords.getLongitude(), lowerCoords.getLongitude());
        double lowerY = Math.min(upperCoords.getLatitude(), lowerCoords.getLatitude());
        double upperY = Math.max(upperCoords.getLatitude(), lowerCoords.getLatitude());;

        for (int i = 0; i < houseList.size(); i++) {
            ItemDto item = houseList.get(i);

            String centroid = item.getGeometry().getCentroid();

            CoordPoint houseCoords = getCoordsByCentroid(centroid);

            if (lowerX <= houseCoords.getLongitude() && upperX >= houseCoords.getLongitude()
                && upperY >= houseCoords.getLatitude() && lowerY <= houseCoords.getLatitude()) {
                return item;
            }
        }

        throw new RuntimeException("Координаты найденных домов не совпадают с нужными");
    }

    /**
     * Получить год постройки, износ и кол. этажей в домах.
     *
     * @param requestHelper
     * @return
     */
    public SimpleResultDto fillHouseYearInfo(RequestHelper requestHelper) {

        Connection connection = requestHelper.getConnection();

        // Получить дома без информации.
        List<HouseEntity> houseEntityList = realtyDao.getHousesWithoutBuildYear(connection, 100);

        HouseEntity currentHouseEntity = null;

        try {
            Map<String, String> headers = getHeaders();
            // Сначала авторизоваться в 2gis
            String mainPageHtmlString = twoGisRepository.getMainPage(headers);

            AuthResult authResult = getAuthResult(mainPageHtmlString);

            for (HouseEntity houseEntity : houseEntityList) {
                currentHouseEntity = houseEntity;

                /*currentHouseEntity.getHouseNum();
                currentHouseEntity.getStreet();*/
                BoundsDto bounds = currentHouseEntity.getCoords().getBound();
                CoordPoint lowerPoint = bounds.getLower();
                CoordPoint upperPoint = bounds.getUpper();
                String view1 = "55.687814580014916,58.19162883445249";//upperPoint.getLongitude() + "," + upperPoint.getLatitude();
                String view2 = "56.771963419985084,57.856551165547515";//lowerPoint.getLongitude() + "," + lowerPoint.getLatitude();

                SearchHouseDto searchHouseDto = twoGisRepository.searchAddr(currentHouseEntity.getStreet() + " " + currentHouseEntity.getHouseNum(),
                                                                            view1, view2, headers);

                if (searchHouseDto.getResult() == null) {
                    throw new RuntimeException("Отсутствует дом в БД 2гис " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
                }

                List<ItemDto__3> items = searchHouseDto.getResult().getItems();

                if (items.isEmpty()) {
                    throw new RuntimeException("Отсутствует дом в БД 2гис " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
                }

                ItemDto__3 item = items.get(0);

                String houseId = item.getId();

                List<KeyValue> paramsList = createParamsList(houseId, view1, view2, authResult);

                HouseResultDto houseResultDto = twoGisRepository.getHouseInfoById(paramsList, headers);

                if (houseResultDto.getMeta().getCode() == 403) {
                    throw new RuntimeException("Авторизация провалилась");
                }

                List<ItemDto> itemList = houseResultDto.getResult().getItems();

                ItemDto houseItem = getExactlyHouse(itemList, houseEntity);

                int floorsCount = houseItem.getFloors().getGroundCount();
                String wallMaterial = houseItem.getStructureInfo().getMaterial();
                int year = houseItem.getStructureInfo().getYearOfConstruction();

                HouseAddInfoEntity houseAddInfoEntity = createHouseAddinfoEntity(houseEntity.getId(), year, -1,
                                                                                 wallMaterial, floorsCount);

                // Тип дома и этажность возьмём с Авито. Ибо на ГисЖКХ чёт не так.
                fillHouseInfoByNotices(connection, houseAddInfoEntity);

                /*String preparedStreet = prepareStreet(houseEntity.getStreet());
                // Получить улицу и дом из ГисЖкх
                StreetResponseDtoDto[] streetResponseDtoArr = gisZkhRepository.getStreetInfo(preparedStreet);

                if (streetResponseDtoArr.length < 1) {
                    throw new RuntimeException("Отсутствует улица в БД гис жкх " + houseEntity.getStreet());
                }

                StreetResponseDtoDto streetResponseDto = chooseStreet(streetResponseDtoArr, preparedStreet);

                // Сделать запрос на информацию о доме.
                HouseResponseDtoDto[] houseResponseDtoDtos = gisZkhRepository.getHouseUUID(streetResponseDto.getAoGuid(), houseEntity.getHouseNum());

                if (houseResponseDtoDtos.length < 1) {
                    throw new RuntimeException("Отсутствует дом в БД гис жкх " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
                }

                HouseResponseDtoDto houseResponseDtoDto = houseResponseDtoDtos[0];


                HouseInfoResponseDto houseInfoResponseDto = gisZkhRepository.getFinalHouseInfo(houseResponseDtoDto.getActualHouseGuid());

                List<ItemDto> itemDtoList = houseInfoResponseDto.getItems();

                if (itemDtoList.isEmpty()) {
                    throw new RuntimeException("Отсутствует подробная информация по дому " + houseEntity.getId() + " " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
                }

                ItemDto houseItemDto = itemDtoList.get(0);

                // Теперь финальная информация.

                HouseFinalInfoDto houseFinalInfoDto = gisZkhRepository.houseDetailsByUUID(houseItemDto.getGuid());

                // int buildingYear = Integer.parseInt(houseFinalInfoDto.getBuildingYear());
                int buildingYear = houseFinalInfoDto.getOperationYear() != null ? houseFinalInfoDto.getOperationYear()
                        : Integer.parseInt(houseFinalInfoDto.getBuildingYear());
                Integer deterioration = (houseFinalInfoDto.getDeterioration() == null) ? null :
                        (int)Double.parseDouble(houseFinalInfoDto.getDeterioration());
                String wallMaterial = houseFinalInfoDto.getIntWallMaterialList();
                int floorsCount = getFluursCount(houseItemDto.getMaxFloorCount());

                HouseAddInfoEntity houseAddInfoEntity = createHouseAddinfoEntity(houseEntity.getId(), buildingYear, deterioration,
                                                                                 wallMaterial, floorsCount);

                // Тип дома и этажность возьмём с Авито. Ибо на ГисЖКХ чёт не так.
                fillHouseInfoByNotices(connection, houseAddInfoEntity);
                 */

                realtyDao.saveHouseAdditionalInfo(connection, houseAddInfoEntity);

                connection.commit();

                Thread.sleep(2000);
            }

            return null;
        } catch (Exception ex) {
            if (currentHouseEntity != null) {
                throw new RuntimeException("Ошибка в получении информации по дому id: " + currentHouseEntity.getId() + " " +
                                                   currentHouseEntity.getStreet() + " " + currentHouseEntity.getHouseNum(), ex);
            } else {
                throw new RuntimeException("Ошибка в получении информации по дому", ex);
            }

        }



    }

}
