package service.realty;

import common.CommonUtils;
import converter.realty.RealtyConverter;
import converter.realty.RealtyMapper;
import converter.realty.RealtyMapperImpl;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import db.entity.parser.NoticeEntity;
import db.entity.parser.view.VNoticeEntity;
import db.entity.realty.*;
import db.entity.realty.admin.AddrHouseEntity;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import dto.common.SimpleResultDto;
import dto.geocode.CommonCoordsDto;
import dto.realty.*;
import dto.report.NoticeWrapper;
import dto.report.RealtyConfigurationDto;
import dto.report.RequestReportDto;
import enums.EDirectionName;
import enums.realty.EStreetType;
import enums.realty.EVillageType;
import enums.report.*;
import exceptions.NoCoordsInCityException;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.hash.TLongHashSet;
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

            if (districtEntity.getParentDistrictId() != null && districtEntity.getParentDistrictId() == 1) {
                continue;
            }

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

    private String getHouseNumByIdx(String[] addrParts, int idx) {
        String houseNum = addrParts[idx].trim();

        int lastSpace = houseNum.lastIndexOf(" ");

        if (lastSpace != -1) {
            houseNum = houseNum.substring(lastSpace + 1);
        }

        return houseNum.toLowerCase();
    }

    private void fillStreet(EStreetType streetType, String addrPart, StreetDto streetDto) {
        String street = streetType.getClearRegexp().matcher(addrPart)
                                  .replaceAll("")
                                  .replaceAll("[ ]{2,}", " ")
                                  .replace('ё', 'е')
                                  .trim();

        streetDto.setName(street);
        streetDto.setStreetType(streetType);
    }

    public HouseDto getHouseAddr(String address, long noticeId) {
        address = address.trim();

        // String street = null;
        String houseNum = null;
        int strtIdx = -1;

        String[] addrParts = address.split(",");

        boolean haveStreet = false;
        int lastVillageIdx = -1;
        // boolean haveVillage = false;
        VillageDto village = new VillageDto();
        StreetDto streetDto = new StreetDto();

        for (int i = 0; i < addrParts.length; i++) {

            EVillageType villageType = EVillageType.getVillageType(addrParts[i]);

            if (villageType != EVillageType.UNKNOWN) {
                String villageStr = villageType.getClearRegexp().matcher(addrParts[i])
                                               .replaceAll("")
                                               .replaceAll("[ ]{2,}", " ")
                                               .replace('ё', 'е')
                                               .trim();

                if (i == addrParts.length - 1 && villageType == EVillageType.THE_VILLAGE) {
                    continue;
                }

                village.setName(villageStr);
                village.setVillageType(villageType);
                lastVillageIdx = i;
                continue;
            }

            EStreetType streetType = EStreetType.getStreetType(addrParts[i]);
            if (streetType != EStreetType.UNKNOWN) {
                fillStreet(streetType, addrParts[i], streetDto);
                // haveStreet = true;

                strtIdx = i;
                continue;
            }

        }

        if (!streetDto.exists() && lastVillageIdx == addrParts.length - 2) {
            houseNum = getHouseNumByIdx(addrParts, lastVillageIdx + 1);
        }

        if (streetDto.exists() && strtIdx < addrParts.length - 1) {
            houseNum = getHouseNumByIdx(addrParts, strtIdx + 1);
        }

        if (lastVillageIdx != addrParts.length - 1 &&
                (!streetDto.exists() && lastVillageIdx < addrParts.length - 2
                || CommonUtils.isNullOrEmpty(houseNum) && (!(streetDto.exists() && strtIdx == addrParts.length - 1)))) {
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

            houseNum = address.substring(lastSpace + 1).trim().toLowerCase();
            // ул. Малкова ш. Космонавтов
            String street;
            if (preLastComma > 0) {
                street = address.substring(preLastComma + 1, lastComma);
            } else {
                street = address.substring(0, lastComma);
            }

            EStreetType streetType = EStreetType.getStreetType(street);

            if (streetType == EStreetType.UNKNOWN) {
                streetType = EStreetType.STREET;
            }

            fillStreet(streetType, street, streetDto);
        }

        return new HouseDto(village, streetDto, houseNum);
    }

    private void determineAddress(Connection connection, VNoticeEntity noticeEntity, IGeocoder forcedGeoRepo) {
        // ул. Космонавта Леонова, д. 68Б
        String address = noticeEntity.getAddress();

        if (CommonUtils.isNullOrEmpty(address)) {
            return;
        }

        HouseDto houseDto = getHouseAddr(address, noticeEntity.getId());
        StreetDto street = houseDto.getStreet();
        String houseNum = houseDto.getHouseNum();
        // EStreetType streetType = houseDto.getStreet().getStreetType();

        try {
            if (!street.exists() && !houseDto.getVillage().exists()) {
                if (!address.contains(noticeEntity.getCityName())) {
                    saveEmptyAddress(connection, noticeEntity);
                    return;
                }

                throw new IllegalArgumentException("Тип улицы не найден.");
            }

            CityEntity cityEntity = realtyDao.getCityById(connection, noticeEntity.getCityId());

            // Получение деревни или улицы.
            // long fiasAddr = findAddrFromFias(connection, cityEntity.getFiasId(), houseDto.getVillage(), houseDto.getStreet());

            // Проверить, есть ли адрес в БД.
            HouseEntity houseEntity = realtyDao.getHouseByAddress(connection, houseDto.printStreetAddr(), houseNum, cityEntity);

            if (houseEntity == null) {

                // long fiasStreet = realtyDao.findFiasStreet(connection, cityEntity.getFiasId(), street, streetType);

                /*if (fiasAddr < 0) {
                    if (!address.contains(cityEntity.getName())) {
                        // Это нормально. Нужно пропустить.
                        saveEmptyAddress(connection, noticeEntity);
                        return;
                    }

                    throw new RuntimeException("Улица не найдена");
                }*/

                if (!houseDto.getStreet().exists() && CommonUtils.isNullOrEmpty(houseDto.getHouseNum())
                    || houseDto.getStreet().getStreetType() == EStreetType.LINE && CommonUtils.isNullOrEmpty(houseDto.getHouseNum())) {
                    // Это не анализируемый объект.
                    saveEmptyAddress(connection, noticeEntity);
                    return;
                }

                // Сейчас нужно получить координаты по адресу.
                List<CommonCoordsDto> rawCoordinatesDto = (forcedGeoRepo == null) ? geocodeHttpRepository
                        .getHouseByAddress(noticeEntity.getCityName(), houseDto, address)
                        : forcedGeoRepo.getHouseByAddress(noticeEntity.getCityName(), houseDto, address);

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
                    /*if (!address.contains(noticeEntity.getCityName()) || streetType == EStreetType.CITY) {
                        saveEmptyAddress(connection, noticeEntity);
                        return;
                    }*/

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
                houseEntity.setStreet(houseDto.printStreetAddr());
                houseEntity.setDistrictId(houseDistrict.getId());
                houseEntity.setCoords(houseCoords);
                // houseEntity.setFiasStreet(fiasAddr);

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

    private long findAddrFromFias(Connection connection, long cityFiasId, VillageDto village, StreetDto street) {

        long villageId = -1;

        if (village.exists()) {
            villageId = realtyDao.findFiasVillage(connection, cityFiasId, village.getName(), village.getVillageType());
        }

        if (villageId < 0 && !street.exists()) {
            return -1;
        }

        if (villageId > 0 && !street.exists()) {
            return villageId;
        }

        if (villageId < 0) {
            return realtyDao.findFiasStreet(connection, cityFiasId, street.getName(), street.getStreetType());
        }

        return realtyDao.findFiasStreet(connection, cityFiasId, street.getName(), street.getStreetType(), villageId);

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
            result.add(new CityDto(cityEntity));
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

    public void remapAllAdresses(RequestHelper requestHelper) {
        Connection connection = requestHelper.getConnection();
        List<AddrHouseEntity> houseEntityList = realtyDao.noticesWithHouse(connection);
        List<AddrHouseEntity> newAddrs = new ArrayList<>();

        LOGGER.info("Start to prepare addresses");

        TLongHashSet houseSet = new TLongHashSet();

        for (AddrHouseEntity houseEntity : houseEntityList) {

            if (houseSet.contains(houseEntity.getId())) {
                continue;
            }

            HouseDto houseDto = getHouseAddr(houseEntity.getNoticeAddr(), houseEntity.getId());

            houseEntity.setStreet(houseDto.printStreetAddr());
            houseEntity.setHouseNum(houseDto.getHouseNum());

            newAddrs.add(houseEntity);

            houseSet.add(houseEntity.getId());
        }

        LOGGER.info("Addresses is ready to save");

        realtyDao.updateHouseAddress(connection, newAddrs);

        try {
            connection.commit();
        } catch (SQLException exception) {
            LOGGER.error("", exception);
            throw new RuntimeException("", exception);
        }
    }

}
