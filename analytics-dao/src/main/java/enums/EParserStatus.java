package enums;

import com.fasterxml.jackson.annotation.JsonValue;
import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum EParserStatus {

    NOT_PARSED(1, "NOT_PARSED"),
    IN_PROGRESS(2, "IN_PROGRESS"),
    COMPLETED(3, "COMPLETED"),
    ERROR(4, "ERROR");

    private int id;

    @JsonValue
    private String name;

    EParserStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EParserStatus fromString (String value) {
        for (EParserStatus ticketType : values()) {
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
