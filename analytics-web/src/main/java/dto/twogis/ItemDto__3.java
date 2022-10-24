
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
    "address_name",
    "full_name",
    "id",
    "name",
    "purpose_name",
    "search_attributes",
    "type"
})
@Generated("jsonschema2pojo")
public class ItemDto__3 {

    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("purpose_name")
    private String purposeName;
    @JsonProperty("search_attributes")
    private SearchAttributesDto searchAttributes;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address_name")
    public String getAddressName() {
        return addressName;
    }

    @JsonProperty("address_name")
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("purpose_name")
    public String getPurposeName() {
        return purposeName;
    }

    @JsonProperty("purpose_name")
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    @JsonProperty("search_attributes")
    public SearchAttributesDto getSearchAttributes() {
        return searchAttributes;
    }

    @JsonProperty("search_attributes")
    public void setSearchAttributes(SearchAttributesDto searchAttributes) {
        this.searchAttributes = searchAttributes;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
        sb.append(ItemDto__3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("addressName");
        sb.append('=');
        sb.append(((this.addressName == null)?"<null>":this.addressName));
        sb.append(',');
        sb.append("fullName");
        sb.append('=');
        sb.append(((this.fullName == null)?"<null>":this.fullName));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("purposeName");
        sb.append('=');
        sb.append(((this.purposeName == null)?"<null>":this.purposeName));
        sb.append(',');
        sb.append("searchAttributes");
        sb.append('=');
        sb.append(((this.searchAttributes == null)?"<null>":this.searchAttributes));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.purposeName == null)? 0 :this.purposeName.hashCode()));
        result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
        result = ((result* 31)+((this.addressName == null)? 0 :this.addressName.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.searchAttributes == null)? 0 :this.searchAttributes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ItemDto__3) == false) {
            return false;
        }
        ItemDto__3 rhs = ((ItemDto__3) other);
        return (((((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.purposeName == rhs.purposeName)||((this.purposeName!= null)&&this.purposeName.equals(rhs.purposeName))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.addressName == rhs.addressName)||((this.addressName!= null)&&this.addressName.equals(rhs.addressName))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.searchAttributes == rhs.searchAttributes)||((this.searchAttributes!= null)&&this.searchAttributes.equals(rhs.searchAttributes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }

}
