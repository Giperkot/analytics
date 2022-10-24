package dto.parser;

import enums.EUnit;

public class FeatureComplexValue {

    private String name;

    private String value;

    private EUnit unit;

    public FeatureComplexValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /*public FeatureComplexValue(String value) {
        this.value = value;
    }*/

    public FeatureComplexValue(String name, String value, EUnit unit) {
        this.value = value;
        this.unit = unit;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EUnit getUnit() {
        return unit;
    }

    public void setUnit(EUnit unit) {
        this.unit = unit;
    }
}
