
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
    "is_default",
    "is_district_area_center",
    "is_region_center"
})
@Generated("jsonschema2pojo")
public class FlagsDto {

    @JsonProperty("is_default")
    private Boolean isDefault;
    @JsonProperty("is_district_area_center")
    private Boolean isDistrictAreaCenter;
    @JsonProperty("is_region_center")
    private Boolean isRegionCenter;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_default")
    public Boolean getIsDefault() {
        return isDefault;
    }

    @JsonProperty("is_default")
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("is_district_area_center")
    public Boolean getIsDistrictAreaCenter() {
        return isDistrictAreaCenter;
    }

    @JsonProperty("is_district_area_center")
    public void setIsDistrictAreaCenter(Boolean isDistrictAreaCenter) {
        this.isDistrictAreaCenter = isDistrictAreaCenter;
    }

    @JsonProperty("is_region_center")
    public Boolean getIsRegionCenter() {
        return isRegionCenter;
    }

    @JsonProperty("is_region_center")
    public void setIsRegionCenter(Boolean isRegionCenter) {
        this.isRegionCenter = isRegionCenter;
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
        sb.append(FlagsDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("isDefault");
        sb.append('=');
        sb.append(((this.isDefault == null)?"<null>":this.isDefault));
        sb.append(',');
        sb.append("isDistrictAreaCenter");
        sb.append('=');
        sb.append(((this.isDistrictAreaCenter == null)?"<null>":this.isDistrictAreaCenter));
        sb.append(',');
        sb.append("isRegionCenter");
        sb.append('=');
        sb.append(((this.isRegionCenter == null)?"<null>":this.isRegionCenter));
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
        result = ((result* 31)+((this.isRegionCenter == null)? 0 :this.isRegionCenter.hashCode()));
        result = ((result* 31)+((this.isDefault == null)? 0 :this.isDefault.hashCode()));
        result = ((result* 31)+((this.isDistrictAreaCenter == null)? 0 :this.isDistrictAreaCenter.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FlagsDto) == false) {
            return false;
        }
        FlagsDto rhs = ((FlagsDto) other);
        return (((((this.isRegionCenter == rhs.isRegionCenter)||((this.isRegionCenter!= null)&&this.isRegionCenter.equals(rhs.isRegionCenter)))&&((this.isDefault == rhs.isDefault)||((this.isDefault!= null)&&this.isDefault.equals(rhs.isDefault))))&&((this.isDistrictAreaCenter == rhs.isDistrictAreaCenter)||((this.isDistrictAreaCenter!= null)&&this.isDistrictAreaCenter.equals(rhs.isDistrictAreaCenter))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
