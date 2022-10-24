package helper.report;

import db.entity.parser.view.VDistrictNoticeEntity;
import dto.report.NoticeWrapper;
import interfaces.report.ITitled;

public interface IClassParam {

    int getOrderByValue(NoticeWrapper value);

    ITitled getValueByOrdinal(int order);

    int length();

}
