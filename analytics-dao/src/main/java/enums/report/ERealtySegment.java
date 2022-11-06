package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERealtySegment implements ITitled {
    UNKNOWN(0, "UNKNOWN", "Неизвестно"),
    NEW_BUILDING(1, "NEW_BUILDING", "Новостройка"),
    MODERN_HOUSE(2, "MODERN_HOUSE", "Современное жилье"),
    OLD_HOUSE(3, "OLD_HOUSE", "Старый жилой фонд");

    private static ERealtySegment[] values = values();

    private final int id;

    private final String name;

    private final String title;

    ERealtySegment(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static ERealtySegment fromString(String value) {
        for (ERealtySegment val : values) {
            if (val.name.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public static ERealtySegment getByTitle(String value) {

        String lower = value.toLowerCase().strip();

        for (ERealtySegment val : values) {
            if (val.title.toLowerCase().equals(lower)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение столбца Сегмент (" + value + ") не найдено. " +
                "Ожидается одно из значений: " + getTitlesEnum());
    }

    public static ERealtySegment getByTitle(String value, int lineNumber) {
        try {
            return getByTitle(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Значение столбца Сегмент в строке " + lineNumber
                    + " (" + value + ") не найдено. " + "Ожидается одно из значений: " + getTitlesEnum());
        }
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

    public static ERealtySegment getByOrdinal(int ordinal) {
        for (ERealtySegment value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static ERealtySegment[] getValues() {
        return values;
    }

    public static String getTitlesEnum() {
        String titles = "";
        for(ERealtySegment value: getValues()) {
            titles += value.getTitle() + ", ";
        }
        return titles.substring(0, titles.length() - 2);
    }
}
