package enums.report;

import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERealtyParam {

    FLOOR(1, "FLOOR"),
    TOTAL_SQUARE(2, "TOTAL_SQUARE"),
    BALCON(3, "BALCON"),
    REPAIR_TYPE(4, "REPAIR_TYPE"),
    KITCHEN_SQUARE(5, "KITCHEN_SQUARE"),
    METRO_DISTANCE(6, "METRO_DISTANCE"),
    TRADE(7, "TRADE");

    private static ERealtyParam[] values = values();

    private final int id;

    private final String name;

    ERealtyParam(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ERealtyParam fromString(String value) {
        for (ERealtyParam val : values) {
            if (val.name.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
