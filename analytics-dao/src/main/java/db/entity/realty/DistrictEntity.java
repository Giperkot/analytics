package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import dto.realty.DistrictCoords;

@Entity
@Table(name = "district", schema = "realty")
public class DistrictEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "coords", sqlType = "jsonb")
    private DistrictCoords[] coords;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "parent_district_id")
    private Long parentDistrictId;

    @Column(name = "parents")
    private String parents;

    @Column(name = "level")
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DistrictCoords[] getCoords() {
        return coords;
    }

    public void setCoords(DistrictCoords[] coords) {
        this.coords = coords;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getParentDistrictId() {
        return parentDistrictId;
    }

    public void setParentDistrictId(Long parentDistrictId) {
        this.parentDistrictId = parentDistrictId;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
