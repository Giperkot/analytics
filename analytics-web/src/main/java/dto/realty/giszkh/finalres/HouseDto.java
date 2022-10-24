
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
    "houseGuid",
    "aoGuid",
    "postalCode",
    "houseNumber",
    "buildingNumber",
    "structNumber",
    "additionalName",
    "houseCondition",
    "propertyStateGuid",
    "oktmo",
    "formattedAddress",
    "isAddedManually",
    "onApproval",
    "estStatus",
    "strStatus",
    "fiasHouseGuid",
    "aggregated",
    "childAddresses",
    "parentAggregatedGuid",
    "houseTextAddress",
    "doubles",
    "actualHouseGuid",
    "startDate",
    "endDate"
})
@Generated("jsonschema2pojo")
public class HouseDto {

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
    @JsonProperty("houseGuid")
    private String houseGuid;
    @JsonProperty("aoGuid")
    private String aoGuid;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("houseNumber")
    private String houseNumber;
    @JsonProperty("buildingNumber")
    private Object buildingNumber;
    @JsonProperty("structNumber")
    private Object structNumber;
    @JsonProperty("additionalName")
    private Object additionalName;
    @JsonProperty("houseCondition")
    private Object houseCondition;
    @JsonProperty("propertyStateGuid")
    private Object propertyStateGuid;
    @JsonProperty("oktmo")
    private Object oktmo;
    @JsonProperty("formattedAddress")
    private Object formattedAddress;
    @JsonProperty("isAddedManually")
    private Boolean isAddedManually;
    @JsonProperty("onApproval")
    private Object onApproval;
    @JsonProperty("estStatus")
    private String estStatus;
    @JsonProperty("strStatus")
    private String strStatus;
    @JsonProperty("fiasHouseGuid")
    private Object fiasHouseGuid;
    @JsonProperty("aggregated")
    private Boolean aggregated;
    @JsonProperty("childAddresses")
    private Object childAddresses;
    @JsonProperty("parentAggregatedGuid")
    private Object parentAggregatedGuid;
    @JsonProperty("houseTextAddress")
    private String houseTextAddress;
    @JsonProperty("doubles")
    private Object doubles;
    @JsonProperty("actualHouseGuid")
    private Object actualHouseGuid;
    @JsonProperty("startDate")
    private Object startDate;
    @JsonProperty("endDate")
    private Object endDate;
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

    @JsonProperty("houseGuid")
    public String getHouseGuid() {
        return houseGuid;
    }

    @JsonProperty("houseGuid")
    public void setHouseGuid(String houseGuid) {
        this.houseGuid = houseGuid;
    }

    @JsonProperty("aoGuid")
    public String getAoGuid() {
        return aoGuid;
    }

    @JsonProperty("aoGuid")
    public void setAoGuid(String aoGuid) {
        this.aoGuid = aoGuid;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("houseNumber")
    public String getHouseNumber() {
        return houseNumber;
    }

    @JsonProperty("houseNumber")
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @JsonProperty("buildingNumber")
    public Object getBuildingNumber() {
        return buildingNumber;
    }

    @JsonProperty("buildingNumber")
    public void setBuildingNumber(Object buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @JsonProperty("structNumber")
    public Object getStructNumber() {
        return structNumber;
    }

    @JsonProperty("structNumber")
    public void setStructNumber(Object structNumber) {
        this.structNumber = structNumber;
    }

    @JsonProperty("additionalName")
    public Object getAdditionalName() {
        return additionalName;
    }

    @JsonProperty("additionalName")
    public void setAdditionalName(Object additionalName) {
        this.additionalName = additionalName;
    }

    @JsonProperty("houseCondition")
    public Object getHouseCondition() {
        return houseCondition;
    }

    @JsonProperty("houseCondition")
    public void setHouseCondition(Object houseCondition) {
        this.houseCondition = houseCondition;
    }

    @JsonProperty("propertyStateGuid")
    public Object getPropertyStateGuid() {
        return propertyStateGuid;
    }

    @JsonProperty("propertyStateGuid")
    public void setPropertyStateGuid(Object propertyStateGuid) {
        this.propertyStateGuid = propertyStateGuid;
    }

    @JsonProperty("oktmo")
    public Object getOktmo() {
        return oktmo;
    }

    @JsonProperty("oktmo")
    public void setOktmo(Object oktmo) {
        this.oktmo = oktmo;
    }

    @JsonProperty("formattedAddress")
    public Object getFormattedAddress() {
        return formattedAddress;
    }

    @JsonProperty("formattedAddress")
    public void setFormattedAddress(Object formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @JsonProperty("isAddedManually")
    public Boolean getIsAddedManually() {
        return isAddedManually;
    }

    @JsonProperty("isAddedManually")
    public void setIsAddedManually(Boolean isAddedManually) {
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

    @JsonProperty("estStatus")
    public String getEstStatus() {
        return estStatus;
    }

    @JsonProperty("estStatus")
    public void setEstStatus(String estStatus) {
        this.estStatus = estStatus;
    }

    @JsonProperty("strStatus")
    public String getStrStatus() {
        return strStatus;
    }

    @JsonProperty("strStatus")
    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    @JsonProperty("fiasHouseGuid")
    public Object getFiasHouseGuid() {
        return fiasHouseGuid;
    }

    @JsonProperty("fiasHouseGuid")
    public void setFiasHouseGuid(Object fiasHouseGuid) {
        this.fiasHouseGuid = fiasHouseGuid;
    }

    @JsonProperty("aggregated")
    public Boolean getAggregated() {
        return aggregated;
    }

    @JsonProperty("aggregated")
    public void setAggregated(Boolean aggregated) {
        this.aggregated = aggregated;
    }

    @JsonProperty("childAddresses")
    public Object getChildAddresses() {
        return childAddresses;
    }

    @JsonProperty("childAddresses")
    public void setChildAddresses(Object childAddresses) {
        this.childAddresses = childAddresses;
    }

    @JsonProperty("parentAggregatedGuid")
    public Object getParentAggregatedGuid() {
        return parentAggregatedGuid;
    }

    @JsonProperty("parentAggregatedGuid")
    public void setParentAggregatedGuid(Object parentAggregatedGuid) {
        this.parentAggregatedGuid = parentAggregatedGuid;
    }

    @JsonProperty("houseTextAddress")
    public String getHouseTextAddress() {
        return houseTextAddress;
    }

    @JsonProperty("houseTextAddress")
    public void setHouseTextAddress(String houseTextAddress) {
        this.houseTextAddress = houseTextAddress;
    }

    @JsonProperty("doubles")
    public Object getDoubles() {
        return doubles;
    }

    @JsonProperty("doubles")
    public void setDoubles(Object doubles) {
        this.doubles = doubles;
    }

    @JsonProperty("actualHouseGuid")
    public Object getActualHouseGuid() {
        return actualHouseGuid;
    }

    @JsonProperty("actualHouseGuid")
    public void setActualHouseGuid(Object actualHouseGuid) {
        this.actualHouseGuid = actualHouseGuid;
    }

    @JsonProperty("startDate")
    public Object getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public Object getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Object endDate) {
        this.endDate = endDate;
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
        sb.append(HouseDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("houseGuid");
        sb.append('=');
        sb.append(((this.houseGuid == null)?"<null>":this.houseGuid));
        sb.append(',');
        sb.append("aoGuid");
        sb.append('=');
        sb.append(((this.aoGuid == null)?"<null>":this.aoGuid));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("houseNumber");
        sb.append('=');
        sb.append(((this.houseNumber == null)?"<null>":this.houseNumber));
        sb.append(',');
        sb.append("buildingNumber");
        sb.append('=');
        sb.append(((this.buildingNumber == null)?"<null>":this.buildingNumber));
        sb.append(',');
        sb.append("structNumber");
        sb.append('=');
        sb.append(((this.structNumber == null)?"<null>":this.structNumber));
        sb.append(',');
        sb.append("additionalName");
        sb.append('=');
        sb.append(((this.additionalName == null)?"<null>":this.additionalName));
        sb.append(',');
        sb.append("houseCondition");
        sb.append('=');
        sb.append(((this.houseCondition == null)?"<null>":this.houseCondition));
        sb.append(',');
        sb.append("propertyStateGuid");
        sb.append('=');
        sb.append(((this.propertyStateGuid == null)?"<null>":this.propertyStateGuid));
        sb.append(',');
        sb.append("oktmo");
        sb.append('=');
        sb.append(((this.oktmo == null)?"<null>":this.oktmo));
        sb.append(',');
        sb.append("formattedAddress");
        sb.append('=');
        sb.append(((this.formattedAddress == null)?"<null>":this.formattedAddress));
        sb.append(',');
        sb.append("isAddedManually");
        sb.append('=');
        sb.append(((this.isAddedManually == null)?"<null>":this.isAddedManually));
        sb.append(',');
        sb.append("onApproval");
        sb.append('=');
        sb.append(((this.onApproval == null)?"<null>":this.onApproval));
        sb.append(',');
        sb.append("estStatus");
        sb.append('=');
        sb.append(((this.estStatus == null)?"<null>":this.estStatus));
        sb.append(',');
        sb.append("strStatus");
        sb.append('=');
        sb.append(((this.strStatus == null)?"<null>":this.strStatus));
        sb.append(',');
        sb.append("fiasHouseGuid");
        sb.append('=');
        sb.append(((this.fiasHouseGuid == null)?"<null>":this.fiasHouseGuid));
        sb.append(',');
        sb.append("aggregated");
        sb.append('=');
        sb.append(((this.aggregated == null)?"<null>":this.aggregated));
        sb.append(',');
        sb.append("childAddresses");
        sb.append('=');
        sb.append(((this.childAddresses == null)?"<null>":this.childAddresses));
        sb.append(',');
        sb.append("parentAggregatedGuid");
        sb.append('=');
        sb.append(((this.parentAggregatedGuid == null)?"<null>":this.parentAggregatedGuid));
        sb.append(',');
        sb.append("houseTextAddress");
        sb.append('=');
        sb.append(((this.houseTextAddress == null)?"<null>":this.houseTextAddress));
        sb.append(',');
        sb.append("doubles");
        sb.append('=');
        sb.append(((this.doubles == null)?"<null>":this.doubles));
        sb.append(',');
        sb.append("actualHouseGuid");
        sb.append('=');
        sb.append(((this.actualHouseGuid == null)?"<null>":this.actualHouseGuid));
        sb.append(',');
        sb.append("startDate");
        sb.append('=');
        sb.append(((this.startDate == null)?"<null>":this.startDate));
        sb.append(',');
        sb.append("endDate");
        sb.append('=');
        sb.append(((this.endDate == null)?"<null>":this.endDate));
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
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.endDate == null)? 0 :this.endDate.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.houseNumber == null)? 0 :this.houseNumber.hashCode()));
        result = ((result* 31)+((this.propertyStateGuid == null)? 0 :this.propertyStateGuid.hashCode()));
        result = ((result* 31)+((this.aoGuid == null)? 0 :this.aoGuid.hashCode()));
        result = ((result* 31)+((this.formattedAddress == null)? 0 :this.formattedAddress.hashCode()));
        result = ((result* 31)+((this.fiasHouseGuid == null)? 0 :this.fiasHouseGuid.hashCode()));
        result = ((result* 31)+((this.parentAggregatedGuid == null)? 0 :this.parentAggregatedGuid.hashCode()));
        result = ((result* 31)+((this.buildingNumber == null)? 0 :this.buildingNumber.hashCode()));
        result = ((result* 31)+((this.houseGuid == null)? 0 :this.houseGuid.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        result = ((result* 31)+((this.actual == null)? 0 :this.actual.hashCode()));
        result = ((result* 31)+((this.houseTextAddress == null)? 0 :this.houseTextAddress.hashCode()));
        result = ((result* 31)+((this.aggregated == null)? 0 :this.aggregated.hashCode()));
        result = ((result* 31)+((this.rootEntityGuid == null)? 0 :this.rootEntityGuid.hashCode()));
        result = ((result* 31)+((this.structNumber == null)? 0 :this.structNumber.hashCode()));
        result = ((result* 31)+((this.houseCondition == null)? 0 :this.houseCondition.hashCode()));
        result = ((result* 31)+((this.oktmo == null)? 0 :this.oktmo.hashCode()));
        result = ((result* 31)+((this.estStatus == null)? 0 :this.estStatus.hashCode()));
        result = ((result* 31)+((this.doubles == null)? 0 :this.doubles.hashCode()));
        result = ((result* 31)+((this.isAddedManually == null)? 0 :this.isAddedManually.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.strStatus == null)? 0 :this.strStatus.hashCode()));
        result = ((result* 31)+((this.childAddresses == null)? 0 :this.childAddresses.hashCode()));
        result = ((result* 31)+((this.additionalName == null)? 0 :this.additionalName.hashCode()));
        result = ((result* 31)+((this.onApproval == null)? 0 :this.onApproval.hashCode()));
        result = ((result* 31)+((this.startDate == null)? 0 :this.startDate.hashCode()));
        result = ((result* 31)+((this.actualHouseGuid == null)? 0 :this.actualHouseGuid.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HouseDto) == false) {
            return false;
        }
        HouseDto rhs = ((HouseDto) other);
        return ((((((((((((((((((((((((((((((((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code)))&&((this.endDate == rhs.endDate)||((this.endDate!= null)&&this.endDate.equals(rhs.endDate))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&((this.houseNumber == rhs.houseNumber)||((this.houseNumber!= null)&&this.houseNumber.equals(rhs.houseNumber))))&&((this.propertyStateGuid == rhs.propertyStateGuid)||((this.propertyStateGuid!= null)&&this.propertyStateGuid.equals(rhs.propertyStateGuid))))&&((this.aoGuid == rhs.aoGuid)||((this.aoGuid!= null)&&this.aoGuid.equals(rhs.aoGuid))))&&((this.formattedAddress == rhs.formattedAddress)||((this.formattedAddress!= null)&&this.formattedAddress.equals(rhs.formattedAddress))))&&((this.fiasHouseGuid == rhs.fiasHouseGuid)||((this.fiasHouseGuid!= null)&&this.fiasHouseGuid.equals(rhs.fiasHouseGuid))))&&((this.parentAggregatedGuid == rhs.parentAggregatedGuid)||((this.parentAggregatedGuid!= null)&&this.parentAggregatedGuid.equals(rhs.parentAggregatedGuid))))&&((this.buildingNumber == rhs.buildingNumber)||((this.buildingNumber!= null)&&this.buildingNumber.equals(rhs.buildingNumber))))&&((this.houseGuid == rhs.houseGuid)||((this.houseGuid!= null)&&this.houseGuid.equals(rhs.houseGuid))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual))))&&((this.houseTextAddress == rhs.houseTextAddress)||((this.houseTextAddress!= null)&&this.houseTextAddress.equals(rhs.houseTextAddress))))&&((this.aggregated == rhs.aggregated)||((this.aggregated!= null)&&this.aggregated.equals(rhs.aggregated))))&&((this.rootEntityGuid == rhs.rootEntityGuid)||((this.rootEntityGuid!= null)&&this.rootEntityGuid.equals(rhs.rootEntityGuid))))&&((this.structNumber == rhs.structNumber)||((this.structNumber!= null)&&this.structNumber.equals(rhs.structNumber))))&&((this.houseCondition == rhs.houseCondition)||((this.houseCondition!= null)&&this.houseCondition.equals(rhs.houseCondition))))&&((this.oktmo == rhs.oktmo)||((this.oktmo!= null)&&this.oktmo.equals(rhs.oktmo))))&&((this.estStatus == rhs.estStatus)||((this.estStatus!= null)&&this.estStatus.equals(rhs.estStatus))))&&((this.doubles == rhs.doubles)||((this.doubles!= null)&&this.doubles.equals(rhs.doubles))))&&((this.isAddedManually == rhs.isAddedManually)||((this.isAddedManually!= null)&&this.isAddedManually.equals(rhs.isAddedManually))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.strStatus == rhs.strStatus)||((this.strStatus!= null)&&this.strStatus.equals(rhs.strStatus))))&&((this.childAddresses == rhs.childAddresses)||((this.childAddresses!= null)&&this.childAddresses.equals(rhs.childAddresses))))&&((this.additionalName == rhs.additionalName)||((this.additionalName!= null)&&this.additionalName.equals(rhs.additionalName))))&&((this.onApproval == rhs.onApproval)||((this.onApproval!= null)&&this.onApproval.equals(rhs.onApproval))))&&((this.startDate == rhs.startDate)||((this.startDate!= null)&&this.startDate.equals(rhs.startDate))))&&((this.actualHouseGuid == rhs.actualHouseGuid)||((this.actualHouseGuid!= null)&&this.actualHouseGuid.equals(rhs.actualHouseGuid))));
    }

}
