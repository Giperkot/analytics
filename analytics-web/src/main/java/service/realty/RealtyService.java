package service.realty;

import common.CommonUtils;
import converter.realty.ImportMapper;
import converter.realty.RealtyConverter;
import converter.realty.RealtyMapper;
import converter.realty.RealtyMapperImpl;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import dao.realty.excelimport.ImportRealtyDao;
import db.entity.parser.view.VNoticeEntity;
import db.entity.realty.AdjustCoeffsEntity;
import db.entity.realty.CityEntity;
import db.entity.realty.DistrictEntity;
import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import db.entity.realty.NoticeCategoryEntity;
import db.entity.realty.ShortDistrictEntity;
import db.entity.realty.admin.AddrHouseEntity;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import dto.common.SimpleResultDto;
import dto.geocode.CommonCoordsDto;
import dto.realty.*;
import dto.realty.excelimport.ImportExcelRealtyDto;
import dto.realty.excelimport.ImportResponseDto;
import dto.realty.excelimport.standart.AdjustmentImportRealtyWrapDto;
import dto.realty.excelimport.standart.EvalutionStandartObjDto;
import dto.realty.excelimport.standart.StandartRequestWrapper;
import dto.realty.standart.SelectedStandartObjectDto;
import dto.report.NoticeWrapper;
import dto.report.RealtyConfigurationDto;
import dto.report.RequestReportDto;
import enums.EDirectionName;
import enums.realty.EStreetType;
import enums.realty.EVillageType;
import enums.report.*;
import exceptions.NoCoordsInCityException;
import exceptions.NotFoundAddressInCityException;
import exceptions.SaveEmptyAddressException;
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
import service.excelReader.ExcelReaderService;
import service.realty.catalog.IRealtyCatalogService;
import service.realty.catalog.RealtyCatalogService;
import service.report.ReportService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RealtyService extends AbstractParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyService.class);
    private static final RealtyService instance = new RealtyService();

    public static RealtyService getInstance() {
        return instance;
    }

    private final RealtyDao realtyDao = RealtyDao.getInstance();

    private final ParserDao parserDao = ParserDao.getInstance();

    private final ImportRealtyDao importRealtyDao = ImportRealtyDao.getInstance();

    private final GeocodeHttpRepository geocodeHttpRepository = GeocodeHttpRepository.getInstance();

    private final RealtyConverter realtyConverter = RealtyConverter.getInstance();

    private final ReportService reportService = ReportService.getInstance();

    private final RealtyMapper realtyMapper = new RealtyMapperImpl();

    private final ImportMapper importMapper = ExcelReaderService.getInstance().getImportMapper();

    private final Map<String, AdjustCoeffsEntity> adjustCoeffDictMap = new ConcurrentHashMap<>();

    private RealtyService() {
    }

    private String createAdjustCoeffKey(String type, String paramFrom, String paramTo) {
        return type + "_" + paramFrom + "_" + paramTo;
    }

    public void initializeAdjustCoeffDictMap(Connection connection) {
        List<AdjustCoeffsEntity>  adjustCoeffsEntityList = realtyDao.getAdjustCoeffList(connection);

        for (AdjustCoeffsEntity entity : adjustCoeffsEntityList) {
            String key = createAdjustCoeffKey(entity.getType().getName(), entity.getFromParametr(), entity.getToParameter());
            adjustCoeffDictMap.put(key, entity);
        }
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

        int addrPartsLength = addrParts.length;

        if (addrParts[addrParts.length - 1].contains("этаж") || addrParts[addrParts.length - 1].contains("эт.")) {
            // Отбрасываем этаж.
            addrPartsLength = addrParts.length - 1;
        }

        boolean haveStreet = false;
        int lastVillageIdx = -1;
        // boolean haveVillage = false;
        VillageDto village = new VillageDto();
        StreetDto streetDto = new StreetDto();

        for (int i = 0; i < addrPartsLength; i++) {

            EVillageType villageType = EVillageType.getVillageType(addrParts[i]);

            if (villageType != EVillageType.UNKNOWN) {
                String villageStr = villageType.getClearRegexp().matcher(addrParts[i])
                                               .replaceAll("")
                                               .replaceAll("[ ]{2,}", " ")
                                               .replace('ё', 'е')
                                               .trim();

                if (i == addrPartsLength - 1 && villageType == EVillageType.THE_VILLAGE) {
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

        if (!streetDto.exists() && lastVillageIdx == addrPartsLength - 2) {
            houseNum = getHouseNumByIdx(addrParts, lastVillageIdx + 1);
        }

        if (streetDto.exists() && strtIdx < addrPartsLength - 1) {
            houseNum = getHouseNumByIdx(addrParts, strtIdx + 1);
        }

        if (lastVillageIdx != addrPartsLength - 1 &&
                (!streetDto.exists() && lastVillageIdx < addrPartsLength - 2
                || CommonUtils.isNullOrEmpty(houseNum) && (!(streetDto.exists() && strtIdx == addrPartsLength - 1)))) {
            // Старый способ
            // String lastPartAddr =

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

    private List<CommonCoordsDto> findHouseCoordsByGeocoder(String address, HouseDto houseDto, CityEntity cityEntity, IGeocoder forcedGeoRepo) {
        // Сейчас нужно получить координаты по адресу.
        List<CommonCoordsDto> rawCoordinatesDto = (forcedGeoRepo == null) ? geocodeHttpRepository
                .getHouseByAddress(cityEntity.getName(), houseDto, address)
                : forcedGeoRepo.getHouseByAddress(cityEntity.getName(), houseDto, address);

        if (CommonUtils.isNullOrEmpty(rawCoordinatesDto)) {
            if (!address.contains(cityEntity.getName())) {
                throw new SaveEmptyAddressException(address);
            }

            throw new NotFoundAddressInCityException(address);
        }

        List<CommonCoordsDto> coordinatesDto = new ArrayList<>();
        // Проверить вхождение в город...
        for (CommonCoordsDto commonCoordsDto : rawCoordinatesDto) {
            if (isPointInCity(commonCoordsDto, cityEntity)) {
                coordinatesDto.add(commonCoordsDto);
            }
        }

        return coordinatesDto;
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
                if (!houseDto.getStreet().exists() && CommonUtils.isNullOrEmpty(houseDto.getHouseNum())
                    || houseDto.getStreet().getStreetType() == EStreetType.LINE && CommonUtils.isNullOrEmpty(houseDto.getHouseNum())) {
                    // Это не анализируемый объект.
                    saveEmptyAddress(connection, noticeEntity);
                    return;
                }

                List<CommonCoordsDto> coordinatesDto;
                try {
                    coordinatesDto = findHouseCoordsByGeocoder(address, houseDto, cityEntity, forcedGeoRepo);
                } catch (SaveEmptyAddressException ex) {
                    LOGGER.error("Адрес не был найден... noticeId: " + noticeEntity.getId() + ". " + noticeEntity.getAddress());
                    saveEmptyAddress(connection, noticeEntity);
                    return;
                } catch (NotFoundAddressInCityException ex) {
                    throw new RuntimeException("Адрес не был найден... noticeId: " + noticeEntity.getId() + ". " + noticeEntity.getAddress());
                }

                if (coordinatesDto.isEmpty()) {
                    throw new NoCoordsInCityException("Найденный адрес не входит в " + noticeEntity.getCityName() + ". noticeId: " + noticeEntity.getId() + ". "
                                                              + noticeEntity.getCityName() + " " + houseDto.printFullAddr());
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

                houseEntity = realtyConverter.createHouseEntity(houseDto, noticeEntity.getCityId(), houseDistrict.getId(), houseCoords);

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
            if (titled instanceof ETotalArea) {
                result.setTotalArea((ETotalArea)titled);
            }
            if (titled instanceof EKitchenArea) {
                result.setKitchenArea((EKitchenArea)titled);
            }
            if (titled instanceof EMetroDistance) {
                result.setMetroDistance((EMetroDistance)titled);
            }
            if (titled instanceof ERealtySegment) {
                result.setRealtySegment((ERealtySegment)titled);
            }
            if (titled instanceof ESimpleHouseType) {
                result.setSimpleHouseType((ESimpleHouseType)titled);
            }
            if (titled instanceof ERepairType) {
                result.setRepairType((ERepairType)titled);
            }
        }

        return result;
    }

    private List<NoticeWrapper> getAlternatives(Connection connection, ImportRealtyObjectEntity importRealtyObjectEntity, HouseEntity houseEntity) {

        double latitude = houseEntity.getCoords().getCenter().getLatitude();
        double longitude = houseEntity.getCoords().getCenter().getLongitude();

        NoticeCategoryEntity noticeCategoryEntity = realtyConverter.toNoticeCategoryEntity(importRealtyObjectEntity);
        noticeCategoryEntity.setRealtyConfigType(ERealtyConfigType.HAKATON_FULL_CONFIG);

        List<VNoticeInfoWithAvgPriceEntity> noticeInfoWithAvgPriceEntityList =
                realtyDao.getNoticesInfoWithAvgPrice(connection, noticeCategoryEntity, true, latitude, longitude);

        if (noticeInfoWithAvgPriceEntityList.size() < 5) {
            List<VNoticeInfoWithAvgPriceEntity> liteNoticeList =
                    realtyDao.getNoticesInfoWithAvgPrice(connection, noticeCategoryEntity, false, latitude, longitude);

            noticeInfoWithAvgPriceEntityList.addAll(liteNoticeList);
        }

        Long[] noticeIdArr = new Long[noticeInfoWithAvgPriceEntityList.size()];

        for (int i = 0; i < noticeInfoWithAvgPriceEntityList.size(); i++) {
            VNoticeInfoWithAvgPriceEntity notice = noticeInfoWithAvgPriceEntityList.get(i);
            noticeIdArr[i] = notice.getId();
        }

        return reportService.getNoticesByIdList(connection, noticeIdArr);
    }

    public void createOrUpdateAllNoticeIndex(RequestHelper requestHelper) {

        Connection connection = requestHelper.getConnection();

        List<NoticeWrapper> noticeInfoList = reportService.getAllActiveNotices(connection);
        RealtyConfigurationDto realtyConfigurationDto = ERealtyConfigType.HAKATON_FULL_CONFIG.buildConfiguration();

        RequestReportDto requestReportDto = new RequestReportDto();
        // Moscow
        requestReportDto.setCityId(2L);

        // Создаём классифаер.
        ReportClassifier reportClassifier = reportService.createReportClassifier(connection, realtyConfigurationDto, requestReportDto);

        try {
            for (NoticeWrapper noticeWrapper : noticeInfoList) {
                List<ITitled> titledList = reportClassifier.getNoticeInfo(noticeWrapper);
                int position = reportClassifier.getNoticePosition(noticeWrapper);

                NoticeCategoryEntity noticeCategoryEntity = toNoticeCategory(titledList, noticeWrapper.getNoticeEntity().getId());
                noticeCategoryEntity.setCanonTypeNumber(position);
                noticeCategoryEntity.setRealtyConfigType(ERealtyConfigType.HAKATON_FULL_CONFIG);
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

    private HouseEntity findHouseByPreviousValues(Connection connection, String address, HouseDto houseDto, CityEntity cityEntity, Map<String, HouseEntity> houseEntityMap) {

        if (houseEntityMap.containsKey(houseDto.printFullAddr())) {
            return houseEntityMap.get(houseDto.printFullAddr());
        }

        // Проверить, есть ли адрес в БД.
        HouseEntity houseEntity = realtyDao.getHouseByAddress(connection, houseDto.printStreetAddr(), houseDto.getHouseNum(), cityEntity);

        if (houseEntity != null) {
            houseEntityMap.put(houseDto.printFullAddr(), houseEntity);
            return houseEntity;
        }

        List<CommonCoordsDto> commonCoordsDtos = findHouseCoordsByGeocoder(address, houseDto, cityEntity, null);

        if (commonCoordsDtos.isEmpty()) {
            throw new RuntimeException("Адрес не найден " + address);
        }

        HouseCoords houseCoords = commonCoordsDtos.get(0).getHouseCoords();

        // Создаём новый дом.
        houseEntity = realtyConverter.createHouseEntity(houseDto, cityEntity.getId(), 1L, houseCoords);

        try {
            realtyDao.saveHouse(connection, houseEntity);
            connection.commit();
        } catch (SQLException exception) {
            LOGGER.error("Ошибка при сохранении дома", exception);
            throw new RuntimeException("Ошибка при сохранении дома", exception);
        }

        // если дом найден
        houseEntityMap.put(houseDto.printFullAddr(), houseEntity);
        return houseEntity;
    }

    private Map<String, AdjustCoeffsDto> getAdjustCoeffs(ImportExcelRealtyDto object, ImportRealtyObjectEntity standart) {
        Map<String, AdjustCoeffsDto> result = new HashMap<>();

        result.put(ERealtyParam.TRADE.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffDictMap.get("TRADE_TRADE_TRADE")));

        ETotalArea objectArea = ETotalArea.getByArea(object.getTotalArea());
        ETotalArea standartArea = ETotalArea.getByArea(standart.getTotalArea());

        if (objectArea != standartArea) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.TOTAL_SQUARE.getName(), standartArea.getName(), objectArea.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.TOTAL_SQUARE.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        EMetroDistance objectMetroDistance = EMetroDistance.fromDistance(object.getMetroDistance());
        EMetroDistance standartMetroDistance = EMetroDistance.fromDistance(standart.getMetroDistance());

        if (objectMetroDistance != standartMetroDistance) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.METRO_DISTANCE.getName(), standartMetroDistance.getName(), objectMetroDistance.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.TOTAL_SQUARE.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        EFloor objectFloor = EFloor.getByFloorAndHouseFloor(object.getFloor(), object.getHouseFloorsCount());
        EFloor standartFloor = EFloor.getByFloorAndHouseFloor(standart.getFloor(), standart.getHouseFloorsCount());

        if (objectFloor != standartFloor) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.FLOOR.getName(), standartFloor.getName(), objectFloor.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.FLOOR.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        EKitchenArea objectKitchenArea = EKitchenArea.getByArea(object.getKitchenArea());
        EKitchenArea standartKitchenArea = EKitchenArea.getByArea(standart.getKitchenArea());

        if (objectKitchenArea != standartKitchenArea) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.KITCHEN_SQUARE.getName(), standartKitchenArea.getName(), objectKitchenArea.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.KITCHEN_SQUARE.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        EBalconParam objectBalconParam = object.getBalcon();
        EBalconParam standartBalconParam = standart.getBalcon();

        if (objectBalconParam != standartBalconParam) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.BALCON.getName(), standartBalconParam.getName(), objectBalconParam.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.BALCON.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        ERepairType objectRepairType = object.getRepairType();
        ERepairType standartRepairType = standart.getRepairType();

        if (objectRepairType != standartRepairType) {
            AdjustCoeffsEntity adjustCoeffsEntity =
                    adjustCoeffDictMap.get(createAdjustCoeffKey(ERealtyParam.REPAIR_TYPE.getName(), standartRepairType.getName(), objectRepairType.getName()));

            if (adjustCoeffsEntity != null) {
                result.put(ERealtyParam.REPAIR_TYPE.getName(), realtyMapper.toAdjustCoeffDto(adjustCoeffsEntity));
            }
        }

        return result;
    }

    private List<AdjustmentImportRealtyWrapDto> calcAnalogCoeffs(Connection connection, List<NoticeWrapper> alternativeList,
                                                                 ImportRealtyObjectEntity standart) {

        List<AdjustmentImportRealtyWrapDto> result = new ArrayList<>();

        for (NoticeWrapper alternativeWrapper : alternativeList) {
            ImportExcelRealtyDto object = realtyConverter.toAdjustmentImportRealtyDto(alternativeWrapper);

            Map<String, AdjustCoeffsDto> adjustCoeffsMap = getAdjustCoeffs(object, standart);

            AdjustmentImportRealtyWrapDto adjustmentImportRealtyWrapDto = new AdjustmentImportRealtyWrapDto();
            adjustmentImportRealtyWrapDto.setObject(object);
            adjustmentImportRealtyWrapDto.setAdjustCoeffsMap(adjustCoeffsMap);
            result.add(adjustmentImportRealtyWrapDto);
        }

        return result;
    }

    public List<EvalutionStandartObjDto> selectStandartObjects(RequestHelper requestHelper, SelectedStandartObjectDto dto) {
        List<EvalutionStandartObjDto> result = new ArrayList<>();

        Connection connection = requestHelper.getConnection();

        CityEntity cityEntity = realtyDao.getCityByName(connection, "Москва");

        // Получить выбранные объекты импорта.
        List<ImportRealtyObjectEntity> standartObjectList = importRealtyDao.getImportRealtyObjectsByIdList(connection, dto.getStandardRecords());

        Map<String, HouseEntity> houseEntityMap = new HashMap<>();

        // Далее нужно найти альтернативы.
        for (ImportRealtyObjectEntity importRealtyObjectEntity : standartObjectList) {
            HouseDto houseDto = getHouseAddr(importRealtyObjectEntity.getAddress(), -1);

            if (houseDto.getVillage().exists() && cityEntity.getName().equals(houseDto.getVillage().getName())) {
                // Не будем писать город второй раз.
                houseDto.getVillage().setName(null);
                houseDto.getVillage().setVillageType(null);
            }

            HouseEntity houseEntity = findHouseByPreviousValues(connection, importRealtyObjectEntity.getAddress(), houseDto, cityEntity, houseEntityMap);

            List<NoticeWrapper> alternativeList = getAlternatives(connection, importRealtyObjectEntity, houseEntity);

            List<AdjustmentImportRealtyWrapDto> adjustmentImportRealtyWrapDtos =
                    calcAnalogCoeffs(connection, alternativeList, importRealtyObjectEntity);

            EvalutionStandartObjDto evalutionStandartObjDto = new EvalutionStandartObjDto();
            evalutionStandartObjDto.setStandartObj(importMapper.toImportExcelRealtyDto(importRealtyObjectEntity));
            evalutionStandartObjDto.setAnalogList(adjustmentImportRealtyWrapDtos);

            result.add(evalutionStandartObjDto);
        }

        return result;
    }

    private void calcStandartObject(List<AdjustmentImportRealtyWrapDto> analogList, ImportExcelRealtyDto importExcelRealtyDto) {
        double sumAnalogPriceBySquare = 0;

        for (AdjustmentImportRealtyWrapDto adjustmentImportRealtyWrapDto : analogList) {
            Map<String, AdjustCoeffsDto> adjustCoeffsDtoMap = adjustmentImportRealtyWrapDto.getAdjustCoeffsMap();
            ImportExcelRealtyDto analog = adjustmentImportRealtyWrapDto.getObject();

            double priceBySquare = analog.getSum() / analog.getTotalArea();

            for (AdjustCoeffsDto adjustCoeffsDto : adjustCoeffsDtoMap.values()) {
                if (!adjustCoeffsDto.isAbsolute()) {
                    priceBySquare = priceBySquare * (1 - adjustCoeffsDto.getValue());
                } else {
                    priceBySquare = priceBySquare - adjustCoeffsDto.getValue();
                }
            }

            sumAnalogPriceBySquare += priceBySquare;
        }

        double avgSumByMeter = sumAnalogPriceBySquare / analogList.size();
        importExcelRealtyDto.setSum(avgSumByMeter * importExcelRealtyDto.getTotalArea());
    }

    private ImportExcelRealtyDto findCorrectStandartObject(ImportRealtyObjectEntity importRealtyObjectEntity,
                                                              List<EvalutionStandartObjDto> evalutionStandartObjDtoList) {

        for (EvalutionStandartObjDto evalutionStandartObjDto : evalutionStandartObjDtoList) {
            ImportExcelRealtyDto importStandartDto = evalutionStandartObjDto.getStandartObj();

            ERoomsCount stRoomsCount = ERoomsCount.fromString(importStandartDto.getRoomsCount());

            if (importRealtyObjectEntity.getRoomsCount() != stRoomsCount) {
                continue;
            }

            if (importRealtyObjectEntity.getRealtySegment() != importStandartDto.getRealtySegment()) {
                continue;
            }

            EHouseFloor houseFloor = EHouseFloor.getByHouseFloor(importRealtyObjectEntity.getHouseFloorsCount());
            EHouseFloor stHouseFloor = EHouseFloor.getByHouseFloor(importStandartDto.getHouseFloorsCount());

            if (houseFloor != stHouseFloor) {
                continue;
            }

            if (importRealtyObjectEntity.getWallMaterial() != importStandartDto.getWallMaterial()) {
                continue;
            }

            return importStandartDto;
        }

        return null;
    }

    private void calcConcreteObjectByStandart(ImportRealtyObjectEntity importRealtyObjectEntity, ImportExcelRealtyDto standartObject,
                                              Map<String, AdjustCoeffsDto> adjustCoeffsMap) {

        double sumBySquare = standartObject.getSum() / standartObject.getTotalArea();

        for (AdjustCoeffsDto adjustCoeffsDto : adjustCoeffsMap.values()) {
            if (!adjustCoeffsDto.isAbsolute()) {
                sumBySquare = sumBySquare * (1 - adjustCoeffsDto.getValue());
            } else {
                sumBySquare = sumBySquare - adjustCoeffsDto.getValue();
            }
        }

        importRealtyObjectEntity.setSum(sumBySquare * importRealtyObjectEntity.getTotalArea());
    }

    public ImportResponseDto calcResult(RequestHelper requestHelper, StandartRequestWrapper standartRequestWrapper) {
        Connection connection = requestHelper.getConnection();
        long requestId = standartRequestWrapper.getRequestId();

        List<ImportRealtyObjectEntity> importRealtyObjectEntityList =
                importRealtyDao.getImportRealtyObjectsByRequest(connection, requestId);

        // 1. Считаем цену эталонного объекта.

        List<EvalutionStandartObjDto> evalutionStandartObjDtoList = standartRequestWrapper.getEvalutionStandartObjDtoList();

        for (EvalutionStandartObjDto evalutionStandartObjDto : evalutionStandartObjDtoList) {
            ImportExcelRealtyDto importExcelRealtyDto = evalutionStandartObjDto.getStandartObj();

            calcStandartObject(evalutionStandartObjDto.getAnalogList(), importExcelRealtyDto);
        }

        // 2. Рассчитываем остальные объекты из запроса.
        for (ImportRealtyObjectEntity importRealtyObjectEntity : importRealtyObjectEntityList) {
            try {
                // 3. Найти подходящий эталонный объект.
                ImportExcelRealtyDto standartObject = findCorrectStandartObject(importRealtyObjectEntity, evalutionStandartObjDtoList);

                if (standartObject == null) {
                    continue;
                }

                // 4. Найти коэффициенты
                Map<String, AdjustCoeffsDto> adjustCoeffsMap = getAdjustCoeffs(standartObject, importRealtyObjectEntity);

                // 5. Посчитать стоимость.
                calcConcreteObjectByStandart(importRealtyObjectEntity, standartObject, adjustCoeffsMap);
            } catch(Exception ex) {
                LOGGER.error("Ошибка при поиске эталонного объекта ", ex);
                // Эталона не нашлось...
                int i = 0;
            }
        }

        // 6. Сохранить результат.
        importRealtyDao.updateImportRealtyObjects(connection, importRealtyObjectEntityList);
        List<ImportExcelRealtyDto> realtyDtoList = new ArrayList<>();

        for (ImportRealtyObjectEntity importRealtyObjectEntity : importRealtyObjectEntityList) {
            realtyDtoList.add(importMapper.toImportExcelRealtyDto(importRealtyObjectEntity));
        }

        ImportResponseDto importResponseDto = new ImportResponseDto();

        importResponseDto.setRequestId(standartRequestWrapper.getRequestId());
        importResponseDto.setImportExcelRealtyDtoList(realtyDtoList);

        return importResponseDto;
    }

}
