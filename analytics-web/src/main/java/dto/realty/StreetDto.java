package dto.realty;

import enums.realty.EStreetType;

public class StreetDto {

    private String name;

    private EStreetType streetType;

    public boolean exists() {
        return name != null && streetType != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EStreetType getStreetType() {
        return streetType;
    }

    public void setStreetType(EStreetType streetType) {
        this.streetType = streetType;
    }
}
