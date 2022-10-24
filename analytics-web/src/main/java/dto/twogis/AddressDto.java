
package dto.twogis;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "building_id",
    "components",
    "postcode"
})
@Generated("jsonschema2pojo")
public class AddressDto {

    @JsonProperty("building_id")
    private String buildingId;
    @JsonProperty("components")
    private List<ComponentDto> components = new ArrayList<ComponentDto>();
    @JsonProperty("postcode")
    private String postcode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("building_id")
    public String getBuildingId() {
        return buildingId;
    }

    @JsonProperty("building_id")
    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @JsonProperty("components")
    public List<ComponentDto> getComponents() {
        return components;
    }

    @JsonProperty("components")
    public void setComponents(List<ComponentDto> components) {
        this.components = components;
    }

    @JsonProperty("postcode")
    public String getPostcode() {
        return postcode;
    }

    @JsonProperty("postcode")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
        sb.append(AddressDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("buildingId");
        sb.append('=');
        sb.append(((this.buildingId == null)?"<null>":this.buildingId));
        sb.append(',');
        sb.append("components");
        sb.append('=');
        sb.append(((this.components == null)?"<null>":this.components));
        sb.append(',');
        sb.append("postcode");
        sb.append('=');
        sb.append(((this.postcode == null)?"<null>":this.postcode));
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
        result = ((result* 31)+((this.postcode == null)? 0 :this.postcode.hashCode()));
        result = ((result* 31)+((this.components == null)? 0 :this.components.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.buildingId == null)? 0 :this.buildingId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddressDto) == false) {
            return false;
        }
        AddressDto rhs = ((AddressDto) other);
        return (((((this.postcode == rhs.postcode)||((this.postcode!= null)&&this.postcode.equals(rhs.postcode)))&&((this.components == rhs.components)||((this.components!= null)&&this.components.equals(rhs.components))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.buildingId == rhs.buildingId)||((this.buildingId!= null)&&this.buildingId.equals(rhs.buildingId))));
    }

}
