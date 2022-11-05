package helper.report.param;

import db.entity.parser.view.VFeatureValueEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EHouseBuildYear;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class HouseBuildYearClassParam implements IClassParam {

    private static final HouseBuildYearClassParam instance = new HouseBuildYearClassParam();

    public static HouseBuildYearClassParam getInstance() {
        return instance;
    }

    private HouseBuildYearClassParam() {
    }
    
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity houseYearFeature = value.getFeatureByExactName(EFeatureExactName.HOUSE_YEAR);

        if (houseYearFeature == null) {
            throw new RuntimeException("Отсутствует год постройки noticeId: " + value.getNoticeEntity().getId());
        }

        int houseYear = Integer.parseInt(houseYearFeature.getValue());

        if (houseYear <= 1950) {
            return EHouseBuildYear.FIFTY_AND_EARLIER.getId();
        }

        if (houseYear <= 1980) {
            return EHouseBuildYear.FIFTY_EIGHTY.getId();
        }

        if (houseYear <= 2000) {
            return EHouseBuildYear.EIGHTY_ZEROS.getId();
        }

        if (houseYear <= 2010) {
            return EHouseBuildYear.ZEROS_TENTH.getId();
        }

        if (houseYear <= 2020) {
            return EHouseBuildYear.TENTH_TWENTY.getId();
        }

        return EHouseBuildYear.TWENTY_AND_LATER.getId();
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EHouseBuildYear.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EHouseBuildYear.getValues().length;
    }
}
