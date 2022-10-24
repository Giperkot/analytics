package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EBalconParam;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class BalconClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {
        VFeatureValueEntity featureValueEntity = value.getFeatureByExactName(EFeatureExactName.BALCON_LOGGIA);
        EBalconParam balconParam = (featureValueEntity != null && "true".equals(featureValueEntity.getValue()))
                ? EBalconParam.TRUE : EBalconParam.FALSE;

        return balconParam.getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EBalconParam.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EBalconParam.getValues().length;
    }
}
