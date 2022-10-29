package dto.realty;

import enums.realty.EStreetType;

public class HouseDto {

    private String street;

    private String houseNum;

    private EStreetType streetType;

    public HouseDto(String street, EStreetType streetType, String houseNum) {
        this.street = street;
        this.streetType = streetType;
        this.houseNum = houseNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public EStreetType getStreetType() {
        return streetType;
    }

    public void setStreetType(EStreetType streetType) {
        this.streetType = streetType;
    }
}
