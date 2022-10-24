package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EHouseFloor implements ITitled {

    LOW_FLOORS(0, "Малоэтажные дома", "LOW_FLOORS"),
    FIVE_OR_LESS(1, "Пять этажей или меньше", "FIVE_OR_LESS"),
    MORE_THAN_FIVE(2, "Более пяти этажей", "MORE_THAN_FIVE");

    private static EHouseFloor[] values = values();

    private final int id;

    private final String title;

    private String name;

    EHouseFloor(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static EHouseFloor fromString (String value) {
        for (EHouseFloor val : values()) {
            if (val.name.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static EHouseFloor getByOrdinal(int ordinal) {
        for (EHouseFloor value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EHouseFloor[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
