package service.realty.catalog;

import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;

public interface IRealtyCatalogService {

    void init() throws InterruptedException, IOException, URISyntaxException;

    HouseAddInfoEntity fillHouseYearInfo(HouseEntity houseEntity, Connection connection) throws InterruptedException, IOException, URISyntaxException;

}
