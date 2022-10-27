package service.realty.catalog;

import common.CommonUtils;
import db.entity.realty.HouseAddInfoEntity;
import enums.report.EHouseType;

public abstract class AbstractCatalogService {

    protected EHouseType getHouseType(String wallType) {

        if (CommonUtils.isNullOrEmpty(wallType)) {
            return EHouseType.UNKNOWN;
        }

        if (wallType.contains("Кирпич")) {
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

    protected HouseAddInfoEntity createHouseAddinfoEntity(long houseId, int buildingYear, Integer deterioration, String wallMateria,
                                                        int floorsCount) {
        HouseAddInfoEntity houseAddInfoEntity = new HouseAddInfoEntity();
        houseAddInfoEntity.setHouseId(houseId);
        houseAddInfoEntity.setBuildYear(buildingYear);
        houseAddInfoEntity.setDeterioration(deterioration);
        houseAddInfoEntity.setHouseType(getHouseType(wallMateria));
        houseAddInfoEntity.setFloorsCount(floorsCount);

        return houseAddInfoEntity;
    }

}
