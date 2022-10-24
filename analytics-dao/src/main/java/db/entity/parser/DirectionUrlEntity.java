package db.entity.parser;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.EParserType;

import java.util.Date;

@Entity
@Table(name = "direction_url", schema = "parser")
public class DirectionUrlEntity extends BaseEntity {

    @Column(name = "type")
    private EParserType type;

    @Column(name = "direction_id")
    private long directionId;

    @Column(name = "url")
    private String url;

    public EParserType getType() {
        return type;
    }

    public void setType(EParserType type) {
        this.type = type;
    }

    public long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(long directionId) {
        this.directionId = directionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
