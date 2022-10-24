package db.entity.parser;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.EDirectionName;

import java.util.Date;

@Entity
@Table(name = "direction", schema = "parser")
public class DirectionEntity extends BaseEntity {

    @Column(name = "name")
    private EDirectionName name;

    @Column(name = "title")
    private String title;

    public EDirectionName getName() {
        return name;
    }

    public void setName(EDirectionName name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DirectionEntity{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "title=" + title + '\'' +
                '}';
    }
}
