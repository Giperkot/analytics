package enums.realty;

public enum EStreetType {

    STREET(1, "ул", "ул.", "улица", "аллея"),
    AVENUE(2, "пр-кт", "пр-т", "проспект"),
    LANE(3, "пер", "пер.", "переулок"),
    DIRECTIONS(4, "пр-д", "проезд", "пр."),
    BOULEVARD(5, "б-р", "б-р", "бульвар"),
    HIGHWAY(6, "ш", "ш.", "шоссе"),
    VILLAGE(7, "пос", "пос."),
    // Да, и такое бывает
    CITY(8, "г", "г.", "город", "Зеленоград"),
    MICRODISTRICT(9, "мкр.", "мкр.", "микрорайон"),
    SEAFRONT(10, "наб", "наб.", "набережная");

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

    public static String createStringRegexp() {
        StringBuilder sb = new StringBuilder();

        for (EStreetType streetType : values) {
            for (String shortName : streetType.titleArr) {
                sb.append(shortName.replaceAll("\\.", "\\\\.") + "|");
            }
        }

        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
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
