package db.entity.parser.view;

import db.annotations.Column;
import db.entity.parser.FeatureEntity;
import enums.EUnit;

public class VFeatureValueEntity extends FeatureEntity {

    @Column(name = "notice_id")
    private long noticeId;

    @Column(name = "value")
    private String value;

    @Column(name = "feature_to_notice_id")
    private long featureToNoticeId;

    @Column(name = "units")
    private EUnit units;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getFeatureToNoticeId() {
        return featureToNoticeId;
    }

    public void setFeatureToNoticeId(long featureToNoticeId) {
        this.featureToNoticeId = featureToNoticeId;
    }

    public EUnit getUnits() {
        return units;
    }

    public void setUnits(EUnit units) {
        this.units = units;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }
}
