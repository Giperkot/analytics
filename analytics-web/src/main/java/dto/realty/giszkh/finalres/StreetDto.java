
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
    "aoGuid",
    "aoLevel",
    "postalCode",
    "formalName",
    "offName",
    "shortName",
    "parentGuid",
    "oktmo",
    "regionCode",
    "autoCode",
    "areaCode",
    "cityCode",
    "ctarCode",
    "placeCode",
    "planCode",
    "streetCode",
    "extrCode",
    "sextCode",
    "updateDate",
    "isAddedManually",
    "onApproval",
    "fiasAddrobjGuid",
    "subjectCity"
})
@Generated("jsonschema2pojo")
public class StreetDto {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("actual")
    private Boolean actual;
    @JsonProperty("code")
    private Object code;
    @JsonProperty("rootEntityGuid")
    private Object rootEntityGuid;
    @JsonProperty("lastUpdateDate")
    private Object lastUpdateDate;
    @JsonProperty("createDate")
    private Object createDate;
    @JsonProperty("aoGuid")
    private String aoGuid;
    @JsonProperty("aoLevel")
    private Integer aoLevel;
    @JsonProperty("postalCode")
    private Object postalCode;
    @JsonProperty("formalName")
    private String formalName;
    @JsonProperty("offName")
    private String offName;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("parentGuid")
    private String parentGuid;
    @JsonProperty("oktmo")
    private Object oktmo;
    @JsonProperty("regionCode")
    private String regionCode;
    @JsonProperty("autoCode")
    private Object autoCode;
    @JsonProperty("areaCode")
    private Object areaCode;
    @JsonProperty("cityCode")
    private Object cityCode;
    @JsonProperty("ctarCode")
    private Object ctarCode;
    @JsonProperty("placeCode")
    private Object placeCode;
    @JsonProperty("planCode")
    private Object planCode;
    @JsonProperty("streetCode")
    private Object streetCode;
    @JsonProperty("extrCode")
    private Object extrCode;
    @JsonProperty("sextCode")
    private Object sextCode;
    @JsonProperty("updateDate")
    private Object updateDate;
    @JsonProperty("isAddedManually")
    private Object isAddedManually;
    @JsonProperty("onApproval")
    private Object onApproval;
    @JsonProperty("fiasAddrobjGuid")
    private Object fiasAddrobjGuid;
    @JsonProperty("subjectCity")
    private Boolean subjectCity;
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

    @JsonProperty("aoGuid")
    public String getAoGuid() {
        return aoGuid;
    }

    @JsonProperty("aoGuid")
    public void setAoGuid(String aoGuid) {
        this.aoGuid = aoGuid;
    }

    @JsonProperty("aoLevel")
    public Integer getAoLevel() {
        return aoLevel;
    }

    @JsonProperty("aoLevel")
    public void setAoLevel(Integer aoLevel) {
        this.aoLevel = aoLevel;
    }

    @JsonProperty("postalCode")
    public Object getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("formalName")
    public String getFormalName() {
        return formalName;
    }

    @JsonProperty("formalName")
    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    @JsonProperty("offName")
    public String getOffName() {
        return offName;
    }

    @JsonProperty("offName")
    public void setOffName(String offName) {
        this.offName = offName;
    }

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("parentGuid")
    public String getParentGuid() {
        return parentGuid;
    }

    @JsonProperty("parentGuid")
    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

    @JsonProperty("oktmo")
    public Object getOktmo() {
        return oktmo;
    }

    @JsonProperty("oktmo")
    public void setOktmo(Object oktmo) {
        this.oktmo = oktmo;
    }

    @JsonProperty("regionCode")
    public String getRegionCode() {
        return regionCode;
    }

    @JsonProperty("regionCode")
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @JsonProperty("autoCode")
    public Object getAutoCode() {
        return autoCode;
    }

    @JsonProperty("autoCode")
    public void setAutoCode(Object autoCode) {
        this.autoCode = autoCode;
    }

    @JsonProperty("areaCode")
    public Object getAreaCode() {
        return areaCode;
    }

    @JsonProperty("areaCode")
    public void setAreaCode(Object areaCode) {
        this.areaCode = areaCode;
    }

    @JsonProperty("cityCode")
    public Object getCityCode() {
        return cityCode;
    }

    @JsonProperty("cityCode")
    public void setCityCode(Object cityCode) {
        this.cityCode = cityCode;
    }

    @JsonProperty("ctarCode")
    public Object getCtarCode() {
        return ctarCode;
    }

    @JsonProperty("ctarCode")
    public void setCtarCode(Object ctarCode) {
        this.ctarCode = ctarCode;
    }

    @JsonProperty("placeCode")
    public Object getPlaceCode() {
        return placeCode;
    }

    @JsonProperty("placeCode")
    public void setPlaceCode(Object placeCode) {
        this.placeCode = placeCode;
    }

    @JsonProperty("planCode")
    public Object getPlanCode() {
        return planCode;
    }

    @JsonProperty("planCode")
    public void setPlanCode(Object planCode) {
        this.planCode = planCode;
    }

    @JsonProperty("streetCode")
    public Object getStreetCode() {
        return streetCode;
    }

    @JsonProperty("streetCode")
    public void setStreetCode(Object streetCode) {
        this.streetCode = streetCode;
    }

    @JsonProperty("extrCode")
    public Object getExtrCode() {
        return extrCode;
    }

    @JsonProperty("extrCode")
    public void setExtrCode(Object extrCode) {
        this.extrCode = extrCode;
    }

    @JsonProperty("sextCode")
    public Object getSextCode() {
        return sextCode;
    }

    @JsonProperty("sextCode")
    public void setSextCode(Object sextCode) {
        this.sextCode = sextCode;
    }

    @JsonProperty("updateDate")
    public Object getUpdateDate() {
        return updateDate;
    }

    @JsonProperty("updateDate")
    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    @JsonProperty("isAddedManually")
    public Object getIsAddedManually() {
        return isAddedManually;
    }

    @JsonProperty("isAddedManually")
    public void setIsAddedManually(Object isAddedManually) {
        this.isAddedManually = isAddedManually;
    }

    @JsonProperty("onApproval")
    public Object getOnApproval() {
        return onApproval;
    }

    @JsonProperty("onApproval")
    public void setOnApproval(Object onApproval) {
        this.onApproval = onApproval;
    }

    @JsonProperty("fiasAddrobjGuid")
    public Object getFiasAddrobjGuid() {
        return fiasAddrobjGuid;
    }

    @JsonProperty("fiasAddrobjGuid")
    public void setFiasAddrobjGuid(Object fiasAddrobjGuid) {
        this.fiasAddrobjGuid = fiasAddrobjGuid;
    }

    @JsonProperty("subjectCity")
    public Boolean getSubjectCity() {
        return subjectCity;
    }

    @JsonProperty("subjectCity")
    public void setSubjectCity(Boolean subjectCity) {
        this.subjectCity = subjectCity;
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
        sb.append(StreetDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("aoGuid");
        sb.append('=');
        sb.append(((this.aoGuid == null)?"<null>":this.aoGuid));
        sb.append(',');
        sb.append("aoLevel");
        sb.append('=');
        sb.append(((this.aoLevel == null)?"<null>":this.aoLevel));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("formalName");
        sb.append('=');
        sb.append(((this.formalName == null)?"<null>":this.formalName));
        sb.append(',');
        sb.append("offName");
        sb.append('=');
        sb.append(((this.offName == null)?"<null>":this.offName));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("parentGuid");
        sb.append('=');
        sb.append(((this.parentGuid == null)?"<null>":this.parentGuid));
        sb.append(',');
        sb.append("oktmo");
        sb.append('=');
        sb.append(((this.oktmo == null)?"<null>":this.oktmo));
        sb.append(',');
        sb.append("regionCode");
        sb.append('=');
        sb.append(((this.regionCode == null)?"<null>":this.regionCode));
        sb.append(',');
        sb.append("autoCode");
        sb.append('=');
        sb.append(((this.autoCode == null)?"<null>":this.autoCode));
        sb.append(',');
        sb.append("areaCode");
        sb.append('=');
        sb.append(((this.areaCode == null)?"<null>":this.areaCode));
        sb.append(',');
        sb.append("cityCode");
        sb.append('=');
        sb.append(((this.cityCode == null)?"<null>":this.cityCode));
        sb.append(',');
        sb.append("ctarCode");
        sb.append('=');
        sb.append(((this.ctarCode == null)?"<null>":this.ctarCode));
        sb.append(',');
        sb.append("placeCode");
        sb.append('=');
        sb.append(((this.placeCode == null)?"<null>":this.placeCode));
        sb.append(',');
        sb.append("planCode");
        sb.append('=');
        sb.append(((this.planCode == null)?"<null>":this.planCode));
        sb.append(',');
        sb.append("streetCode");
        sb.append('=');
        sb.append(((this.streetCode == null)?"<null>":this.streetCode));
        sb.append(',');
        sb.append("extrCode");
        sb.append('=');
        sb.append(((this.extrCode == null)?"<null>":this.extrCode));
        sb.append(',');
        sb.append("sextCode");
        sb.append('=');
        sb.append(((this.sextCode == null)?"<null>":this.sextCode));
        sb.append(',');
        sb.append("updateDate");
        sb.append('=');
        sb.append(((this.updateDate == null)?"<null>":this.updateDate));
        sb.append(',');
        sb.append("isAddedManually");
        sb.append('=');
        sb.append(((this.isAddedManually == null)?"<null>":this.isAddedManually));
        sb.append(',');
        sb.append("onApproval");
        sb.append('=');
        sb.append(((this.onApproval == null)?"<null>":this.onApproval));
        sb.append(',');
        sb.append("fiasAddrobjGuid");
        sb.append('=');
        sb.append(((this.fiasAddrobjGuid == null)?"<null>":this.fiasAddrobjGuid));
        sb.append(',');
        sb.append("subjectCity");
        sb.append('=');
        sb.append(((this.subjectCity == null)?"<null>":this.subjectCity));
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
        result = ((result* 31)+((this.updateDate == null)? 0 :this.updateDate.hashCode()));
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.formalName == null)? 0 :this.formalName.hashCode()));
        result = ((result* 31)+((this.autoCode == null)? 0 :this.autoCode.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.cityCode == null)? 0 :this.cityCode.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.regionCode == null)? 0 :this.regionCode.hashCode()));
        result = ((result* 31)+((this.aoGuid == null)? 0 :this.aoGuid.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        result = ((result* 31)+((this.placeCode == null)? 0 :this.placeCode.hashCode()));
        result = ((result* 31)+((this.sextCode == null)? 0 :this.sextCode.hashCode()));
        result = ((result* 31)+((this.actual == null)? 0 :this.actual.hashCode()));
        result = ((result* 31)+((this.extrCode == null)? 0 :this.extrCode.hashCode()));
        result = ((result* 31)+((this.streetCode == null)? 0 :this.streetCode.hashCode()));
        result = ((result* 31)+((this.rootEntityGuid == null)? 0 :this.rootEntityGuid.hashCode()));
        result = ((result* 31)+((this.planCode == null)? 0 :this.planCode.hashCode()));
        result = ((result* 31)+((this.areaCode == null)? 0 :this.areaCode.hashCode()));
        result = ((result* 31)+((this.aoLevel == null)? 0 :this.aoLevel.hashCode()));
        result = ((result* 31)+((this.oktmo == null)? 0 :this.oktmo.hashCode()));
        result = ((result* 31)+((this.subjectCity == null)? 0 :this.subjectCity.hashCode()));
        result = ((result* 31)+((this.parentGuid == null)? 0 :this.parentGuid.hashCode()));
        result = ((result* 31)+((this.isAddedManually == null)? 0 :this.isAddedManually.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.fiasAddrobjGuid == null)? 0 :this.fiasAddrobjGuid.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.ctarCode == null)? 0 :this.ctarCode.hashCode()));
        result = ((result* 31)+((this.onApproval == null)? 0 :this.onApproval.hashCode()));
        result = ((result* 31)+((this.offName == null)? 0 :this.offName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StreetDto) == false) {
            return false;
        }
        StreetDto rhs = ((StreetDto) other);
        return (((((((((((((((((((((((((((((((this.updateDate == rhs.updateDate)||((this.updateDate!= null)&&this.updateDate.equals(rhs.updateDate)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.formalName == rhs.formalName)||((this.formalName!= null)&&this.formalName.equals(rhs.formalName))))&&((this.autoCode == rhs.autoCode)||((this.autoCode!= null)&&this.autoCode.equals(rhs.autoCode))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.cityCode == rhs.cityCode)||((this.cityCode!= null)&&this.cityCode.equals(rhs.cityCode))))&&((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&((this.regionCode == rhs.regionCode)||((this.regionCode!= null)&&this.regionCode.equals(rhs.regionCode))))&&((this.aoGuid == rhs.aoGuid)||((this.aoGuid!= null)&&this.aoGuid.equals(rhs.aoGuid))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.placeCode == rhs.placeCode)||((this.placeCode!= null)&&this.placeCode.equals(rhs.placeCode))))&&((this.sextCode == rhs.sextCode)||((this.sextCode!= null)&&this.sextCode.equals(rhs.sextCode))))&&((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual))))&&((this.extrCode == rhs.extrCode)||((this.extrCode!= null)&&this.extrCode.equals(rhs.extrCode))))&&((this.streetCode == rhs.streetCode)||((this.streetCode!= null)&&this.streetCode.equals(rhs.streetCode))))&&((this.rootEntityGuid == rhs.rootEntityGuid)||((this.rootEntityGuid!= null)&&this.rootEntityGuid.equals(rhs.rootEntityGuid))))&&((this.planCode == rhs.planCode)||((this.planCode!= null)&&this.planCode.equals(rhs.planCode))))&&((this.areaCode == rhs.areaCode)||((this.areaCode!= null)&&this.areaCode.equals(rhs.areaCode))))&&((this.aoLevel == rhs.aoLevel)||((this.aoLevel!= null)&&this.aoLevel.equals(rhs.aoLevel))))&&((this.oktmo == rhs.oktmo)||((this.oktmo!= null)&&this.oktmo.equals(rhs.oktmo))))&&((this.subjectCity == rhs.subjectCity)||((this.subjectCity!= null)&&this.subjectCity.equals(rhs.subjectCity))))&&((this.parentGuid == rhs.parentGuid)||((this.parentGuid!= null)&&this.parentGuid.equals(rhs.parentGuid))))&&((this.isAddedManually == rhs.isAddedManually)||((this.isAddedManually!= null)&&this.isAddedManually.equals(rhs.isAddedManually))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.fiasAddrobjGuid == rhs.fiasAddrobjGuid)||((this.fiasAddrobjGuid!= null)&&this.fiasAddrobjGuid.equals(rhs.fiasAddrobjGuid))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.ctarCode == rhs.ctarCode)||((this.ctarCode!= null)&&this.ctarCode.equals(rhs.ctarCode))))&&((this.onApproval == rhs.onApproval)||((this.onApproval!= null)&&this.onApproval.equals(rhs.onApproval))))&&((this.offName == rhs.offName)||((this.offName!= null)&&this.offName.equals(rhs.offName))));
    }

}
