package dto.report;

import db.entity.parser.view.VDistrictNoticeEntity;
import db.entity.parser.view.VFeatureValueEntity;
import enums.EFeatureExactName;
import enums.report.EHouseType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeWrapper {

    private VDistrictNoticeEntity noticeEntity;

    private final Map<EFeatureExactName, VFeatureValueEntity> featureDtoMap;

    public NoticeWrapper(VDistrictNoticeEntity noticeEntity,
                         List<VFeatureValueEntity> featureDtoList) {
        this.noticeEntity = noticeEntity;
        this.featureDtoMap = new HashMap<>();

        for (int i = 0; i < featureDtoList.size(); i++) {
            VFeatureValueEntity featureValueEntity = featureDtoList.get(i);
            featureDtoMap.put(featureValueEntity.getExactName(), featureValueEntity);
        }
    }

    public double getNoticeSum() {

        double sum = noticeEntity.getSum();

        if (sum <= 0) {
            throw new RuntimeException("Нет стоимости noticeId: " + noticeEntity.getId() + " Address: " + noticeEntity.getAddress());
        }

        return noticeEntity.getSum();
    }

    public double getNoticeSquare() {
        VFeatureValueEntity featureValueEntity = featureDtoMap.get(EFeatureExactName.TOTAL_AREA);

        if (featureValueEntity == null) {
            throw new RuntimeException("Нет общей площади noticeId: " + noticeEntity.getId() + " Address: " + noticeEntity.getAddress());
        }

        return Double.parseDouble(featureValueEntity.getValue());
    }

    public EHouseType getHouseType() {
        VFeatureValueEntity featureValueEntity = featureDtoMap.get(EFeatureExactName.HOUSE_TYPE);

        String houseType = featureValueEntity.getValue();

        if (houseType == null) {
            return EHouseType.UNKNOWN;
        }

        switch (houseType) {
            case "блочный":
                return EHouseType.BLOCK;
            case "монолитный":
                return EHouseType.MONOLIT;
            case "Кирпичный":
            case "кирпичный":
                return EHouseType.BRICK;
            case "панельный":
                return EHouseType.PANEL;
            case "монолитно-кирпичный":
                return EHouseType.BRICK_MONOLIT;
            case "деревянный":
                return EHouseType.WOOD;
        }

        return EHouseType.UNKNOWN;
    }

    public int getFloorsCount() {
        VFeatureValueEntity featureValueEntity = featureDtoMap.get(EFeatureExactName.HOUSE_FLOORS);

        return Integer.parseInt(featureValueEntity.getValue());
    }

    public VFeatureValueEntity getFeatureByExactName(EFeatureExactName exactName) {
        return featureDtoMap.get(exactName);
    }

    public VDistrictNoticeEntity getNoticeEntity() {
        return noticeEntity;
    }

    public void setNoticeEntity(VDistrictNoticeEntity noticeEntity) {
        this.noticeEntity = noticeEntity;
    }
}
