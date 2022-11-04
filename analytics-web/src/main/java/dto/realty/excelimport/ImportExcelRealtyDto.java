package dto.realty.excelimport;

import enums.report.EBalconParam;
import enums.report.ERealtySegment;
import enums.report.ERepairType;
import enums.report.ESimpleHouseType;

public class ImportExcelRealtyDto {

    private long id;

    private String address;

    private String roomsCount;

    /**
     * Новостройка, современное жилье, старый жилой фонд
     */
    private ERealtySegment realtySegment;

    private int houseFloorsCount;

    /**
     * Материал стен (Кипич, панель, монолит)
     *
     * @return
     */
    private ESimpleHouseType wallMaterial;

    private int floor;

    private double totalArea;

    private double kitchenArea;

    private EBalconParam balcon;

    private int metroDistance;

    private ERepairType repairType;

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

    public String getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(String roomsCount) {
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
