package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ERoomsCount;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class RoomsCountClassParam implements IClassParam {

    private static final RoomsCountClassParam instance = new RoomsCountClassParam();

    public static RoomsCountClassParam getInstance() {
        return instance;
    }

    private RoomsCountClassParam() {
    }

    public ERoomsCount getRoomsCount(NoticeWrapper value) {
        VFeatureValueEntity roomsCountFeature = value.getFeatureByExactName(EFeatureExactName.ROOM_COUNT);

        if (roomsCountFeature == null) {
            throw new RuntimeException("Отсутствует количество комнат");
        }

        try {
            return ERoomsCount.getByRoomsCount(roomsCountFeature.getValue());
        }catch (Exception ex) {
            throw new RuntimeException("Неверный формат числа: " + roomsCountFeature.getValue() +
                                               " noticeId: " + value.getNoticeEntity().getId(), ex);
        }
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        return getRoomsCount(value).getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return ERoomsCount.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return ERoomsCount.getValues().length;
    }
}
