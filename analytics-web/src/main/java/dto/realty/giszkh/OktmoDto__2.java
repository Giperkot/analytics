
package dto.realty.giszkh;

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
    "classType",
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
public class OktmoDto__2 {

    @JsonProperty("classType")
    private String classType;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("actual")
    private Object actual;
    @JsonProperty("code")
    private Object code;
    @JsonProperty("rootEntityGuid")
    private Object rootEntityGuid;
    @JsonProperty("lastUpdateDate")
    private Object lastUpdateDate;
    @JsonProperty("createDate")
    private Object createDate;
    @JsonProperty("name")
    private Object name;
    @JsonProperty("controlNumber")
    private Object controlNumber;
    @JsonProperty("additionalData")
    private Object additionalData;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("parent")
    private Object parent;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("level")
    private Object level;
    @JsonProperty("hasChildren")
    private Object hasChildren;
    @JsonProperty("hasActualChildren")
    private Object hasActualChildren;
    @JsonProperty("loadAttribute")
    private Object loadAttribute;
    @JsonProperty("oldMoscow")
    private Object oldMoscow;
    @JsonProperty("utvDate")
    private Object utvDate;
    @JsonProperty("vvedDate")
    private Object vvedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("classType")
    public String getClassType() {
        return classType;
    }

    @JsonProperty("classType")
    public void setClassType(String classType) {
        this.classType = classType;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("actual")
    public Object getActual() {
        return actual;
    }

    @JsonProperty("actual")
    public void setActual(Object actual) {
        this.actual = actual;
    }

    @JsonProperty("code")
    public Object getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Object code) {
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
    public Object getLastUpdateDate() {
        return lastUpdateDate;
    }

    @JsonProperty("lastUpdateDate")
    public void setLastUpdateDate(Object lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @JsonProperty("createDate")
    public Object getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("name")
    public Object getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Object name) {
        this.name = name;
    }

    @JsonProperty("controlNumber")
    public Object getControlNumber() {
        return controlNumber;
    }

    @JsonProperty("controlNumber")
    public void setControlNumber(Object controlNumber) {
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
    public Object getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(Object parent) {
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
    public Object getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(Object level) {
        this.level = level;
    }

    @JsonProperty("hasChildren")
    public Object getHasChildren() {
        return hasChildren;
    }

    @JsonProperty("hasChildren")
    public void setHasChildren(Object hasChildren) {
        this.hasChildren = hasChildren;
    }

    @JsonProperty("hasActualChildren")
    public Object getHasActualChildren() {
        return hasActualChildren;
    }

    @JsonProperty("hasActualChildren")
    public void setHasActualChildren(Object hasActualChildren) {
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
    public Object getUtvDate() {
        return utvDate;
    }

    @JsonProperty("utvDate")
    public void setUtvDate(Object utvDate) {
        this.utvDate = utvDate;
    }

    @JsonProperty("vvedDate")
    public Object getVvedDate() {
        return vvedDate;
    }

    @JsonProperty("vvedDate")
    public void setVvedDate(Object vvedDate) {
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
        sb.append(OktmoDto__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("classType");
        sb.append('=');
        sb.append(((this.classType == null)?"<null>":this.classType));
        sb.append(',');
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
        result = ((result* 31)+((this.classType == null)? 0 :this.classType.hashCode()));
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
        if ((other instanceof OktmoDto__2) == false) {
            return false;
        }
        OktmoDto__2 rhs = ((OktmoDto__2) other);
        return ((((((((((((((((((((((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual)))&&((this.parent == rhs.parent)||((this.parent!= null)&&this.parent.equals(rhs.parent))))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.level == rhs.level)||((this.level!= null)&&this.level.equals(rhs.level))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.hasChildren == rhs.hasChildren)||((this.hasChildren!= null)&&this.hasChildren.equals(rhs.hasChildren))))&&((this.hasActualChildren == rhs.hasActualChildren)||((this.hasActualChildren!= null)&&this.hasActualChildren.equals(rhs.hasActualChildren))))&&((this.vvedDate == rhs.vvedDate)||((this.vvedDate!= null)&&this.vvedDate.equals(rhs.vvedDate))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.loadAttribute == rhs.loadAttribute)||((this.loadAttribute!= null)&&this.loadAttribute.equals(rhs.loadAttribute))))&&((this.rootEntityGuid == rhs.rootEntityGuid)||((this.rootEntityGuid!= null)&&this.rootEntityGuid.equals(rhs.rootEntityGuid))))&&((this.utvDate == rhs.utvDate)||((this.utvDate!= null)&&this.utvDate.equals(rhs.utvDate))))&&((this.oldMoscow == rhs.oldMoscow)||((this.oldMoscow!= null)&&this.oldMoscow.equals(rhs.oldMoscow))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.additionalData == rhs.additionalData)||((this.additionalData!= null)&&this.additionalData.equals(rhs.additionalData))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.classType == rhs.classType)||((this.classType!= null)&&this.classType.equals(rhs.classType))))&&((this.controlNumber == rhs.controlNumber)||((this.controlNumber!= null)&&this.controlNumber.equals(rhs.controlNumber))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
