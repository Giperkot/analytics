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
}
