package dto.realty;

import enums.report.EBalconParam;
import enums.report.EFloor;
import enums.report.EHouseBuildYear;
import enums.report.EHouseFloor;
import enums.report.EHouseType;
import enums.report.ERoomsCount;

public class NoticeInfoFilter {

    private long[] districtIdArr;

    private ERoomsCount roomsCount;

    private EFloor floor;

    private EHouseFloor houseFloor;

    private EHouseType houseType;

    private EHouseBuildYear houseBuildYear;

    private EBalconParam balcon;

    public long[] getDistrictIdArr() {
        return districtIdArr;
    }

    public void setDistrictIdArr(long[] districtIdArr) {
        this.districtIdArr = districtIdArr;
    }

    public ERoomsCount getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(ERoomsCount roomsCount) {
        this.roomsCount = roomsCount;
    }

    public EFloor getFloor() {
        return floor;
    }

    public void setFloor(EFloor floor) {
        this.floor = floor;
    }

    public EHouseFloor getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(EHouseFloor houseFloor) {
        this.houseFloor = houseFloor;
    }

    public EHouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(EHouseType houseType) {
        this.houseType = houseType;
    }

    public EHouseBuildYear getHouseBuildYear() {
        return houseBuildYear;
    }

    public void setHouseBuildYear(EHouseBuildYear houseBuildYear) {
        this.houseBuildYear = houseBuildYear;
    }

    public EBalconParam getBalcon() {
        return balcon;
    }

    public void setBalcon(EBalconParam balcon) {
        this.balcon = balcon;
    }
}
