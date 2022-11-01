package db.entity.realty.excelimport;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;

@Entity
@Table(name = "import_realty_request", schema = "realty")
public class ImportRealtyRequestEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "objects_count")
    private int objectsCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getObjectsCount() {
        return objectsCount;
    }

    public void setObjectsCount(int objectsCount) {
        this.objectsCount = objectsCount;
    }
}
