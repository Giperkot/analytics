package db.entity.realty.admin;

import db.annotations.Column;
import db.entity.realty.HouseEntity;

public class AddrHouseEntity extends HouseEntity {

    @Column(name = "notice_addr")
    private String noticeAddr;

    public String getNoticeAddr() {
        return noticeAddr;
    }

    public void setNoticeAddr(String noticeAddr) {
        this.noticeAddr = noticeAddr;
    }
}
