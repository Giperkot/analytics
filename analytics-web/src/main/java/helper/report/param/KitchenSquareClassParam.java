package helper.report.param;

import dto.report.NoticeWrapper;
import helper.report.IClassParam;
import interfaces.report.ITitled;

public class KitchenSquareClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {
        return 0;
    }

    @Override
    public ITitled getValueByOrdinal(int order) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }
}
