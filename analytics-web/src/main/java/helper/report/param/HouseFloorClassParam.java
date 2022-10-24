package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EHouseFloor;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class HouseFloorClassParam implements IClassParam {


    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity houseFloorFeature = value.getFeatureByExactName(EFeatureExactName.HOUSE_FLOORS);

        if (houseFloorFeature == null) {
            throw new RuntimeException("Отсутствует количество этажей в доме: noticeId: " + value.getNoticeEntity().getId());
        }

        int val = Integer.parseInt(houseFloorFeature.getValue());

        if (val < 4) {
            return EHouseFloor.LOW_FLOORS.getId();
        }

        if (val <= 5) {
            return EHouseFloor.FIVE_OR_LESS.getId();
        }

        return EHouseFloor.MORE_THAN_FIVE.getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EHouseFloor.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EHouseFloor.getValues().length;
    }
}
