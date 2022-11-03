package helper.report;

import dto.report.NoticeWrapper;
import interfaces.report.ITitled;

public interface IClassParam {

    int getOrderByValue(NoticeWrapper value);

    ITitled getValueByOrdinal(int ordinal);

    int length();

}
