package db.entity.parser;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.EFeatureExactName;

import java.util.Date;

@Entity
@Table(name = "feature", schema = "parser")
public class FeatureEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "exact_name")
    private EFeatureExactName exactName;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public EFeatureExactName getExactName() {
        return exactName;
    }

    public void setExactName(EFeatureExactName exactName) {
        this.exactName = exactName;
    }
}
