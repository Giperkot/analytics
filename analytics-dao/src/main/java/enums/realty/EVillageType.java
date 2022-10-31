package enums.realty;

import java.util.regex.Pattern;

public enum EVillageType {

    UNKNOWN(0, "", "UNKNOWN", "", "UNKNOWN"),
    VILLAGE(1, "пос.", "пос", "","пос.", "поселение"),
    THE_VILLAGE(2, "д.", "д", "","д.", "деревня"),
    CITY(3, "г.", "г", "", "г.", "Зеленоград"),
    DISTRICT(4, "р-н.", "", "", "р-н.", "район");

    private static EVillageType[] values = values();

    private final int id;

    private final String fiasShortName;

    private final String canonShortName;

    private final String[] titleArr;

    private final Pattern clearRegexp;

    EVillageType(int id,  String canonShortName, String fiasShortName, String toClear, String... titleArr) {
        this.id = id;
        this.canonShortName = canonShortName;
        this.fiasShortName = fiasShortName;
        this.titleArr = titleArr;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.titleArr.length; i++) {
            if ("Зеленоград".equals(this.titleArr[i])) {
                continue;
            }

            sb.append(this.titleArr[i].replaceAll("\\.", "\\\\."));

            if (i < titleArr.length - 1) {
                sb.append("|");
            }
        }

        clearRegexp = Pattern.compile(sb.toString() + toClear);
    }

    public static EVillageType getVillageType(String street) {
        for (EVillageType streetType : values) {
            for (String shortName : streetType.titleArr) {
                if (street.contains(shortName)) {
                    return streetType;
                }
            }
        }

        return EVillageType.UNKNOWN;
    }

    public boolean containsVillageType(String address) {
        for (String streetShortname : this.titleArr) {
            if (address.contains(streetShortname)) {
                return true;
            }
        }

        return false;
    }

    public String toGeocodeStr(String villageStr) {
        switch (this) {
            case VILLAGE:
                if ("Коммунарка".equals(villageStr) || "Московский".equals(villageStr)) {
                    // todo убрать
                    return "";
                }

                return villageStr;
            default:
                return villageStr;
        }
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

    public static EVillageType[] getValues() {
        return values;
    }

    public String getCanonShortName() {
        return canonShortName;
    }
}