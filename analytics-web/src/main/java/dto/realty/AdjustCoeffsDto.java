package dto.realty;

import enums.report.ERealtyParam;

public class AdjustCoeffsDto {

    private String fromParametr;

    private String toParameter;

    private Double value;

    private boolean extreme;

    private ERealtyParam type;

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
