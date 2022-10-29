package enums.realty;

public enum EStreetType {

    STREET(1, "ул", "ул.", "улица"),
    AVENUE(2, "пр-кт", "пр-т"),
    LANE(3, "пер", "пер."),
    DIRECTIONS(4, "проезд", "проезд"),
    BOULEVARD(5, "б-р", "б-р"),
    HIGHWAY(6, "ш", "ш."),
    VILLAGE(7, "пос", "пос.");

    private static EStreetType[] values = values();

    private final int id;

    private final String fiasShortName;

    private final String[] titleArr;

    EStreetType(int id, String fiasShortName, String... titleArr) {
        this.id = id;
        this.fiasShortName = fiasShortName;
        this.titleArr = titleArr;
    }

    public static EStreetType getStreetType(String street) {
        for (EStreetType streetType : values) {
            for (String shortName : streetType.titleArr) {
                if (street.contains(shortName)) {
                    return streetType;
                }
            }
        }

        throw new IllegalArgumentException("Тип улицы не найден.");
    }

    public int getId() {
        return id;
    }

    public String[] getTitleArr() {
        return titleArr;
    }

    public String getFiasShortName() {
        return fiasShortName;
    }
}
