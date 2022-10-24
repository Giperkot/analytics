package helper.report;

import dto.report.NoticeWrapper;
import gnu.trove.map.hash.TIntObjectHashMap;
import interfaces.report.ITitled;

import java.util.ArrayList;
import java.util.List;

/**
 * Этот объект должен разбивать наши объявления на классы,
 * с которыми будем производить манипуляции
 */
public class ReportClassifier {

    private List<IClassParam> classParamList = new ArrayList<>();

    private int length = 1;

    private final TIntObjectHashMap<CalcCache> calcCacheMap = new TIntObjectHashMap<>();

    public void addClassParam(IClassParam classParam) {
        classParamList.add(classParam);

        length = length * classParam.length();
    }

    /**
     * Старшие разряды числа идут с конца. Это нужно чтобы быстрей брать срез по районам.
     *
     * @param noticeWrapper
     * @return
     */
    public int getNoticePosition(NoticeWrapper noticeWrapper) {

        int result = classParamList.get(classParamList.size() - 1).getOrderByValue(noticeWrapper);

        for (int i = classParamList.size() - 2; i >= 0; i--) {
            IClassParam param = classParamList.get(i);

            result = result * param.length() + param.getOrderByValue(noticeWrapper);
        }

        return result;
    }

    public List<ITitled> getNoticeInfoByPosition(int ordinal) {

        List<ITitled> titledList = new ArrayList<>();

        for (int i = 0; i < classParamList.size(); i++) {
            IClassParam param = classParamList.get(i);

            int mod = ordinal % param.length();
            ordinal = ordinal / param.length();

            titledList.add(param.getValueByOrdinal(mod));
        }

        return titledList;
    }

    public List<ITitled> getNoticeInfo(NoticeWrapper noticeWrapper) {

        List<ITitled> titledList = new ArrayList<>();

        for (int i = 0; i < classParamList.size(); i++) {
            IClassParam param = classParamList.get(i);

            ITitled titled = param.getValueByOrdinal(param.getOrderByValue(noticeWrapper));

            titledList.add(titled);
        }

        return titledList;
    }

    public void addToCalc(NoticeWrapper noticeWrapper) {
        int noticePosition = getNoticePosition(noticeWrapper);

        CalcCache calcCache = calcCacheMap.get(noticePosition);

        double sum = noticeWrapper.getNoticeSum();
        double totalSquare = noticeWrapper.getNoticeSquare();

        if (calcCache == null) {
            calcCache = new CalcCache();
            calcCache.totalSquare = totalSquare;
            calcCache.totalSum = sum;

            calcCacheMap.put(noticePosition, calcCache);
        } else {
            calcCache.totalSquare += totalSquare;
            calcCache.totalSum += sum;
        }
    }

    public String getCalcResult() {

        int firstLength = classParamList.get(0).length();
        StringBuilder titleSb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            List<ITitled> titledList = getNoticeInfoByPosition(i);
            CalcCache calcCache = calcCacheMap.get(i);

            if (calcCache == null) {
                continue;
            }

            if (i % firstLength == 0) {

                for (int j = 1; j < titledList.size(); j++) {
                    titleSb.append(titledList.get(j).getTitle());

                    if (j < titledList.size() - 1) {
                        titleSb.append("; ");
                    }
                }

                titleSb.append("\n\n");
            }

            titleSb.append(titledList.get(0).getTitle())
                .append(":\t")
                .append(calcCache.totalSum / calcCache.totalSquare)
                .append("\n");

            if ((i + 1) % firstLength == 0) {
                titleSb.append("\n\n");
            }

        }

        return titleSb.toString();
    }

    public List<IClassParam> getClassParamList() {
        return classParamList;
    }

    public void setClassParamList(List<IClassParam> classParamList) {
        this.classParamList = classParamList;
    }

    public int getLength() {
        return length;
    }

    public TIntObjectHashMap<CalcCache> getCalcCacheMap() {
        return calcCacheMap;
    }

    public static class CalcCache {
        public double totalSum;

        public double totalSquare;
    }
}
