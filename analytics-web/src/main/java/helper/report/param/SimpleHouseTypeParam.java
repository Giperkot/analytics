package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EHouseType;
import enums.report.ESimpleHouseType;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class SimpleHouseTypeParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity houseTypeStr = value.getFeatureByExactName(EFeatureExactName.HOUSE_TYPE);

        if (houseTypeStr == null) {
            // throw new RuntimeException("Тип стен не указан.");
            return ESimpleHouseType.UNKNOWN.getId();
        }

        EHouseType houseType = EHouseType.getByTitle(houseTypeStr.getValue());

        switch (houseType) {
            case BRICK:
                return ESimpleHouseType.BRICK.getId();
            case BLOCK:
            case PANEL:
                return ESimpleHouseType.PANEL.getId();
            case MONOLIT:
            case BRICK_MONOLIT:
                return ESimpleHouseType.MONOLIT.getId();
            default:
                return ESimpleHouseType.UNKNOWN.getId();
                //throw new RuntimeException("Тип стен не указан.");
        }
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return ESimpleHouseType.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return ESimpleHouseType.getValues().length;
    }
}
