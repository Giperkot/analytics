package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EKitchenArea;
import enums.report.ETotalArea;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class KitchenAreaClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {
        VFeatureValueEntity kitchenAreaFeature = value.getFeatureByExactName(EFeatureExactName.KITCHEN_AREA);

        if (kitchenAreaFeature == null || kitchenAreaFeature.getValue() == null) {
            return EKitchenArea.UNKNOWN.getId();
        }

        double kitchenArea = Double.parseDouble(kitchenAreaFeature.getValue());

        if (kitchenArea < 7) {
            return ETotalArea.LESS30.getId();
        }

        if (kitchenArea >= 7 && kitchenArea < 10) {
            return ETotalArea.FROM30TO50.getId();
        }

        if (kitchenArea >= 10 && kitchenArea < 15) {
            return ETotalArea.FROM50TO65.getId();
        }

        return ETotalArea.MORE120.getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EKitchenArea.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EKitchenArea.getValues().length;
    }
}
