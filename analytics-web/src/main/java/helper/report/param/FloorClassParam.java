package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EFloor;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class FloorClassParam implements IClassParam {


    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity floorFeature = value.getFeatureByExactName(EFeatureExactName.FLOOR);
        VFeatureValueEntity houseFloorFeature = value.getFeatureByExactName(EFeatureExactName.HOUSE_FLOORS);

        if (floorFeature == null || houseFloorFeature == null) {
            throw new RuntimeException("Отсутствует этаж noticeId: " + value.getNoticeEntity().getId());
        }

        int floor = Integer.parseInt(floorFeature.getValue());
        int houseFloor = Integer.parseInt(houseFloorFeature.getValue());

        if (floor < houseFloor && floor > 1) {
            return EFloor.AVERAGE.getId();
        } else {
            return EFloor.FIRST_OR_LAST.getId();
        }
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EFloor.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EFloor.getValues().length;
    }
}
