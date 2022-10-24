package enums;

import com.fasterxml.jackson.annotation.JsonValue;
import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EDirectionName {

    REALTY_SALE("REALTY_SALE");

    @JsonValue
    private String name;

    EDirectionName(String name) {
        this.name = name;
    }

    public static EDirectionName fromString (String value) {
        for (EDirectionName val : values()) {
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
