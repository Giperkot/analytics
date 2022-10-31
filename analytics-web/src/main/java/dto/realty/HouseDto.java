package dto.realty;

import enums.realty.EStreetType;

public class HouseDto {

    private VillageDto village;

    private StreetDto street;

    private String houseNum;

    public HouseDto(VillageDto village, StreetDto street, String houseNum) {
        this.village = village;
        this.street = street;
        this.houseNum = houseNum;
    }

    public String printStreetAddr() {
        StringBuilder sb = new StringBuilder();
        boolean streetExists = street.exists();

        if (village.exists()) {
            sb.append(village.getVillageType().getCanonShortName() + " " + village.getName());

            if (streetExists) {
                sb.append(", ");
            }
        }

        if (streetExists) {
            sb.append(street.getStreetType().getCanonShortName() + " " + street.getName());
        }

        return sb.toString();
    }

    public String printFullAddr() {
        return printStreetAddr() + ", " + houseNum;
    }

    public VillageDto getVillage() {
        return village;
    }

    public void setVillage(VillageDto village) {
        this.village = village;
    }

    public StreetDto getStreet() {
        return street;
    }

    public void setStreet(StreetDto street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }
}
