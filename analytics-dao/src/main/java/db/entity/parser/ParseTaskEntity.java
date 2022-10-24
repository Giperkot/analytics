package db.entity.parser;


import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Id;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.EParserStatus;
import enums.EParserType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "parse_task", schema = "parser")
public class ParseTaskEntity extends BaseEntity {

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "type")
    private EParserType type;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private EParserStatus status;
    
    @Column(name = "direction_url_id")
    private long directionUrlId;

    public EParserType getType() {
        return type;
    }

    public void setType(EParserType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EParserStatus getStatus() {
        return status;
    }

    public void setStatus(EParserStatus status) {
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public long getDirectionUrlId() {
        return directionUrlId;
    }

    public void setDirectionUrlId(long directionUrlId) {
        this.directionUrlId = directionUrlId;
    }

    @Override
    public String toString() {
        return "ParseTask{" +
                "id=" + id + '\'' +
                "type=" + type + '\'' +
                "url=" + url + '\'' +
                "title=" + title + '\'' +
                "status=" + status + '\'' +
                '}';
    }
}
