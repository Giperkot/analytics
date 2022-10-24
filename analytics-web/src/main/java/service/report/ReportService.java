package service.report;

import common.CommonUtils;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import db.entity.parser.NoticeEntity;
import db.entity.parser.view.VDistrictNoticeEntity;
import db.entity.parser.view.VFeatureValueEntity;
import db.entity.realty.NoticeAveragePriceEntity;
import db.entity.realty.ShortDistrictEntity;
import dto.report.NoticeWrapper;
import dto.report.RealtyConfigurationDto;
import dto.report.RequestReportDto;
import enums.report.EPlacement;
import enums.report.ERealtyConfigType;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import helper.report.ReportClassifier;
import helper.report.param.BalconClassParam;
import helper.report.param.FloorClassParam;
import helper.report.param.HouseBuildYearClassParam;
import helper.report.param.HouseFloorClassParam;
import helper.report.param.HouseTypeClassParam;
import helper.report.param.KitchenSquareClassParam;
import helper.report.param.PlacementClassParam;
import helper.report.param.RoomsCountClassParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.realty.RealtyService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyService.class);
    private static final ReportService instance = new ReportService();

    public static ReportService getInstance() {
        return instance;
    }

    private final RealtyDao realtyDao = RealtyDao.getInstance();

    private final ParserDao parserDao = ParserDao.getInstance();

    private ReportService() {
    }

    public ReportClassifier createReportClassifier(Connection connection, RealtyConfigurationDto realtyConfigurationDto,
                                                    RequestReportDto requestReportDto) {
        ReportClassifier reportClassifier = new ReportClassifier();

        EPlacement placement = realtyConfigurationDto.getPlacement();
        long cityId = requestReportDto.getCityId();

        List<ShortDistrictEntity> districtEntityList = null;

        if (placement == EPlacement.MICRODISTRICT) {
            districtEntityList = realtyDao.getMicroDistricts(connection, cityId, 1, 100);
        }

        if (placement == EPlacement.DISTRICT) {
            districtEntityList = realtyDao.getMicroDistricts(connection, cityId, 0, 0);
        }

        if (CommonUtils.isNotEmpty(districtEntityList)) {
            PlacementClassParam placementClassParam = new PlacementClassParam(districtEntityList);

            reportClassifier.addClassParam(placementClassParam);
        }

        if (realtyConfigurationDto.isFloor()) {
            reportClassifier.addClassParam(new FloorClassParam());
        }

        if (realtyConfigurationDto.isHouseFloor()) {
            reportClassifier.addClassParam(new HouseFloorClassParam());
        }

        if (realtyConfigurationDto.isKitchenSquare()) {
            reportClassifier.addClassParam(new KitchenSquareClassParam());
        }

        if (realtyConfigurationDto.isBalcon()) {
            reportClassifier.addClassParam(new BalconClassParam());
        }

        if (realtyConfigurationDto.isRoomsCount()) {
            reportClassifier.addClassParam(new RoomsCountClassParam());
        }

        if (realtyConfigurationDto.isHouseType()) {
            reportClassifier.addClassParam(new HouseTypeClassParam());
        }

        if (realtyConfigurationDto.isHouseBuildYear()) {
            reportClassifier.addClassParam(new HouseBuildYearClassParam());
        }

        return reportClassifier;
    }

    private List<NoticeWrapper> getAllNoticesInfoByList(Connection connection, List<VDistrictNoticeEntity> activeNoticeList) {
        List<NoticeWrapper> noticeInfoList = new ArrayList<>();
        Long[] noticeIds = new Long[activeNoticeList.size()];

        for (int i = 0; i < activeNoticeList.size(); i++) {
            NoticeEntity noticeEntity = activeNoticeList.get(i);
            noticeIds[i] = noticeEntity.getId();
        }

        TLongObjectHashMap<List<VFeatureValueEntity>> featuresListMap =
                parserDao.getFeaturesByNoticeIdArr(connection, noticeIds);

        for (VDistrictNoticeEntity noticeEntity : activeNoticeList) {
            noticeInfoList.add(new NoticeWrapper(noticeEntity, featuresListMap.get(noticeEntity.getId())));
        }

        return noticeInfoList;
    }

    public List<NoticeWrapper> getQllNoticeByHouse(Connection connection, long houseId) {
        List<NoticeWrapper> noticeInfoList = new ArrayList<>();

        List<VDistrictNoticeEntity> noticeByHoustList = parserDao.getNoticesByHouse(connection, houseId);

        return getAllNoticesInfoByList(connection, noticeByHoustList);
    }

    public List<NoticeWrapper> getAllActiveNotices(Connection connection) {
        List<VDistrictNoticeEntity> activeNoticeList = parserDao.getActiveNotices(connection);

        return getAllNoticesInfoByList(connection, activeNoticeList);
    }

    public String generateReport(RequestHelper requestHelper, RequestReportDto requestReportDto) {

        Connection connection = requestHelper.getConnection();

        List<NoticeWrapper> noticeInfoList = getAllActiveNotices(connection);

        ERealtyConfigType configType = requestReportDto.getConfigType();

        RealtyConfigurationDto realtyConfigurationDto = configType.buildConfiguration();

        // Создаём классифаер.
        ReportClassifier reportClassifier = createReportClassifier(connection, realtyConfigurationDto, requestReportDto);

        for (NoticeWrapper noticeWrapper : noticeInfoList) {
            reportClassifier.addToCalc(noticeWrapper);
        }

        TIntObjectHashMap<ReportClassifier.CalcCache> categoryCacheMap = reportClassifier.getCalcCacheMap();
        List<NoticeAveragePriceEntity> noticeAveragePriceList = new ArrayList<>();

        for (int ordinal : categoryCacheMap.keys()) {
            ReportClassifier.CalcCache calcCache = categoryCacheMap.get(ordinal);

            if (calcCache == null) {
                continue;
            }

            NoticeAveragePriceEntity noticeAveragePrice = new NoticeAveragePriceEntity();
            noticeAveragePrice.setSum(calcCache.totalSum / calcCache.totalSquare);
            noticeAveragePrice.setRealtyConfigType(configType.getConfigName());
            noticeAveragePrice.setCanonicalTypeNumber(ordinal);

            noticeAveragePriceList.add(noticeAveragePrice);
        }

        try {
            realtyDao.saveOrUpdateNoticeAveragePrice(connection, noticeAveragePriceList);
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return reportClassifier.getCalcResult();
    }


}
