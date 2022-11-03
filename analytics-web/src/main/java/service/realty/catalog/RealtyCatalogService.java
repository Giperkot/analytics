package service.realty.catalog;

import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import enums.report.EHouseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;

public class RealtyCatalogService implements IRealtyCatalogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyCatalogService.class);
    private static final RealtyCatalogService instance = new RealtyCatalogService();

    public static RealtyCatalogService getInstance() {
        return instance;
    }

    private TwoGisService twoGisService = TwoGisService.getInstance();

    private GisZkhService gisZkhService = GisZkhService.getInstance();

    private RealtyCatalogService() {
    }

    @Override
    public void init() throws InterruptedException, IOException, URISyntaxException {
        twoGisService.init();
    }

    @Override
    public HouseAddInfoEntity fillHouseYearInfo(HouseEntity houseEntity, Connection connection) throws InterruptedException, IOException, URISyntaxException {

        // Сначала ищем в GisZkh
        HouseAddInfoEntity houseAddInfoEntity = null;
        try {
            houseAddInfoEntity = gisZkhService.fillHouseYearInfo(houseEntity, connection);
        } catch (Exception ex) {

        }

        if (houseAddInfoEntity == null || houseAddInfoEntity.getBuildYear() < 1 || houseAddInfoEntity.getHouseType() == null
                || houseAddInfoEntity.getHouseType() == EHouseType.UNKNOWN) {
            HouseAddInfoEntity twoGisHouseInfo = twoGisService.fillHouseYearInfo(houseEntity, connection);

            if (twoGisHouseInfo.getHouseType() != null && twoGisHouseInfo.getHouseType() != EHouseType.UNKNOWN) {
                houseAddInfoEntity.setHouseType(twoGisHouseInfo.getHouseType());
            }

            if (twoGisHouseInfo.getBuildYear() > 1) {
                houseAddInfoEntity.setBuildYear(twoGisHouseInfo.getBuildYear());
            }
        }

        return houseAddInfoEntity;
    }
}
