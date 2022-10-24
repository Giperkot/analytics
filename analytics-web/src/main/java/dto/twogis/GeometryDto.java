
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
    "centroid",
    "selection"
})
@Generated("jsonschema2pojo")
public class GeometryDto {

    @JsonProperty("centroid")
    private String centroid;
    @JsonProperty("selection")
    private String selection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("centroid")
    public String getCentroid() {
        return centroid;
    }

    @JsonProperty("centroid")
    public void setCentroid(String centroid) {
        this.centroid = centroid;
    }

    @JsonProperty("selection")
    public String getSelection() {
        return selection;
    }

    @JsonProperty("selection")
    public void setSelection(String selection) {
        this.selection = selection;
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
        sb.append(GeometryDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("centroid");
        sb.append('=');
        sb.append(((this.centroid == null)?"<null>":this.centroid));
        sb.append(',');
        sb.append("selection");
        sb.append('=');
        sb.append(((this.selection == null)?"<null>":this.selection));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.selection == null)? 0 :this.selection.hashCode()));
        result = ((result* 31)+((this.centroid == null)? 0 :this.centroid.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeometryDto) == false) {
            return false;
        }
        GeometryDto rhs = ((GeometryDto) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.selection == rhs.selection)||((this.selection!= null)&&this.selection.equals(rhs.selection))))&&((this.centroid == rhs.centroid)||((this.centroid!= null)&&this.centroid.equals(rhs.centroid))));
    }

}
