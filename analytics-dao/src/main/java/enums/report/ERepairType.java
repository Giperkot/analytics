package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERepairType implements ITitled {
    UNKNOWN(0, "UNKNOWN", "Неизвестно"),
    NONE(1, "NONE", "Без отделки"),
    BAD(2, "BAD", "Муниципальный ремонт"),
    GOOD(3, "GOOD", "Современная отделка");

    private static ERepairType[] values = values();

    private final int id;

    private final String name;

    private final String title;

    ERepairType(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static ERepairType fromString(String value) {
        for (ERepairType val : values) {
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

    public static ERepairType getByOrdinal(int ordinal) {
        for (ERepairType value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static ERepairType getByTitle(String value) {

        String lower = value.toLowerCase().strip();

        for (ERepairType val : values) {
            if (val.title.toLowerCase().equals(lower)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение столбца Состояние (" + value + ") не найдено. " +
                "Ожидается одно из значений: " + getTitlesEnum());
    }

    public static ERepairType getByTitle(String value, int lineNumber) {
        try {
            return getByTitle(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Значение столбца Состояние в строке " + lineNumber
                    + " (" + value + ") не найдено. " + "Ожидается одно из значений: " + getTitlesEnum());
        }
    }

    public static ERepairType[] getValues() {
        return values;
    }

    public static String getTitlesEnum() {
        String titles = "";
        for(ERepairType value: getValues()) {
            titles += value.getTitle() + ", ";
        }
        return titles.substring(0, titles.length() - 2);
    }
}
