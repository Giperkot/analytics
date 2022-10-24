package dto.temp;

import dto.realty.CoordPoint;

public class TempPoint {

    private double longitude;

    private double latitude;

    public CoordPoint toCoordPoint() {
        return new CoordPoint(longitude, latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
