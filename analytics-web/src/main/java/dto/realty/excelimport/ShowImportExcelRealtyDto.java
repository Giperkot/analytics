package dto.realty.excelimport;

import enums.report.EBalconParam;
import enums.report.ERealtySegment;
import enums.report.ERepairType;
import enums.report.ESimpleHouseType;

public class ShowImportExcelRealtyDto {

    private long id;

    private String address;

    private int roomsCount;

    private String realtySegment;

    private int houseFloorsCount;

    private String wallMaterial;

    private int floor;

    private double totalArea;

    private double kitchenArea;

    private String balcon;

    private int metroDistance;

    private String repairType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public String getRealtySegment() {
        return realtySegment;
    }

    public void setRealtySegment(String realtySegment) {
        this.realtySegment = realtySegment;
    }

    public int getHouseFloorsCount() {
        return houseFloorsCount;
    }

    public void setHouseFloorsCount(int houseFloorsCount) {
        this.houseFloorsCount = houseFloorsCount;
    }

    public String getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(String wallMaterial) {
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

    public String getBalcon() {
        return balcon;
    }

    public void setBalcon(String balcon) {
        this.balcon = balcon;
    }

    public int getMetroDistance() {
        return metroDistance;
    }

    public void setMetroDistance(int metroDistance) {
        this.metroDistance = metroDistance;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }
}
