package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import dto.realty.HouseCoords;

import java.util.Date;

@Entity
@Table(name = "house", schema = "realty")
public class HouseEntity extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "house_num")
    private String houseNum;

    @Column(name = "district_id")
    private long districtId;

    @Column(name = "city_id")
    private long cityId;

    @Column(name = "coords", sqlType = "jsonb")
    private HouseCoords coords;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public HouseCoords getCoords() {
        return coords;
    }

    public void setCoords(HouseCoords coords) {
        this.coords = coords;
    }
}
