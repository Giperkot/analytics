package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ERoomsCount;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class RoomsCountClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {
        // ERoomsCount

        VFeatureValueEntity roomsCountFeature = value.getFeatureByExactName(EFeatureExactName.ROOM_COUNT);

        if (roomsCountFeature == null) {
            throw new RuntimeException("Отсутствует количество комнат");
        }

        if ("студия".equals(roomsCountFeature.getValue())) {
            return ERoomsCount.STUDIO.getId();
        }

        if ("свободная планировка".equals(roomsCountFeature.getValue())) {
            return ERoomsCount.FREE_LAYOUT.getId();
        }

        if ("10 и больше".equals(roomsCountFeature.getValue())) {
            return ERoomsCount.TEN_AND_MORE.getId();
        }

        int count;
        try {
            count = Integer.parseInt(roomsCountFeature.getValue());
        } catch (Exception ex) {
            throw new RuntimeException("Неверный формат числа: " + roomsCountFeature.getValue() +
                                               " noticeId: " + value.getNoticeEntity().getId(), ex);
        }


        if (count == 1) {
            return ERoomsCount.ONE.getId();
        }

        if (count == 2) {
            return ERoomsCount.TWO.getId();
        }

        if (count == 3) {
            return ERoomsCount.THREE.getId();
        }

        if (count == 4) {
            return ERoomsCount.FOUR.getId();
        }

        if (count == 5) {
            return ERoomsCount.FIVE.getId();
        }

        if (count > 5) {
            return ERoomsCount.MORE_THAN_FIVE.getId();
        }

        throw new RuntimeException("некорректное значение количества комнат: " + count);
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
