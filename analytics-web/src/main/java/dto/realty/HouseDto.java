package dto.realty;

public class HouseDto {

    private String street;

    private String houseNum;

    public HouseDto(String street, String houseNum) {
        this.street = street;
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
}
