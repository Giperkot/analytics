package dto.realty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoundsDto {

    /**
     * Нижний правый угол
     */
    @JsonProperty("low")
    private CoordPoint lower;

    /**
     * Левый верхний угол
     */
    @JsonProperty("up")
    private CoordPoint upper;

    public BoundsDto() {
    }

    public BoundsDto(CoordPoint lower, CoordPoint upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public CoordPoint getLower() {
        return lower;
    }

    public void setLower(CoordPoint lower) {
        this.lower = lower;
    }

    public CoordPoint getUpper() {
        return upper;
    }

    public void setUpper(CoordPoint upper) {
        this.upper = upper;
    }
}
