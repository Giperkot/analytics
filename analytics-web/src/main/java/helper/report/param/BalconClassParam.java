package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EBalconParam;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class BalconClassParam implements IClassParam {

    private static final BalconClassParam instance = new BalconClassParam();

    public static BalconClassParam getInstance() {
        return instance;
    }

    private BalconClassParam() {
    }

    public EBalconParam getBalcon(NoticeWrapper value) {
        VFeatureValueEntity featureValueEntity = value.getFeatureByExactName(EFeatureExactName.BALCON_LOGGIA);
        return (featureValueEntity != null && "true".equals(featureValueEntity.getValue()))
                ? EBalconParam.TRUE : EBalconParam.FALSE;
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        EBalconParam balconParam = getBalcon(value);

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
