package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ETotalArea;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class TotalAreaClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity totalAreaFeature = value.getFeatureByExactName(EFeatureExactName.TOTAL_AREA);

        double totalArea = Double.parseDouble(totalAreaFeature.getValue());

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
