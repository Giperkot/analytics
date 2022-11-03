package enums.report;

import interfaces.report.ITitled;

public enum ETotalArea implements ITitled {

    UNKNOWN(0, "UNKNOWN", ""),
    LESS30(1, "LESS30", "<30"),
    FROM30TO50(2, "FROM30TO50", "30-50"),
    FROM50TO65(3, "FROM50TO65", "50-65"),
    FROM65TO90(4, "FROM65TO90", "65-90"),
    FROM90TO120(5, "FROM90TO120", "90-120"),
    MORE120(6, "MORE120", ">120");

    private static ETotalArea[] values = values();

    private final int id;

    private final String name;

    private final String title;

    ETotalArea(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static ETotalArea fromString (String value) {
        for (ETotalArea val : values) {
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

    public static ETotalArea getByTitle(String value) {

        for (ETotalArea val : values) {
            if (val.title.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static ETotalArea getByOrdinal(int ordinal) {
        for (ETotalArea value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static ETotalArea[] getValues() {
        return values;
    }
}
