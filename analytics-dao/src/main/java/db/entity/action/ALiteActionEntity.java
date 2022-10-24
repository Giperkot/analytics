package db.entity.action;

import db.annotations.Column;
import db.entity.BaseEntity;
import db.entity.DiffAction;

public class ALiteActionEntity extends BaseEntity {

    @Column(name = "field")
    private String field;
    @Column(name = "title")
    private String title;
    @Column(name = "value")
    private String value;

    public ALiteActionEntity() {
    }

    public ALiteActionEntity(String field, String title, String value) {
        this.field = field;
        this.title = title;
        this.value = value;
    }

    public ALiteActionEntity(DiffAction diffAction) {
        this.field = diffAction.getField();
        this.title = diffAction.getTitle();
        this.value = diffAction.getValue();
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
