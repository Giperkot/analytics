
package dto.realty.giszkh.finalres;

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
    "guid",
    "actual",
    "code",
    "rootEntityGuid",
    "lastUpdateDate",
    "createDate",
    "name",
    "controlNumber",
    "additionalData",
    "description",
    "parent",
    "status",
    "level",
    "hasChildren",
    "hasActualChildren",
    "loadAttribute",
    "oldMoscow",
    "utvDate",
    "vvedDate"
})
@Generated("jsonschema2pojo")
public class ParentDto {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("actual")
    private Boolean actual;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rootEntityGuid")
    private Object rootEntityGuid;
    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("controlNumber")
    private Integer controlNumber;
    @JsonProperty("additionalData")
    private Object additionalData;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("parent")
    private ParentDto__1 parent;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("hasChildren")
    private Boolean hasChildren;
    @JsonProperty("hasActualChildren")
    private Boolean hasActualChildren;
    @JsonProperty("loadAttribute")
    private Object loadAttribute;
    @JsonProperty("oldMoscow")
    private Object oldMoscow;
    @JsonProperty("utvDate")
    private String utvDate;
    @JsonProperty("vvedDate")
    private String vvedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("actual")
    public Boolean getActual() {
        return actual;
    }

    @JsonProperty("actual")
    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("rootEntityGuid")
    public Object getRootEntityGuid() {
        return rootEntityGuid;
    }

    @JsonProperty("rootEntityGuid")
    public void setRootEntityGuid(Object rootEntityGuid) {
        this.rootEntityGuid = rootEntityGuid;
    }

    @JsonProperty("lastUpdateDate")
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    @JsonProperty("lastUpdateDate")
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @JsonProperty("createDate")
    public String getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("controlNumber")
    public Integer getControlNumber() {
        return controlNumber;
    }

    @JsonProperty("controlNumber")
    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    @JsonProperty("additionalData")
    public Object getAdditionalData() {
        return additionalData;
    }

    @JsonProperty("additionalData")
    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("parent")
    public ParentDto__1 getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(ParentDto__1 parent) {
        this.parent = parent;
    }

    @JsonProperty("status")
    public Object getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Object status) {
        this.status = status;
    }

    @JsonProperty("level")
    public Integer getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(Integer level) {
        this.level = level;
    }

    @JsonProperty("hasChildren")
    public Boolean getHasChildren() {
        return hasChildren;
    }

    @JsonProperty("hasChildren")
    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @JsonProperty("hasActualChildren")
    public Boolean getHasActualChildren() {
        return hasActualChildren;
    }

    @JsonProperty("hasActualChildren")
    public void setHasActualChildren(Boolean hasActualChildren) {
        this.hasActualChildren = hasActualChildren;
    }

    @JsonProperty("loadAttribute")
    public Object getLoadAttribute() {
        return loadAttribute;
    }

    @JsonProperty("loadAttribute")
    public void setLoadAttribute(Object loadAttribute) {
        this.loadAttribute = loadAttribute;
    }

    @JsonProperty("oldMoscow")
    public Object getOldMoscow() {
        return oldMoscow;
    }

    @JsonProperty("oldMoscow")
    public void setOldMoscow(Object oldMoscow) {
        this.oldMoscow = oldMoscow;
    }

    @JsonProperty("utvDate")
    public String getUtvDate() {
        return utvDate;
    }

    @JsonProperty("utvDate")
    public void setUtvDate(String utvDate) {
        this.utvDate = utvDate;
    }

    @JsonProperty("vvedDate")
    public String getVvedDate() {
        return vvedDate;
    }

    @JsonProperty("vvedDate")
    public void setVvedDate(String vvedDate) {
        this.vvedDate = vvedDate;
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
        sb.append(ParentDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("guid");
        sb.append('=');
        sb.append(((this.guid == null)?"<null>":this.guid));
        sb.append(',');
        sb.append("actual");
        sb.append('=');
        sb.append(((this.actual == null)?"<null>":this.actual));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("rootEntityGuid");
        sb.append('=');
        sb.append(((this.rootEntityGuid == null)?"<null>":this.rootEntityGuid));
        sb.append(',');
        sb.append("lastUpdateDate");
        sb.append('=');
        sb.append(((this.lastUpdateDate == null)?"<null>":this.lastUpdateDate));
        sb.append(',');
        sb.append("createDate");
        sb.append('=');
        sb.append(((this.createDate == null)?"<null>":this.createDate));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("controlNumber");
        sb.append('=');
        sb.append(((this.controlNumber == null)?"<null>":this.controlNumber));
        sb.append(',');
        sb.append("additionalData");
        sb.append('=');
        sb.append(((this.additionalData == null)?"<null>":this.additionalData));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("parent");
        sb.append('=');
        sb.append(((this.parent == null)?"<null>":this.parent));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("level");
        sb.append('=');
        sb.append(((this.level == null)?"<null>":this.level));
        sb.append(',');
        sb.append("hasChildren");
        sb.append('=');
        sb.append(((this.hasChildren == null)?"<null>":this.hasChildren));
        sb.append(',');
        sb.append("hasActualChildren");
        sb.append('=');
        sb.append(((this.hasActualChildren == null)?"<null>":this.hasActualChildren));
        sb.append(',');
        sb.append("loadAttribute");
        sb.append('=');
        sb.append(((this.loadAttribute == null)?"<null>":this.loadAttribute));
        sb.append(',');
        sb.append("oldMoscow");
        sb.append('=');
        sb.append(((this.oldMoscow == null)?"<null>":this.oldMoscow));
        sb.append(',');
        sb.append("utvDate");
        sb.append('=');
        sb.append(((this.utvDate == null)?"<null>":this.utvDate));
        sb.append(',');
        sb.append("vvedDate");
        sb.append('=');
        sb.append(((this.vvedDate == null)?"<null>":this.vvedDate));
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
        result = ((result* 31)+((this.actual == null)? 0 :this.actual.hashCode()));
        result = ((result* 31)+((this.parent == null)? 0 :this.parent.hashCode()));
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.level == null)? 0 :this.level.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.hasChildren == null)? 0 :this.hasChildren.hashCode()));
        result = ((result* 31)+((this.hasActualChildren == null)? 0 :this.hasActualChildren.hashCode()));
        result = ((result* 31)+((this.vvedDate == null)? 0 :this.vvedDate.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.loadAttribute == null)? 0 :this.loadAttribute.hashCode()));
        result = ((result* 31)+((this.rootEntityGuid == null)? 0 :this.rootEntityGuid.hashCode()));
        result = ((result* 31)+((this.utvDate == null)? 0 :this.utvDate.hashCode()));
        result = ((result* 31)+((this.oldMoscow == null)? 0 :this.oldMoscow.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.additionalData == null)? 0 :this.additionalData.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.controlNumber == null)? 0 :this.controlNumber.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ParentDto) == false) {
            return false;
        }
        ParentDto rhs = ((ParentDto) other);
        return (((((((((((((((((((((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual)))&&((this.parent == rhs.parent)||((this.parent!= null)&&this.parent.equals(rhs.parent))))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.level == rhs.level)||((this.level!= null)&&this.level.equals(rhs.level))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.hasChildren == rhs.hasChildren)||((this.hasChildren!= null)&&this.hasChildren.equals(rhs.hasChildren))))&&((this.hasActualChildren == rhs.hasActualChildren)||((this.hasActualChildren!= null)&&this.hasActualChildren.equals(rhs.hasActualChildren))))&&((this.vvedDate == rhs.vvedDate)||((this.vvedDate!= null)&&this.vvedDate.equals(rhs.vvedDate))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.loadAttribute == rhs.loadAttribute)||((this.loadAttribute!= null)&&this.loadAttribute.equals(rhs.loadAttribute))))&&((this.rootEntityGuid == rhs.rootEntityGuid)||((this.rootEntityGuid!= null)&&this.rootEntityGuid.equals(rhs.rootEntityGuid))))&&((this.utvDate == rhs.utvDate)||((this.utvDate!= null)&&this.utvDate.equals(rhs.utvDate))))&&((this.oldMoscow == rhs.oldMoscow)||((this.oldMoscow!= null)&&this.oldMoscow.equals(rhs.oldMoscow))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.additionalData == rhs.additionalData)||((this.additionalData!= null)&&this.additionalData.equals(rhs.additionalData))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.controlNumber == rhs.controlNumber)||((this.controlNumber!= null)&&this.controlNumber.equals(rhs.controlNumber))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
