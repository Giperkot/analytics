package enums.realty;

import java.util.regex.Pattern;

public enum EStreetType {

    UNKNOWN(0, "", "UNKNOWN", "", "UNKNOWN"),
    STREET(1, "ул.", "ул", "","ул.", "улица"),
    AVENUE(2, "пр-т", "пр-кт", "","пр-т", "проспект"),
    LANE(3, "пер.", "пер", "","пер.", "переулок"),
    DIRECTIONS(4, "пр.", "пр-д", "","проезд", "пр."),
    BOULEVARD(5, "б-р", "б-р", "","б-р", "бульвар"),
    HIGHWAY(6, "ш.", "ш", "","ш.", "шоссе"),
    MICRODISTRICT(7, "мкр.", "мкр.", "","мкр.", "микрорайон"),
    SEAFRONT(8, "наб.", "наб", "","наб.", "набережная"),
    QUARTER(9, "кв-л", "кв-л", "|№","квартал", "кв-л"),
    DEAD_END(10, "туп.", "туп", "","тупик", "туп."),
    ALLEY(11, "ал.", "аллея", "", "аллея", "ал."),
    RESIDENT_COMPLEX(12, "жк.", "", "", "жилой комплекс", "жк.", "ЖК"),
    SNT(12, "снт.", "", "", "садоводческое некоммерческое товарищество", "снт."),
    LINE(13, "лин.", "", "", "линия", "лин."),
    SQUARE(14, "пл.", "", "", "пл.", "площадь");

    private static EStreetType[] values = values();

    private final int id;

    private final String fiasShortName;

    private final String canonShortName;

    private final String[] titleArr;

    private final Pattern clearRegexp;

    EStreetType(int id, String canonShortName, String fiasShortName, String toClear, String... titleArr) {
        this.id = id;
        this.canonShortName = canonShortName;
        this.fiasShortName = fiasShortName;
        this.titleArr = titleArr;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.titleArr.length; i++) {
            sb.append(this.titleArr[i].replaceAll("\\.", "\\\\."));

            if (i < titleArr.length - 1) {
                sb.append("|");
            }
        }

        clearRegexp = Pattern.compile(sb.toString() + toClear);
    }

    public static EStreetType getStreetType(String street) {
        for (EStreetType streetType : values) {
            for (String shortName : streetType.titleArr) {
                if (street.contains(shortName)) {
                    return streetType;
                }
            }
        }

        return EStreetType.UNKNOWN;
    }

    public String toGeocodeStr(String streetStr) {
        switch (this) {
            case QUARTER:
                return "квартал " + streetStr;
            default:
                return streetStr;
        }
    }

    public boolean containsStreetType(String address) {
        for (String streetShortname : this.titleArr) {
            if (address.contains(streetShortname)) {
                return true;
            }
        }

        return false;
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

    public Pattern getClearRegexp() {
        return clearRegexp;
    }

    public String getCanonShortName() {
        return canonShortName;
    }
}
