package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;

@Entity
@Table(schema = "realty", name = "excel_data_temp")
public class ExcelDataTempEntity extends BaseEntity {
    @Column(name = "location")
    private String location;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "building_type")
    private String buildingType;

    @Column(name = "floors_in_building")
    private Integer floorsInBuilding;

    @Column(name = "walls_material")
    private String wallsMaterial;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "apartment_area")
    private Float apartmentArea;

    @Column(name = "kitchen_area")
    private Float kitchenArea;

    @Column(name = "balcony")
    private Boolean balcony;

    @Column(name = "distance_to_metro")
    private Integer distanceToMetro;

    @Column(name = "condition")
    private String condition;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getFloorsInBuilding() {
        return floorsInBuilding;
    }

    public void setFloorsInBuilding(Integer floorsInBuilding) {
        this.floorsInBuilding = floorsInBuilding;
    }

    public String getWallsMaterial() {
        return wallsMaterial;
    }

    public void setWallsMaterial(String wallsMaterial) {
        this.wallsMaterial = wallsMaterial;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Float getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(Float apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public Float getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(Float kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Integer getDistanceToMetro() {
        return distanceToMetro;
    }

    public void setDistanceToMetro(Integer distanceToMetro) {
        this.distanceToMetro = distanceToMetro;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
