package db.entity.realty;


import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.report.EBalconParam;
import enums.report.EFloor;
import enums.report.EHouseBuildYear;
import enums.report.EHouseFloor;
import enums.report.EHouseType;
import enums.report.ERoomsCount;

@Entity
@Table(name = "notice_category", schema = "realty")
public class NoticeCategoryEntity extends BaseEntity {

    @Column(name = "notice_id")
    private long noticeId;

    @Column(name = "canon_type_number")
    private int canonTypeNumber;

    @Column(name = "rooms_count")
    private ERoomsCount roomsCount;

    @Column(name = "floor")
    private EFloor floor;

    @Column(name = "house_floor")
    private EHouseFloor houseFloor;

    @Column(name = "house_type")
    private EHouseType houseType;

    @Column(name = "house_build_year")
    private EHouseBuildYear houseBuildYear;

    @Column(name = "balcon")
    private EBalconParam balcon;

    @Column(name = "square_value")
    private double squareValue;

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
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

    public int getCanonTypeNumber() {
        return canonTypeNumber;
    }

    public void setCanonTypeNumber(int canonTypeNumber) {
        this.canonTypeNumber = canonTypeNumber;
    }

    public double getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(double squareValue) {
        this.squareValue = squareValue;
    }
}
