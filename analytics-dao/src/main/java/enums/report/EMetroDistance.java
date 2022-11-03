package enums.report;

import interfaces.report.ITitled;

public enum EMetroDistance implements ITitled {

    UNKNOWN(0, "UNKNOWN", ""),
    LESS5(1, "LESS7", "<5"),
    FROM5TO10(2, "FROM5TO10", "5-10"),
    FROM10TO15(3, "FROM10TO15", "10-15"),
    FROM15TO30(4, "FROM15TO30", "15-30"),
    FROM30TO60(5, "FROM30TO60", "30-60"),
    FROM60TO90(6, "FROM60TO90", "60-90");

    private static EMetroDistance[] values = values();

    private final int id;

    private final String name;

    private final String title;

    EMetroDistance(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static EMetroDistance fromString (String value) {
        for (EMetroDistance val : values) {
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

    public static EMetroDistance getByTitle(String value) {

        for (EMetroDistance val : values) {
            if (val.title.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static EMetroDistance getByOrdinal(int ordinal) {
        for (EMetroDistance value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EMetroDistance[] getValues() {
        return values;
    }

}
