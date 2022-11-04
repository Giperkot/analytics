package enums.report;

import interfaces.report.ITitled;

public enum EKitchenArea implements ITitled {

    UNKNOWN(0, "UNKNOWN", ""),
    LESS7(1, "LESS7", "<7"),
    FROM7TO10(2, "FROM30TO50", "7-10"),
    FROM10TO15(3, "FROM50TO65", "10-15"),
    MORE15(6, "MORE120", ">15");

    private static EKitchenArea[] values = values();

    private final int id;

    private final String name;

    private final String title;

    EKitchenArea(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static EKitchenArea fromString (String value) {
        for (EKitchenArea val : values) {
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

    public static EKitchenArea getByTitle(String value) {

        for (EKitchenArea val : values) {
            if (val.title.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static EKitchenArea getByOrdinal(int ordinal) {
        for (EKitchenArea value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EKitchenArea getByArea(double kitchenArea) {
        if (kitchenArea < 7) {
            return EKitchenArea.LESS7;
        }

        if (kitchenArea >= 7 && kitchenArea < 10) {
            return EKitchenArea.FROM7TO10;
        }

        if (kitchenArea >= 10 && kitchenArea < 15) {
            return EKitchenArea.FROM10TO15;
        }

        return EKitchenArea.MORE15;
    }

    public static EKitchenArea[] getValues() {
        return values;
    }

}
