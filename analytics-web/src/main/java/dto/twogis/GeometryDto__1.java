
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
    "normals",
    "points",
    "vectors"
})
@Generated("jsonschema2pojo")
public class GeometryDto__1 {

    @JsonProperty("normals")
    private List<String> normals = new ArrayList<String>();
    @JsonProperty("points")
    private List<String> points = new ArrayList<String>();
    @JsonProperty("vectors")
    private List<String> vectors = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("normals")
    public List<String> getNormals() {
        return normals;
    }

    @JsonProperty("normals")
    public void setNormals(List<String> normals) {
        this.normals = normals;
    }

    @JsonProperty("points")
    public List<String> getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(List<String> points) {
        this.points = points;
    }

    @JsonProperty("vectors")
    public List<String> getVectors() {
        return vectors;
    }

    @JsonProperty("vectors")
    public void setVectors(List<String> vectors) {
        this.vectors = vectors;
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
        sb.append(GeometryDto__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("normals");
        sb.append('=');
        sb.append(((this.normals == null)?"<null>":this.normals));
        sb.append(',');
        sb.append("points");
        sb.append('=');
        sb.append(((this.points == null)?"<null>":this.points));
        sb.append(',');
        sb.append("vectors");
        sb.append('=');
        sb.append(((this.vectors == null)?"<null>":this.vectors));
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
        result = ((result* 31)+((this.vectors == null)? 0 :this.vectors.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.normals == null)? 0 :this.normals.hashCode()));
        result = ((result* 31)+((this.points == null)? 0 :this.points.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeometryDto__1) == false) {
            return false;
        }
        GeometryDto__1 rhs = ((GeometryDto__1) other);
        return (((((this.vectors == rhs.vectors)||((this.vectors!= null)&&this.vectors.equals(rhs.vectors)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.normals == rhs.normals)||((this.normals!= null)&&this.normals.equals(rhs.normals))))&&((this.points == rhs.points)||((this.points!= null)&&this.points.equals(rhs.points))));
    }

}
