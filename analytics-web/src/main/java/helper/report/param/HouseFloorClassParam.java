package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EHouseFloor;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class HouseFloorClassParam implements IClassParam {

    private static final HouseFloorClassParam instance = new HouseFloorClassParam();

    public static HouseFloorClassParam getInstance() {
        return instance;
    }

    private HouseFloorClassParam() {
    }

    public int getFloorValue(NoticeWrapper value) {
        VFeatureValueEntity houseFloorFeature = value.getFeatureByExactName(EFeatureExactName.HOUSE_FLOORS);

        if (houseFloorFeature == null) {
            throw new RuntimeException("Отсутствует количество этажей в доме: noticeId: " + value.getNoticeEntity().getId());
        }

        return Integer.parseInt(houseFloorFeature.getValue());
    }

    public EHouseFloor getHouseFloor(NoticeWrapper value) {
        int val = getFloorValue(value);

        return EHouseFloor.getByHouseFloor(val);
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        return getHouseFloor(value).getId();
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
