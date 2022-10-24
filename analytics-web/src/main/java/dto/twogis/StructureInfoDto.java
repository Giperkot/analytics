
package dto.twogis;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elevators_count",
    "floor_type",
    "gas_type",
    "is_in_emergency_state",
    "material",
    "year_of_construction"
})
@Generated("jsonschema2pojo")
public class StructureInfoDto {

    @JsonProperty("elevators_count")
    private Integer elevatorsCount;
    @JsonProperty("floor_type")
    private String floorType;
    @JsonProperty("gas_type")
    private String gasType;
    @JsonProperty("is_in_emergency_state")
    private Boolean isInEmergencyState;
    @JsonProperty("material")
    private String material;
    @JsonProperty("year_of_construction")
    private Integer yearOfConstruction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("elevators_count")
    public Integer getElevatorsCount() {
        return elevatorsCount;
    }

    @JsonProperty("elevators_count")
    public void setElevatorsCount(Integer elevatorsCount) {
        this.elevatorsCount = elevatorsCount;
    }

    @JsonProperty("floor_type")
    public String getFloorType() {
        return floorType;
    }

    @JsonProperty("floor_type")
    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    @JsonProperty("gas_type")
    public String getGasType() {
        return gasType;
    }

    @JsonProperty("gas_type")
    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    @JsonProperty("is_in_emergency_state")
    public Boolean getIsInEmergencyState() {
        return isInEmergencyState;
    }

    @JsonProperty("is_in_emergency_state")
    public void setIsInEmergencyState(Boolean isInEmergencyState) {
        this.isInEmergencyState = isInEmergencyState;
    }

    @JsonProperty("material")
    public String getMaterial() {
        return material;
    }

    @JsonProperty("material")
    public void setMaterial(String material) {
        this.material = material;
    }

    @JsonProperty("year_of_construction")
    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    @JsonProperty("year_of_construction")
    public void setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StructureInfoDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("elevatorsCount");
        sb.append('=');
        sb.append(((this.elevatorsCount == null)?"<null>":this.elevatorsCount));
        sb.append(',');
        sb.append("floorType");
        sb.append('=');
        sb.append(((this.floorType == null)?"<null>":this.floorType));
        sb.append(',');
        sb.append("gasType");
        sb.append('=');
        sb.append(((this.gasType == null)?"<null>":this.gasType));
        sb.append(',');
        sb.append("isInEmergencyState");
        sb.append('=');
        sb.append(((this.isInEmergencyState == null)?"<null>":this.isInEmergencyState));
        sb.append(',');
        sb.append("material");
        sb.append('=');
        sb.append(((this.material == null)?"<null>":this.material));
        sb.append(',');
        sb.append("yearOfConstruction");
        sb.append('=');
        sb.append(((this.yearOfConstruction == null)?"<null>":this.yearOfConstruction));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.elevatorsCount == null)? 0 :this.elevatorsCount.hashCode()));
        result = ((result* 31)+((this.isInEmergencyState == null)? 0 :this.isInEmergencyState.hashCode()));
        result = ((result* 31)+((this.material == null)? 0 :this.material.hashCode()));
        result = ((result* 31)+((this.gasType == null)? 0 :this.gasType.hashCode()));
        result = ((result* 31)+((this.floorType == null)? 0 :this.floorType.hashCode()));
        result = ((result* 31)+((this.yearOfConstruction == null)? 0 :this.yearOfConstruction.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StructureInfoDto) == false) {
            return false;
        }
        StructureInfoDto rhs = ((StructureInfoDto) other);
        return ((((((((this.elevatorsCount == rhs.elevatorsCount)||((this.elevatorsCount!= null)&&this.elevatorsCount.equals(rhs.elevatorsCount)))&&((this.isInEmergencyState == rhs.isInEmergencyState)||((this.isInEmergencyState!= null)&&this.isInEmergencyState.equals(rhs.isInEmergencyState))))&&((this.material == rhs.material)||((this.material!= null)&&this.material.equals(rhs.material))))&&((this.gasType == rhs.gasType)||((this.gasType!= null)&&this.gasType.equals(rhs.gasType))))&&((this.floorType == rhs.floorType)||((this.floorType!= null)&&this.floorType.equals(rhs.floorType))))&&((this.yearOfConstruction == rhs.yearOfConstruction)||((this.yearOfConstruction!= null)&&this.yearOfConstruction.equals(rhs.yearOfConstruction))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
