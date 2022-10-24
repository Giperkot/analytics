package dto.realty;

public class HouseCoords {

    private CoordPoint center;

    private BoundsDto bound;

    public CoordPoint getCenter() {
        return center;
    }

    public void setCenter(CoordPoint center) {
        this.center = center;
    }

    public BoundsDto getBound() {
        return bound;
    }

    public void setBound(BoundsDto bound) {
        this.bound = bound;
    }
}
