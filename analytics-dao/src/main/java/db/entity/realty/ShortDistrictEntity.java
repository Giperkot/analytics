package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import interfaces.report.ITitled;

@Entity
@Table(name = "district", schema = "realty")
public class ShortDistrictEntity extends BaseEntity implements ITitled {

    @Column(name = "name")
    private String name;

    @Column(name = "parent_district_id")
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
