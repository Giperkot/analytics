package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import dto.realty.CityCoords;

import java.util.Date;

@Entity
@Table(name = "city", schema = "realty")
public class CityEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "coords", sqlType = "jsonb")
    private CityCoords coords;

    @Column(name = "fias_id")
    private long fiasId;

    @Column(name = "lat")
    private double latitude;

    @Column(name = "lng")
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityCoords getCoords() {
        return coords;
    }

    public void setCoords(CityCoords coords) {
        this.coords = coords;
    }

    public long getFiasId() {
        return fiasId;
    }

    public void setFiasId(long fiasId) {
        this.fiasId = fiasId;
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
