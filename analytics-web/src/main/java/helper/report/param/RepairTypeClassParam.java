package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.ERepairType;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class RepairTypeClassParam implements IClassParam {

    private static final RepairTypeClassParam instance = new RepairTypeClassParam();

    public static RepairTypeClassParam getInstance() {
        return instance;
    }

    private RepairTypeClassParam() {
    }

    public ERepairType getRepairType(NoticeWrapper value) {
        VFeatureValueEntity repair = value.getFeatureByExactName(EFeatureExactName.REPAIR);
        VFeatureValueEntity repairType = value.getFeatureByExactName(EFeatureExactName.REPAIR_TYPE);

        if (repair != null) {
            String repairStr = repair.getValue();

            if ("требует ремонта".equals(repairStr)) {
                return ERepairType.NONE;
            }
            if ("косметический".equals(repairStr)) {
                return ERepairType.BAD;
            }
            if ("дизайнерский".equals(repairStr)) {
                return ERepairType.GOOD;
            }
            if ("евро".equals(repairStr)) {
                return ERepairType.GOOD;
            }
        }

        if (repairType != null) {
            String repairStr = repairType.getValue();

            if ("без отделки".equals(repairStr)) {
                return ERepairType.NONE;
            }
            if ("предчистовая".equals(repairStr)) {
                return ERepairType.BAD;
            }
            if ("чистовая".equals(repairStr)) {
                return ERepairType.GOOD;
            }
        }

        return ERepairType.UNKNOWN;
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        return getRepairType(value).getId();
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
