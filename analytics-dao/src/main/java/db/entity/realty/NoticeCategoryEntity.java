package db.entity.realty;


import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.report.*;

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

    @Column(name = "classifier_category")
    private ERealtyConfigType realtyConfigType;

    @Column(name = "realty_segment")
    private ERealtySegment realtySegment;

    @Column(name = "repair_type")
    private ERepairType repairType;

    @Column(name = "simple_house_type")
    private ESimpleHouseType simpleHouseType;

    @Column(name = "total_square")
    private ETotalArea totalArea;

    @Column(name = "kitchen_square")
    private EKitchenArea kitchenArea;

    @Column(name = "metro_distance")
    private EMetroDistance metroDistance;

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

    public ERealtyConfigType getRealtyConfigType() {
        return realtyConfigType;
    }

    public void setRealtyConfigType(ERealtyConfigType realtyConfigType) {
        this.realtyConfigType = realtyConfigType;
    }

    public ERealtySegment getRealtySegment() {
        return realtySegment;
    }

    public void setRealtySegment(ERealtySegment realtySegment) {
        this.realtySegment = realtySegment;
    }

    public ERepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(ERepairType repairType) {
        this.repairType = repairType;
    }

    public ESimpleHouseType getSimpleHouseType() {
        return simpleHouseType;
    }

    public void setSimpleHouseType(ESimpleHouseType simpleHouseType) {
        this.simpleHouseType = simpleHouseType;
    }

    public ETotalArea getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(ETotalArea totalArea) {
        this.totalArea = totalArea;
    }

    public EKitchenArea getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(EKitchenArea kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public EMetroDistance getMetroDistance() {
        return metroDistance;
    }

    public void setMetroDistance(EMetroDistance metroDistance) {
        this.metroDistance = metroDistance;
    }
}
