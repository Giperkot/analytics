package db.entity.parser.view;

import db.annotations.Column;
import db.entity.parser.NoticeEntity;

public class VDistrictNoticeEntity extends NoticeEntity {

    @Column(name = "district_id")
    private long districtId;

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }
}
