package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EFloor;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class FloorClassParam implements IClassParam {

    private static final FloorClassParam instance = new FloorClassParam();

    public static FloorClassParam getInstance() {
        return instance;
    }

    private FloorClassParam() {
    }

    public int getFloor(NoticeWrapper value) {
        VFeatureValueEntity floorFeature = value.getFeatureByExactName(EFeatureExactName.FLOOR);
        return Integer.parseInt(floorFeature.getValue());
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity floorFeature = value.getFeatureByExactName(EFeatureExactName.FLOOR);
        VFeatureValueEntity houseFloorFeature = value.getFeatureByExactName(EFeatureExactName.HOUSE_FLOORS);

        if (floorFeature == null || houseFloorFeature == null) {
            throw new RuntimeException("Отсутствует этаж noticeId: " + value.getNoticeEntity().getId());
        }

        int floor = Integer.parseInt(floorFeature.getValue());
        int houseFloor = Integer.parseInt(houseFloorFeature.getValue());

        return EFloor.getByFloorAndHouseFloor(floor, houseFloor).getId();
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
