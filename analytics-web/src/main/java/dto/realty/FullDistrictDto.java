package dto.realty;

public class FullDistrictDto extends DistrictDto {

    private long cityId;

    private DistrictCoords[] coords;

    public DistrictCoords[] getCoords() {
        return coords;
    }

    public void setCoords(DistrictCoords[] coords) {
        this.coords = coords;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
