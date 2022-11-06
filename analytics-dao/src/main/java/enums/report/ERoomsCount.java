package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERoomsCount implements ITitled {

    ONE(0, "1-к квартира", "ONE"),
    TWO(1, "2-к квартира", "TWO"),
    THREE(2, "3-к квартира", "THREE"),
    FOUR(3, "4-к квартира", "FOUR"),
    FIVE(4, "5-к квартира", "FIVE"),
    MORE_THAN_FIVE(5, "более 5-к квартира", "MORE_THAN_FIVE"),
    FREE_LAYOUT(6, "Свободная планировка", "FREE_LAYOUT"),
    STUDIO(7, "Студия", "STUDIO"),
    TEN_AND_MORE(8, "10-к и больше", "TEN_AND_MORE");

    private static ERoomsCount[] values = values();

    private final int id;

    private final String title;

    private final String name;

    ERoomsCount(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static ERoomsCount fromString (String value) {
        for (ERoomsCount val : values()) {
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

    public static ERoomsCount getByOrdinal(int ordinal) {
        for (ERoomsCount value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id: " + ordinal);
    }

    public static ERoomsCount getByRoomsCount(String roomsCount) {
        String lowerRoomsCount = roomsCount.toLowerCase();

        if ("студия".equals(lowerRoomsCount)) {
            return ERoomsCount.STUDIO;
        }

        if ("свободная планировка".equals(lowerRoomsCount)) {
            return ERoomsCount.FREE_LAYOUT;
        }

        if ("10 и больше".equals(lowerRoomsCount)) {
            return ERoomsCount.TEN_AND_MORE;
        }

        int count = Integer.parseInt(lowerRoomsCount);

        if (count == 1) {
            return ERoomsCount.ONE;
        }

        if (count == 2) {
            return ERoomsCount.TWO;
        }

        if (count == 3) {
            return ERoomsCount.THREE;
        }

        if (count == 4) {
            return ERoomsCount.FOUR;
        }

        if (count == 5) {
            return ERoomsCount.FIVE;
        }

        if (count > 5) {
            return ERoomsCount.MORE_THAN_FIVE;
        }

        throw new RuntimeException("некорректное значение количества комнат: " + count);
    }

    public static ERoomsCount[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    public static ERoomsCount getByTitle(String value, int lineNumber) {
        try {
            return getByRoomsCount(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Значение столбца Количество комнат в строке " + lineNumber
                    + " (" + value + ") не найдено. " + "Ожидается численное значение, \"Свободная планировка\" или \"Студия\"");
        }
    }
}
