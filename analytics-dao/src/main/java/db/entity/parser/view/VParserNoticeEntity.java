package db.entity.parser.view;

import db.annotations.Column;
import db.entity.parser.NoticeEntity;

public class VParserNoticeEntity extends NoticeEntity {

    @Column(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
