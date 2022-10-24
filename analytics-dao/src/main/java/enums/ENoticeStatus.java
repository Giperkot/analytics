package enums;

import com.fasterxml.jackson.annotation.JsonValue;
import db.annotations.EnumMethodDataBase;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ENoticeStatus {

    ACTIVE(1, "ACTIVE"),
    REMOVED_FROM_PUBLICATION(2, "REMOVED_FROM_PUBLICATION"),
    REOPENED(3, "REOPENED"),
    DELETED_301(4, "DELETED_301"),
    DELETED(5, "DELETED");

    private final int id;

    @JsonValue
    private final String name;

    ENoticeStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ENoticeStatus fromString (String value) {
        for (ENoticeStatus status : values()) {
            if (status.name.equals(value)) {
                return status;
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
