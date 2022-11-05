package db.entity.realty;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.report.ERealtyParam;

@Entity
@Table(name = "adjust_coeffs", schema = "realty")
public class AdjustCoeffsEntity extends BaseEntity {

    @Column(name = "from_parametr")
    private String fromParametr;

    @Column(name = "to_parameter")
    private String toParameter;

    @Column(name = "value")
    private Double value;

    @Column(name = "extreme")
    private boolean extreme;

    @Column(name = "type")
    private ERealtyParam type;

    @Column(name = "absolute")
    private boolean absolute;

    public String getFromParametr() {
        return fromParametr;
    }

    public void setFromParametr(String fromParametr) {
        this.fromParametr = fromParametr;
    }

    public String getToParameter() {
        return toParameter;
    }

    public void setToParameter(String toParameter) {
        this.toParameter = toParameter;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isExtreme() {
        return extreme;
    }

    public void setExtreme(boolean extreme) {
        this.extreme = extreme;
    }

    public ERealtyParam getType() {
        return type;
    }

    public void setType(ERealtyParam type) {
        this.type = type;
    }

    public boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(boolean absolute) {
        this.absolute = absolute;
    }
}
