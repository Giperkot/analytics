package dto.realty;

public class CityCoords {

    private DistrictCoords[] territory;

    private CoordPoint center;

    public DistrictCoords[] getTerritory() {
        return territory;
    }

    public void setTerritory(DistrictCoords[] territory) {
        this.territory = territory;
    }

    public CoordPoint getCenter() {
        return center;
    }

    public void setCenter(CoordPoint center) {
        this.center = center;
    }
}
