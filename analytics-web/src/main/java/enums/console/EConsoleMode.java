package enums.console;

public enum EConsoleMode {

    UNKNOWN(0, "UNKNOWN"),
    TWO_GIS_BY_ID(1, "TWO_GIS_BY_ID");

    private final static EConsoleMode[] VALUES = values();

    private int id;

    private String name;

    EConsoleMode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EConsoleMode getByString (String title) {
        for (EConsoleMode mode : VALUES) {
            if (mode.name.equals(title)) {
                return mode;
            }
        }

        return EConsoleMode.UNKNOWN;
    }
}
