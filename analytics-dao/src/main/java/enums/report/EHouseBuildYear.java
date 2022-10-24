package enums.report;

import db.annotations.EnumMethodDataBase;
import interfaces.report.ITitled;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EHouseBuildYear implements ITitled {

    FIFTY_AND_EARLIER(0, "FIFTY_AND_EARLIER", "FIFTY_AND_EARLIER"),
    FIFTY_EIGHTY(1, "FIFTY_EIGHTY", "FIFTY_EIGHTY"),
    EIGHTY_ZEROS(2, "EIGHTY_ZEROS", "EIGHTY_ZEROS"),
    ZEROS_TENTH(3, "ZEROS_TENTH", "ZEROS_TENTH"),
    TENTH_TWENTY(4, "TENTH_TWENTY", "TENTH_TWENTY"),
    TWENTY_AND_LATER(5, "TWENTY_AND_LATER", "TWENTY_AND_LATER");

    private static EHouseBuildYear[] values = values();

    private int id;

    private String title;

    private String name;

    EHouseBuildYear(int id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public static EHouseBuildYear fromString (String value) {
        for (EHouseBuildYear val : values()) {
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

    public static EHouseBuildYear getByOrdinal(int ordinal) {
        for (EHouseBuildYear value : values) {
            if (value.id == ordinal) {
                return value;
            }
        }

        throw new RuntimeException("Нет такого id");
    }

    public static EHouseBuildYear[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
