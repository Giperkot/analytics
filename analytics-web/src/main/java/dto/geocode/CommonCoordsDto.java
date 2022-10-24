package dto.geocode;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.realty.BoundsDto;
import dto.realty.HouseCoords;

public class CommonCoordsDto {

    private String displayName;

    private HouseCoords houseCoords;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public HouseCoords getHouseCoords() {
        return houseCoords;
    }

    public void setHouseCoords(HouseCoords houseCoords) {
        this.houseCoords = houseCoords;
    }
}
