package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;

@Entity
@Table(name = "", schema = "")
public class NoticeAveragePriceEntity extends BaseEntity {

    @Column(name = "canon_type_number")
    private int canonicalTypeNumber;

    @Column(name = "realty_config_type")
    private String realtyConfigType;

    @Column(name = "sum")
    private double sum;

    public int getCanonicalTypeNumber() {
        return canonicalTypeNumber;
    }

    public void setCanonicalTypeNumber(int canonicalTypeNumber) {
        this.canonicalTypeNumber = canonicalTypeNumber;
    }

    public String getRealtyConfigType() {
        return realtyConfigType;
    }

    public void setRealtyConfigType(String realtyConfigType) {
        this.realtyConfigType = realtyConfigType;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
