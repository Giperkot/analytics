package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ERepairType;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class RepairTypeClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity repair = value.getFeatureByExactName(EFeatureExactName.REPAIR);
        VFeatureValueEntity repairType = value.getFeatureByExactName(EFeatureExactName.REPAIR_TYPE);

        if (repair != null) {
            String repairStr = repair.getValue();

            if ("требует ремонта".equals(repairStr)) {
                return ERepairType.NONE.getId();
            }
            if ("косметический".equals(repairStr)) {
                return ERepairType.BAD.getId();
            }
            if ("дизайнерский".equals(repairStr)) {
                return ERepairType.GOOD.getId();
            }
            if ("евро".equals(repairStr)) {
                return ERepairType.GOOD.getId();
            }
        }

        if (repairType != null) {
            String repairStr = repairType.getValue();

            if ("без отделки".equals(repairStr)) {
                return ERepairType.NONE.getId();
            }
            if ("предчистовая".equals(repairStr)) {
                return ERepairType.BAD.getId();
            }
            if ("чистовая".equals(repairStr)) {
                return ERepairType.GOOD.getId();
            }
        }

        return ERepairType.UNKNOWN.getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return ERepairType.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return ERepairType.getValues().length;
    }
}
