package enums;

import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EFeatureExactName {

    HOUSE_YEAR("HOUSE_YEAR"),
    TOTAL_AREA("TOTAL_AREA"),
    HOUSE_FLOORS("HOUSE_FLOORS"),
    FLOOR("FLOOR"),
    BALCON_LOGGIA("BALCON_LOGGIA"),
    HOUSE_TYPE("HOUSE_TYPE"),
    ROOM_COUNT("ROOM_COUNT"),
    KITCHEN_AREA("KITCHEN_AREA"),
    LIVING_AREA("LIVING_AREA");

    private static final EFeatureExactName[] values = values();

    private final String name;

    EFeatureExactName(String name) {
        this.name = name;
    }

    public static EFeatureExactName fromString(String value) {
        if (value == null) {
            return null;
        }

        for (EFeatureExactName val : values) {
            if (val.name.equals(value)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + value);
    }

    public String getName() {
        return name;
    }
}
