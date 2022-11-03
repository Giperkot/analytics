package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ERealtySegment;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class RealtySegmentParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity deadline = value.getFeatureByExactName(EFeatureExactName.DEADLINE);
        VFeatureValueEntity houseYear = value.getFeatureByExactName(EFeatureExactName.HOUSE_YEAR);

        if (deadline == null && houseYear == null) {
            return ERealtySegment.UNKNOWN.getId();
            // throw new RuntimeException("Год постройки не указан");
        }

        if (deadline != null && deadline.getValue() != null) {
            return ERealtySegment.NEW_BUILDING.getId();
        }

        if (houseYear != null) {
            int buildYear = Integer.parseInt(houseYear.getValue());

            if (buildYear >= 2020) {
                return ERealtySegment.NEW_BUILDING.getId();
            }

            if (buildYear >= 1989) {
                return ERealtySegment.MODERN_HOUSE.getId();
            }

            return ERealtySegment.OLD_HOUSE.getId();
        }

        throw new RuntimeException("Год постройки не указан");
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return ERealtySegment.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return ERealtySegment.getValues().length;
    }
}
