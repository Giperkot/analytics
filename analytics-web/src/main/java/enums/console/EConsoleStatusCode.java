package enums.console;

public enum EConsoleStatusCode {

    UNKNOWN (-1, "UNKNOWN"),
    SUCCESS_OK (0, "SUCCESS_OK"),
    ERROR_NO_RESPONSE (1, "ERROR_NO_RESPONSE"),
    ERROR_IN_JS_SCRIPT(2, "ERROR_IN_JS_SCRIPT");

    private final static EConsoleStatusCode[] VALUES = values();

    private int id;
    private String title;

    EConsoleStatusCode(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static EConsoleStatusCode getByString (String title) {
        for (EConsoleStatusCode statusCode : VALUES) {
            if (statusCode.title.equals(title)) {
                return statusCode;
            }
        }

        return EConsoleStatusCode.UNKNOWN;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
