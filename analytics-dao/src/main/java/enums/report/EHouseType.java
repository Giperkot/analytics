package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EHouseType implements ITitled {
    UNKNOWN(0, "UNKNOWN", "Неизвестно"),
    BRICK(1, "BRICK", "Кирпичный"),
    MONOLIT(2, "MONOLIT", "Монолитный"),
    PANEL(3, "PANEL", "Панельный"),
    BLOCK(4, "BLOCK", "Блочный"),
    BRICK_MONOLIT(5, "BRICK_MONOLIT", "Монолитно-кирпичный"),
    WOOD(6, "WOOD", "Деревянный");

    private static EHouseType[] values = values();

    private final int id;

    private final String name;

    private final String title;

    private final String lowerTitle;

    EHouseType(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
        lowerTitle = title.toLowerCase();
    }

    public static EHouseType fromString (String value) {
        for (EHouseType val : values) {
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

    public String getName() {
        return name;
    }

    public static EHouseType getByTitle(String value) {
        String lowerValue = value.toLowerCase();

        for (EHouseType val : values) {
            if (val.lowerTitle.equals(lowerValue)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static EHouseType getByOrdinal(int ordinal) {
        for (EHouseType value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EHouseType[] getValues() {
        return values;
    }
}
