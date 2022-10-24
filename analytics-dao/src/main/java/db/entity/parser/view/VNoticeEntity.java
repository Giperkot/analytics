package db.entity.parser.view;

import db.annotations.Column;
import db.entity.parser.NoticeEntity;

public class VNoticeEntity extends NoticeEntity {

    @Column(name = "city_id")
    private long cityId;

    @Column(name = "city_name")
    private String cityName;

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
