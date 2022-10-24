
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
    "geometry",
    "id",
    "is_primary",
    "entity_name",
    "entity_number",
    "has_poi",
    "is_visible_in_ui",
    "is_visible_on_map",
    "name"
})
@Generated("jsonschema2pojo")
public class DatabaseEntranceDto {

    @JsonProperty("geometry")
    private GeometryDto__1 geometry;
    @JsonProperty("id")
    private String id;
    @JsonProperty("is_primary")
    private Boolean isPrimary;
    @JsonProperty("entity_name")
    private String entityName;
    @JsonProperty("entity_number")
    private String entityNumber;
    @JsonProperty("has_poi")
    private Boolean hasPoi;
    @JsonProperty("is_visible_in_ui")
    private Boolean isVisibleInUi;
    @JsonProperty("is_visible_on_map")
    private Boolean isVisibleOnMap;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("geometry")
    public GeometryDto__1 getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(GeometryDto__1 geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("is_primary")
    public Boolean getIsPrimary() {
        return isPrimary;
    }

    @JsonProperty("is_primary")
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @JsonProperty("entity_name")
    public String getEntityName() {
        return entityName;
    }

    @JsonProperty("entity_name")
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @JsonProperty("entity_number")
    public String getEntityNumber() {
        return entityNumber;
    }

    @JsonProperty("entity_number")
    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    @JsonProperty("has_poi")
    public Boolean getHasPoi() {
        return hasPoi;
    }

    @JsonProperty("has_poi")
    public void setHasPoi(Boolean hasPoi) {
        this.hasPoi = hasPoi;
    }

    @JsonProperty("is_visible_in_ui")
    public Boolean getIsVisibleInUi() {
        return isVisibleInUi;
    }

    @JsonProperty("is_visible_in_ui")
    public void setIsVisibleInUi(Boolean isVisibleInUi) {
        this.isVisibleInUi = isVisibleInUi;
    }

    @JsonProperty("is_visible_on_map")
    public Boolean getIsVisibleOnMap() {
        return isVisibleOnMap;
    }

    @JsonProperty("is_visible_on_map")
    public void setIsVisibleOnMap(Boolean isVisibleOnMap) {
        this.isVisibleOnMap = isVisibleOnMap;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        sb.append(DatabaseEntranceDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("geometry");
        sb.append('=');
        sb.append(((this.geometry == null)?"<null>":this.geometry));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("isPrimary");
        sb.append('=');
        sb.append(((this.isPrimary == null)?"<null>":this.isPrimary));
        sb.append(',');
        sb.append("entityName");
        sb.append('=');
        sb.append(((this.entityName == null)?"<null>":this.entityName));
        sb.append(',');
        sb.append("entityNumber");
        sb.append('=');
        sb.append(((this.entityNumber == null)?"<null>":this.entityNumber));
        sb.append(',');
        sb.append("hasPoi");
        sb.append('=');
        sb.append(((this.hasPoi == null)?"<null>":this.hasPoi));
        sb.append(',');
        sb.append("isVisibleInUi");
        sb.append('=');
        sb.append(((this.isVisibleInUi == null)?"<null>":this.isVisibleInUi));
        sb.append(',');
        sb.append("isVisibleOnMap");
        sb.append('=');
        sb.append(((this.isVisibleOnMap == null)?"<null>":this.isVisibleOnMap));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
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
        result = ((result* 31)+((this.isVisibleOnMap == null)? 0 :this.isVisibleOnMap.hashCode()));
        result = ((result* 31)+((this.hasPoi == null)? 0 :this.hasPoi.hashCode()));
        result = ((result* 31)+((this.isPrimary == null)? 0 :this.isPrimary.hashCode()));
        result = ((result* 31)+((this.entityName == null)? 0 :this.entityName.hashCode()));
        result = ((result* 31)+((this.isVisibleInUi == null)? 0 :this.isVisibleInUi.hashCode()));
        result = ((result* 31)+((this.entityNumber == null)? 0 :this.entityNumber.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.geometry == null)? 0 :this.geometry.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DatabaseEntranceDto) == false) {
            return false;
        }
        DatabaseEntranceDto rhs = ((DatabaseEntranceDto) other);
        return (((((((((((this.isVisibleOnMap == rhs.isVisibleOnMap)||((this.isVisibleOnMap!= null)&&this.isVisibleOnMap.equals(rhs.isVisibleOnMap)))&&((this.hasPoi == rhs.hasPoi)||((this.hasPoi!= null)&&this.hasPoi.equals(rhs.hasPoi))))&&((this.isPrimary == rhs.isPrimary)||((this.isPrimary!= null)&&this.isPrimary.equals(rhs.isPrimary))))&&((this.entityName == rhs.entityName)||((this.entityName!= null)&&this.entityName.equals(rhs.entityName))))&&((this.isVisibleInUi == rhs.isVisibleInUi)||((this.isVisibleInUi!= null)&&this.isVisibleInUi.equals(rhs.isVisibleInUi))))&&((this.entityNumber == rhs.entityNumber)||((this.entityNumber!= null)&&this.entityNumber.equals(rhs.entityNumber))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.geometry == rhs.geometry)||((this.geometry!= null)&&this.geometry.equals(rhs.geometry))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
