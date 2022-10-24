package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.report.EHouseType;

@Entity
@Table(name = "house_add_info", schema = "realty")
public class HouseAddInfoEntity extends BaseEntity {

    @Column(name = "house_id")
    private Long houseId;

    @Column(name = "build_year")
    private int buildYear;

    @Column(name = "floors_count")
    private int floorsCount;

    @Column(name = "deterioration")
    private Integer deterioration;

    @Column(name = "house_type")
    private EHouseType houseType;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public Integer getDeterioration() {
        return deterioration;
    }

    public void setDeterioration(Integer deterioration) {
        this.deterioration = deterioration;
    }

    public EHouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(EHouseType houseType) {
        this.houseType = houseType;
    }
}
