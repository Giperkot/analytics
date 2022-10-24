
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
    "additional_info",
    "id",
    "name",
    "org_id",
    "contacts"
})
@Generated("jsonschema2pojo")
public class ItemDto__1 {

    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("org_id")
    private String orgId;
    @JsonProperty("contacts")
    private List<ContactDto> contacts = new ArrayList<ContactDto>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("additional_info")
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @JsonProperty("additional_info")
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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

    @JsonProperty("org_id")
    public String getOrgId() {
        return orgId;
    }

    @JsonProperty("org_id")
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @JsonProperty("contacts")
    public List<ContactDto> getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
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
        sb.append(ItemDto__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("additionalInfo");
        sb.append('=');
        sb.append(((this.additionalInfo == null)?"<null>":this.additionalInfo));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("orgId");
        sb.append('=');
        sb.append(((this.orgId == null)?"<null>":this.orgId));
        sb.append(',');
        sb.append("contacts");
        sb.append('=');
        sb.append(((this.contacts == null)?"<null>":this.contacts));
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
        result = ((result* 31)+((this.additionalInfo == null)? 0 :this.additionalInfo.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.orgId == null)? 0 :this.orgId.hashCode()));
        result = ((result* 31)+((this.contacts == null)? 0 :this.contacts.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ItemDto__1) == false) {
            return false;
        }
        ItemDto__1 rhs = ((ItemDto__1) other);
        return (((((((this.additionalInfo == rhs.additionalInfo)||((this.additionalInfo!= null)&&this.additionalInfo.equals(rhs.additionalInfo)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.orgId == rhs.orgId)||((this.orgId!= null)&&this.orgId.equals(rhs.orgId))))&&((this.contacts == rhs.contacts)||((this.contacts!= null)&&this.contacts.equals(rhs.contacts))));
    }

}
