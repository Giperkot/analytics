package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EBalconParam implements ITitled {

    FALSE(0, "Нет", "FALSE"),
    TRUE(1, "Да", "TRUE");

    private static EBalconParam[] values = values();

    private int id;

    private String title;

    private String name;

    EBalconParam(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static EBalconParam getByOrdinal(int ordinal) {
        for (EBalconParam value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EBalconParam fromString (String value) {
        for (EBalconParam val : values()) {
            if (val.name.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static EBalconParam getByTitle(String value) {

        String lower = value.toLowerCase().strip();

        for (EBalconParam val : values) {
            if (val.title.toLowerCase().equals(lower)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static EBalconParam[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
