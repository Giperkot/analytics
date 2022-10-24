
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
    "lifeCycleStage"
})
@Generated("jsonschema2pojo")
public class LifeCycleStageDto {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("actual")
    private Boolean actual;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rootEntityGuid")
    private String rootEntityGuid;
    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("lifeCycleStage")
    private String lifeCycleStage;
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
    public String getRootEntityGuid() {
        return rootEntityGuid;
    }

    @JsonProperty("rootEntityGuid")
    public void setRootEntityGuid(String rootEntityGuid) {
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

    @JsonProperty("lifeCycleStage")
    public String getLifeCycleStage() {
        return lifeCycleStage;
    }

    @JsonProperty("lifeCycleStage")
    public void setLifeCycleStage(String lifeCycleStage) {
        this.lifeCycleStage = lifeCycleStage;
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
        sb.append(LifeCycleStageDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("lifeCycleStage");
        sb.append('=');
        sb.append(((this.lifeCycleStage == null)?"<null>":this.lifeCycleStage));
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
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.rootEntityGuid == null)? 0 :this.rootEntityGuid.hashCode()));
        result = ((result* 31)+((this.lifeCycleStage == null)? 0 :this.lifeCycleStage.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LifeCycleStageDto) == false) {
            return false;
        }
        LifeCycleStageDto rhs = ((LifeCycleStageDto) other);
        return (((((((((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.rootEntityGuid == rhs.rootEntityGuid)||((this.rootEntityGuid!= null)&&this.rootEntityGuid.equals(rhs.rootEntityGuid))))&&((this.lifeCycleStage == rhs.lifeCycleStage)||((this.lifeCycleStage!= null)&&this.lifeCycleStage.equals(rhs.lifeCycleStage))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))));
    }

}
