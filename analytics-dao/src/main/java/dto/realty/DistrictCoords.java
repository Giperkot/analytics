package dto.realty;

public class DistrictCoords extends HouseCoords {

    private CoordPoint[] poligonBounds;

    public CoordPoint[] getPoligonBounds() {
        return poligonBounds;
    }

    public void setPoligonBounds(CoordPoint[] poligonBounds) {
        this.poligonBounds = poligonBounds;
    }
}
