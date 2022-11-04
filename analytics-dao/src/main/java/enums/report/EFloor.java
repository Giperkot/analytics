package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EFloor implements ITitled {

    FIRST_OR_LAST(0, "Первый или последний этаж", "FIRST_OR_LAST"),
    AVERAGE(1, "Средние этажи", "AVERAGE");

    private static EFloor[] values = values();

    private int id;

    private String title;

    private String name;

    EFloor(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static EFloor fromString (String value) {
        for (EFloor val : values()) {
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

    public static EFloor getByOrdinal(int ordinal) {
        for (EFloor value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EFloor getByFloorAndHouseFloor(int floor, int houseFloor) {
        if (floor == houseFloor || floor == 1) {
            return EFloor.FIRST_OR_LAST;
        }

        return EFloor.AVERAGE;
    }

    public static EFloor[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
