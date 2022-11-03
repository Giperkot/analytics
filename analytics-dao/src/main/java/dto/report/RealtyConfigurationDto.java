package dto.report;

import enums.report.EPlacement;

public class RealtyConfigurationDto {

    private EPlacement placement;

    private boolean floor;

    private boolean houseFloor;

    private boolean totalSquare;

    private boolean kitchenSquare;

    private boolean balcon;

    private boolean roomsCount;

    private boolean houseType;

    private boolean houseBuildYear;

    private boolean realtySegment;

    private boolean simpleHouseType;

    private boolean metroDistance;

    private boolean repairType;

    public EPlacement getPlacement() {
        return placement;
    }

    public void setPlacement(EPlacement placement) {
        this.placement = placement;
    }

    public boolean isFloor() {
        return floor;
    }

    public void setFloor(boolean floor) {
        this.floor = floor;
    }

    public boolean isHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(boolean houseFloor) {
        this.houseFloor = houseFloor;
    }

    public boolean isKitchenSquare() {
        return kitchenSquare;
    }

    public void setKitchenSquare(boolean kitchenSquare) {
        this.kitchenSquare = kitchenSquare;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(boolean roomsCount) {
        this.roomsCount = roomsCount;
    }

    public boolean isHouseType() {
        return houseType;
    }

    public void setHouseType(boolean houseType) {
        this.houseType = houseType;
    }

    public boolean isHouseBuildYear() {
        return houseBuildYear;
    }

    public void setHouseBuildYear(boolean houseBuildYear) {
        this.houseBuildYear = houseBuildYear;
    }

    public boolean isRealtySegment() {
        return realtySegment;
    }

    public void setRealtySegment(boolean realtySegment) {
        this.realtySegment = realtySegment;
    }

    public boolean isSimpleHouseType() {
        return simpleHouseType;
    }

    public void setSimpleHouseType(boolean simpleHouseType) {
        this.simpleHouseType = simpleHouseType;
    }

    public boolean isMetroDistance() {
        return metroDistance;
    }

    public void setMetroDistance(boolean metroDistance) {
        this.metroDistance = metroDistance;
    }

    public boolean isTotalSquare() {
        return totalSquare;
    }

    public void setTotalSquare(boolean totalSquare) {
        this.totalSquare = totalSquare;
    }

    public boolean isRepairType() {
        return repairType;
    }

    public void setRepairType(boolean repairType) {
        this.repairType = repairType;
    }
}
