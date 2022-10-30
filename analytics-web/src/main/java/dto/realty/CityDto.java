package dto.realty;

import db.entity.realty.CityEntity;

public class CityDto {

    private long id;

    private String name;

    private double latitude;

    private double longitude;

    public CityDto() {
    }

    public CityDto(CityEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        longitude = entity.getLongitude();
        latitude = entity.getLatitude();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
