package enums;

import com.fasterxml.jackson.annotation.JsonValue;
import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EParserType {

    AVITO(1, "AVITO"),
    CIAN(2, "CIAN"),
    N1(3, "N1"),
    DOM_CLICK(4, "DOM_CLICK");

    private final int id;

    @JsonValue
    private final String name;

    EParserType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EParserType fromString (String value) {
        for (EParserType ticketType : values()) {
            if (ticketType.name.equals(value)) {
                return ticketType;
            }
        }

        throw new RuntimeException("Значение не найдено " + value);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
