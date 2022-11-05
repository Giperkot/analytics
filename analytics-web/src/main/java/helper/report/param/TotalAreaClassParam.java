package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ETotalArea;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class TotalAreaClassParam implements IClassParam {

    private static final TotalAreaClassParam instance = new TotalAreaClassParam();

    public static TotalAreaClassParam getInstance() {
        return instance;
    }

    private TotalAreaClassParam() {
    }

    public double getTotalArea(NoticeWrapper value) {
        VFeatureValueEntity totalAreaFeature = value.getFeatureByExactName(EFeatureExactName.TOTAL_AREA);

        return Double.parseDouble(totalAreaFeature.getValue());
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        double totalArea = getTotalArea(value);
        return ETotalArea.getByArea(totalArea).getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return ETotalArea.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return ETotalArea.getValues().length;
    }
}
