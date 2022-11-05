package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EHouseType;
import enums.report.ESimpleHouseType;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class SimpleHouseTypeParam implements IClassParam {

    private static final SimpleHouseTypeParam instance = new SimpleHouseTypeParam();

    public static SimpleHouseTypeParam getInstance() {
        return instance;
    }

    private SimpleHouseTypeParam() {
    }

    public ESimpleHouseType getSimpleHouseType(NoticeWrapper value) {
        VFeatureValueEntity houseTypeStr = value.getFeatureByExactName(EFeatureExactName.HOUSE_TYPE);

        if (houseTypeStr == null) {
            // throw new RuntimeException("Тип стен не указан.");
            return ESimpleHouseType.UNKNOWN;
        }

        EHouseType houseType = EHouseType.getByTitle(houseTypeStr.getValue());

        switch (houseType) {
            case BRICK:
                return ESimpleHouseType.BRICK;
            case BLOCK:
            case PANEL:
                return ESimpleHouseType.PANEL;
            case MONOLIT:
            case BRICK_MONOLIT:
                return ESimpleHouseType.MONOLIT;
            default:
                return ESimpleHouseType.UNKNOWN;
            //throw new RuntimeException("Тип стен не указан.");
        }
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        return getSimpleHouseType(value).getId();
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
