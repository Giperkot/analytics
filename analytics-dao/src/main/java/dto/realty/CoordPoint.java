package dto.realty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordPoint {

    /**
     * Долгота
     */
    @JsonProperty("lng")
    private double longitude;

    /**
     * Широта
     */
    @JsonProperty("lat")
    private double latitude;

    public CoordPoint() {
    }

    public CoordPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static double lineLength(CoordPoint point1, CoordPoint point2) {
        return Math.hypot(point2.getLongitude() - point1.getLongitude(), point2.getLatitude() - point1.getLatitude());
    }

    public static double getTriangleSquare(CoordPoint first, CoordPoint second, CoordPoint third) {
        double a = lineLength(first, second);
        double b = lineLength(first, third);
        double c = lineLength(second, third);
        double p = (a + b + c) / 2;

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public boolean isNorthWested(CoordPoint coordPoint) {
        if (this.longitude <= coordPoint.longitude && this.latitude >= coordPoint.latitude) {
            return true;
        }

        return false;
    }

    public boolean isSouthernEastern(CoordPoint coordPoint) {
        if (this.longitude >= coordPoint.longitude && this.latitude <= coordPoint.latitude) {
            return true;
        }

        return false;
    }

    public boolean inTriangle(CoordPoint first, CoordPoint second, CoordPoint third) {
        double originSquare = getTriangleSquare(first, second, third);
        double firstSquare = getTriangleSquare(this, second, third);
        double secondSquare = getTriangleSquare(first, this, third);
        double thirdSquare = getTriangleSquare(first, second, this);

        return Math.abs(originSquare - firstSquare - secondSquare - thirdSquare) < 0.0000000001;
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
