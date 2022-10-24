package dto.report;

import enums.report.EPlacement;

public class RealtyConfigurationDto {

    private EPlacement placement;

    private boolean floor;

    private boolean houseFloor;

    private boolean kitchenSquare;

    private boolean balcon;

    private boolean roomsCount;

    private boolean houseType;

    private boolean houseBuildYear;

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
}
