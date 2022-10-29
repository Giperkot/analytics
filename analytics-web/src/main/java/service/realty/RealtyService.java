package service.realty;

import common.CommonUtils;
import converter.realty.RealtyConverter;
import converter.realty.RealtyMapper;
import converter.realty.RealtyMapperImpl;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import db.entity.parser.view.VNoticeEntity;
import db.entity.realty.*;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import dto.common.SimpleResultDto;
import dto.geocode.CommonCoordsDto;
import dto.realty.*;
import dto.report.NoticeWrapper;
import dto.report.RealtyConfigurationDto;
import dto.report.RequestReportDto;
import enums.EDirectionName;
import enums.realty.EStreetType;
import enums.report.*;
import exceptions.NoCoordsInCityException;
import gnu.trove.map.hash.TLongObjectHashMap;
import helper.report.ReportClassifier;
import interfaces.report.ITitled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.geocoder.GeocodeHttpRepository;
import repository.geocoder.IGeocoder;
import repository.geocoder.yandex.YandexHttpRepository;
import service.AbstractParser;
import service.realty.catalog.IRealtyCatalogService;
import service.realty.catalog.RealtyCatalogService;
import service.report.ReportService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

        EStreetType streetType = EStreetType.getStreetType(street);

        street = street.replaceAll("ул\\.|улица|ш\\.|б-р|пр-т|пр\\.", "").trim();

        return new HouseDto(street, streetType, houseNum);
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
        EStreetType streetType = houseDto.getStreetType();

        CityEntity cityEntity = realtyDao.getCityById(connection, noticeEntity.getCityId());

        try {
            // Проверить, есть ли адрес в БД.
            HouseEntity houseEntity = realtyDao.getHouseByAddress(connection, street, streetType, houseNum, cityEntity);

            if (houseEntity == null) {

                long fiasStreet = realtyDao.findFiasStreet(connection, cityEntity.getFiasId(), street, streetType);

                // Сейчас нужно получить координаты по адресу.
                List<CommonCoordsDto> rawCoordinatesDto = (forcedGeoRepo == null) ? geocodeHttpRepository
                        .getHouseByAddress(noticeEntity.getCityName(), street, streetType, houseNum)
                        : forcedGeoRepo.getHouseByAddress(noticeEntity.getCityName(), street, streetType, houseNum);

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
                houseEntity.setFiasStreet(fiasStreet);

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

            IRealtyCatalogService catalogService = RealtyCatalogService.getInstance();
            catalogService.init();

            for (HouseEntity houseEntity : houseEntityList) {
                currentHouseEntity = houseEntity;

                /*currentHouseEntity.getHouseNum();
                currentHouseEntity.getStreet();*/
                HouseAddInfoEntity houseAddInfoEntity = catalogService.fillHouseYearInfo(currentHouseEntity, connection);

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
