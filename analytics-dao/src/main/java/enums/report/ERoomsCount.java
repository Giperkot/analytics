package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERoomsCount implements ITitled {

    ONE(0, "1-к квартира", "ONE"),
    TWO(1, "2-к квартира", "TWO"),
    THREE(2, "3-к квартира", "THREE"),
    FOUR(3, "4-к квартира", "FOUR"),
    FIVE(4, "5-к квартира", "FIVE"),
    MORE_THAN_FIVE(5, "более 5-к квартира", "MORE_THAN_FIVE"),
    FREE_LAYOUT(6, "Свободная планировка", "FREE_LAYOUT"),
    STUDIO(7, "Студия", "STUDIO"),
    TEN_AND_MORE(8, "10-к и больше", "TEN_AND_MORE");

    private static ERoomsCount[] values = values();

    private final int id;

    private final String title;

    private final String name;

    ERoomsCount(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static ERoomsCount fromString (String value) {
        for (ERoomsCount val : values()) {
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

    public static ERoomsCount getByOrdinal(int ordinal) {
        for (ERoomsCount value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id: " + ordinal);
    }

    public static ERoomsCount[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
