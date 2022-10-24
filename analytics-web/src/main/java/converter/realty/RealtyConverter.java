package converter.realty;

import dto.geocode.yandex.GeoObject;
import dto.realty.BoundsDto;
import dto.realty.CoordPoint;
import dto.realty.HouseCoords;
import dto.geocode.yandex.BoundedByDto;
import dto.geocode.yandex.PointDto;

public class RealtyConverter {
    private static final RealtyConverter instance = new RealtyConverter();

    public static RealtyConverter getInstance() {
        return instance;
    }

    private RealtyConverter() {
    }

    private CoordPoint toCoordPoint(String position) {
        int spacePos = position.indexOf(" ");

        if (spacePos < 0) {
            throw new RuntimeException("Непредвиденный формат координат");
        }

        double longitude = Double.parseDouble(position.substring(0, spacePos));
        double latitude = Double.parseDouble(position.substring(spacePos + 1));

        return new CoordPoint(longitude, latitude);
    }

    private BoundsDto toBoundsDto(BoundedByDto boundedBy) {
        BoundsDto bound = new BoundsDto();
        String lowerCorner = boundedBy.getEnvelope().getLowerCorner();
        String upperCorner = boundedBy.getEnvelope().getUpperCorner();

        bound.setLower(toCoordPoint(lowerCorner));
        bound.setUpper(toCoordPoint(upperCorner));

        return bound;
    }

    private CoordPoint toCoordPoint(PointDto point) {
        String position = point.getPos();

        return toCoordPoint(position);
    }

    public HouseCoords toHouseCoords(GeoObject geoObject) {
        HouseCoords result = new HouseCoords();

        PointDto point = geoObject.getPoint();
        BoundedByDto boundedBy = geoObject.getBoundedBy();

        result.setCenter(toCoordPoint(point));
        result.setBound(toBoundsDto(boundedBy));

        return result;
    }

}
