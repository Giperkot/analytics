package enums;

import db.annotations.EnumMethodDataBase;

import java.util.HashMap;
import java.util.Map;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EUnit {

    SQUARE_METER("SQUARE_METER", new String[]{"м²"}),
    METER("METER", new String[]{"м"});

    private static final EUnit[] values = values();

    private static final Map<String, EUnit> interToUnitMap = new HashMap<>();

    static {

        for (EUnit unit : values) {
            for (int i = 0; i < unit.strInterpretationArr.length; i++) {
                interToUnitMap.put(unit.strInterpretationArr[i], unit);
            }
        }

    }

    EUnit(String name, String[] strInterpretationArr) {
        this.name = name;
        this.strInterpretationArr = strInterpretationArr;

        /*for (int i = 0; i < strInterpretationArr.length; i++) {
            interToUnitMap.put(strInterpretationArr[i], this);
        }*/
    }

    private final String name;

    private String[] strInterpretationArr;

    public static EUnit fromString(String value) {
        if (value == null) {
            return null;
        }

        for (EUnit unit : values) {
            if (unit.name.equals(value)) {
                return unit;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static EUnit getUnit(String intepretation) {
        EUnit unit = interToUnitMap.get(intepretation);

        if (unit == null) {
            throw new IllegalArgumentException("Нет такой единицы измерения: " + intepretation);
        }

        return unit;
    }

    public String getName() {
        return name;
    }
}
