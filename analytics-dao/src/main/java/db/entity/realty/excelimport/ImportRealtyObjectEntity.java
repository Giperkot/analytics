package db.entity.realty.excelimport;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.report.EBalconParam;
import enums.report.ERealtySegment;
import enums.report.ERepairType;
import enums.report.ERoomsCount;
import enums.report.ESimpleHouseType;

@Entity
@Table(name = "import_realty_object", schema = "realty")
public class ImportRealtyObjectEntity extends BaseEntity {

    @Column(name = "import_excel_request_id")
    private Long importExcelRequestId;

    @Column(name = "address")
    private String address;

    @Column(name = "rooms_count")
    private ERoomsCount roomsCount;

    @Column(name = "realty_segment")
    private ERealtySegment realtySegment;

    @Column(name = "house_floors_count")
    private int houseFloorsCount;

    @Column(name = "wall_material")
    private ESimpleHouseType wallMaterial;

    @Column(name = "floor")
    private int floor;

    @Column(name = "total_area")
    private double totalArea;

    @Column(name = "kitchen_area")
    private double kitchenArea;

    @Column(name = "balcon")
    private EBalconParam balcon;

    @Column(name = "metro_distance")
    private int metroDistance;

    @Column(name = "repair_type")
    private ERepairType repairType;

    public Long getImportExcelRequestId() {
        return importExcelRequestId;
    }

    public void setImportExcelRequestId(Long importExcelRequestId) {
        this.importExcelRequestId = importExcelRequestId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ERoomsCount getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(ERoomsCount roomsCount) {
        this.roomsCount = roomsCount;
    }

    public ERealtySegment getRealtySegment() {
        return realtySegment;
    }

    public void setRealtySegment(ERealtySegment realtySegment) {
        this.realtySegment = realtySegment;
    }

    public int getHouseFloorsCount() {
        return houseFloorsCount;
    }

    public void setHouseFloorsCount(int houseFloorsCount) {
        this.houseFloorsCount = houseFloorsCount;
    }

    public ESimpleHouseType getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(ESimpleHouseType wallMaterial) {
        this.wallMaterial = wallMaterial;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public double getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public EBalconParam getBalcon() {
        return balcon;
    }

    public void setBalcon(EBalconParam balcon) {
        this.balcon = balcon;
    }

    public int getMetroDistance() {
        return metroDistance;
    }

    public void setMetroDistance(int metroDistance) {
        this.metroDistance = metroDistance;
    }

    public ERepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(ERepairType repairType) {
        this.repairType = repairType;
    }
}
