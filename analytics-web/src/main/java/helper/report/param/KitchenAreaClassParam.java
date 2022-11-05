package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EKitchenArea;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class KitchenAreaClassParam implements IClassParam {

    private static final KitchenAreaClassParam instance = new KitchenAreaClassParam();

    public static KitchenAreaClassParam getInstance() {
        return instance;
    }

    private KitchenAreaClassParam() {
    }

    public double getKitchenArea(NoticeWrapper value) {
        VFeatureValueEntity kitchenAreaFeature = value.getFeatureByExactName(EFeatureExactName.KITCHEN_AREA);

        if (kitchenAreaFeature == null || kitchenAreaFeature.getValue() == null) {
            return -1;
        }

        return Double.parseDouble(kitchenAreaFeature.getValue());
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        VFeatureValueEntity kitchenAreaFeature = value.getFeatureByExactName(EFeatureExactName.KITCHEN_AREA);

        if (kitchenAreaFeature == null || kitchenAreaFeature.getValue() == null) {
            return EKitchenArea.UNKNOWN.getId();
        }

        double kitchenArea = Double.parseDouble(kitchenAreaFeature.getValue());

        return EKitchenArea.getByArea(kitchenArea).getId();
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
