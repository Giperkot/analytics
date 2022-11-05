package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ESimpleHouseType implements ITitled {
    UNKNOWN(0, "UNKNOWN", "Неизвестно"),
    BRICK(1, "BRICK", "Кирпичн"),
    MONOLIT(2, "MONOLIT", "Монолит)"),
    PANEL(3, "PANEL", "Панель");

    private static ESimpleHouseType[] values = values();

    private final int id;

    private final String name;

    private final String title;

    ESimpleHouseType(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static ESimpleHouseType fromString(String value) {
        for (ESimpleHouseType val : values) {
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

    public static ESimpleHouseType getByOrdinal(int ordinal) {
        for (ESimpleHouseType value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static ESimpleHouseType getByTitle(String value) {

        String lower = value.toLowerCase().strip();

        for (ESimpleHouseType val : values) {
            if (val.title.toLowerCase().equals(lower)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение столбца Материал стен (" + value + ") не найдено. " +
                "Ожидается одно из значений: " + getTitlesEnum());
    }

    public static ESimpleHouseType[] getValues() {
        return values;
    }

    public static String getTitlesEnum() {
        String titles = "";
        for(ESimpleHouseType value: getValues()) {
            titles += value.getTitle() + ", ";
        }
        return titles.substring(0, titles.length() - 2);
    }
}
