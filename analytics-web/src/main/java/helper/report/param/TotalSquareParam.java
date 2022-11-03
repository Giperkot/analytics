package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ETotalArea;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class TotalSquareParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity totalAreaFeature = value.getFeatureByExactName(EFeatureExactName.TOTAL_AREA);

        double totalArea = Double.parseDouble(totalAreaFeature.getValue());

        if (totalArea < 30) {
            return ETotalArea.LESS30.getId();
        }

        if (totalArea >= 30 && totalArea < 50) {
            return ETotalArea.FROM30TO50.getId();
        }

        if (totalArea >= 50 && totalArea < 65) {
            return ETotalArea.FROM50TO65.getId();
        }

        if (totalArea >= 65 && totalArea < 90) {
            return ETotalArea.FROM65TO90.getId();
        }

        if (totalArea >= 90 && totalArea < 120) {
            return ETotalArea.FROM90TO120.getId();
        }

        return ETotalArea.MORE120.getId();
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
