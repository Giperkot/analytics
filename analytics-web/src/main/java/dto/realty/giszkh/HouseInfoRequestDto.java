
package dto.realty.giszkh;

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
    "regionCode",
    "cityCode",
    "streetCode",
    "fiasHouseCodeList",
    "estStatus",
    "strStatus",
    "calcCount",
    "houseConditionRefList",
    "houseTypeRefList",
    "houseManagementTypeRefList",
    "cadastreNumber",
    "oktmo",
    "statuses"
})
@Generated("jsonschema2pojo")
public class HouseInfoRequestDto {

    @JsonProperty("regionCode")
    private String regionCode;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("streetCode")
    private String streetCode;
    @JsonProperty("fiasHouseCodeList")
    private List<String> fiasHouseCodeList = new ArrayList<String>();
    @JsonProperty("estStatus")
    private Object estStatus;
    @JsonProperty("strStatus")
    private Object strStatus;
    @JsonProperty("calcCount")
    private Boolean calcCount;
    @JsonProperty("houseConditionRefList")
    private Object houseConditionRefList;
    @JsonProperty("houseTypeRefList")
    private Object houseTypeRefList;
    @JsonProperty("houseManagementTypeRefList")
    private Object houseManagementTypeRefList;
    @JsonProperty("cadastreNumber")
    private Object cadastreNumber;
    @JsonProperty("oktmo")
    private Object oktmo;
    @JsonProperty("statuses")
    private List<String> statuses = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("regionCode")
    public String getRegionCode() {
        return regionCode;
    }

    @JsonProperty("regionCode")
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @JsonProperty("cityCode")
    public String getCityCode() {
        return cityCode;
    }

    @JsonProperty("cityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JsonProperty("streetCode")
    public String getStreetCode() {
        return streetCode;
    }

    @JsonProperty("streetCode")
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    @JsonProperty("fiasHouseCodeList")
    public List<String> getFiasHouseCodeList() {
        return fiasHouseCodeList;
    }

    @JsonProperty("fiasHouseCodeList")
    public void setFiasHouseCodeList(List<String> fiasHouseCodeList) {
        this.fiasHouseCodeList = fiasHouseCodeList;
    }

    @JsonProperty("estStatus")
    public Object getEstStatus() {
        return estStatus;
    }

    @JsonProperty("estStatus")
    public void setEstStatus(Object estStatus) {
        this.estStatus = estStatus;
    }

    @JsonProperty("strStatus")
    public Object getStrStatus() {
        return strStatus;
    }

    @JsonProperty("strStatus")
    public void setStrStatus(Object strStatus) {
        this.strStatus = strStatus;
    }

    @JsonProperty("calcCount")
    public Boolean getCalcCount() {
        return calcCount;
    }

    @JsonProperty("calcCount")
    public void setCalcCount(Boolean calcCount) {
        this.calcCount = calcCount;
    }

    @JsonProperty("houseConditionRefList")
    public Object getHouseConditionRefList() {
        return houseConditionRefList;
    }

    @JsonProperty("houseConditionRefList")
    public void setHouseConditionRefList(Object houseConditionRefList) {
        this.houseConditionRefList = houseConditionRefList;
    }

    @JsonProperty("houseTypeRefList")
    public Object getHouseTypeRefList() {
        return houseTypeRefList;
    }

    @JsonProperty("houseTypeRefList")
    public void setHouseTypeRefList(Object houseTypeRefList) {
        this.houseTypeRefList = houseTypeRefList;
    }

    @JsonProperty("houseManagementTypeRefList")
    public Object getHouseManagementTypeRefList() {
        return houseManagementTypeRefList;
    }

    @JsonProperty("houseManagementTypeRefList")
    public void setHouseManagementTypeRefList(Object houseManagementTypeRefList) {
        this.houseManagementTypeRefList = houseManagementTypeRefList;
    }

    @JsonProperty("cadastreNumber")
    public Object getCadastreNumber() {
        return cadastreNumber;
    }

    @JsonProperty("cadastreNumber")
    public void setCadastreNumber(Object cadastreNumber) {
        this.cadastreNumber = cadastreNumber;
    }

    @JsonProperty("oktmo")
    public Object getOktmo() {
        return oktmo;
    }

    @JsonProperty("oktmo")
    public void setOktmo(Object oktmo) {
        this.oktmo = oktmo;
    }

    @JsonProperty("statuses")
    public List<String> getStatuses() {
        return statuses;
    }

    @JsonProperty("statuses")
    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
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
        sb.append(
                HouseInfoRequestDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("regionCode");
        sb.append('=');
        sb.append(((this.regionCode == null)?"<null>":this.regionCode));
        sb.append(',');
        sb.append("cityCode");
        sb.append('=');
        sb.append(((this.cityCode == null)?"<null>":this.cityCode));
        sb.append(',');
        sb.append("streetCode");
        sb.append('=');
        sb.append(((this.streetCode == null)?"<null>":this.streetCode));
        sb.append(',');
        sb.append("fiasHouseCodeList");
        sb.append('=');
        sb.append(((this.fiasHouseCodeList == null)?"<null>":this.fiasHouseCodeList));
        sb.append(',');
        sb.append("estStatus");
        sb.append('=');
        sb.append(((this.estStatus == null)?"<null>":this.estStatus));
        sb.append(',');
        sb.append("strStatus");
        sb.append('=');
        sb.append(((this.strStatus == null)?"<null>":this.strStatus));
        sb.append(',');
        sb.append("calcCount");
        sb.append('=');
        sb.append(((this.calcCount == null)?"<null>":this.calcCount));
        sb.append(',');
        sb.append("houseConditionRefList");
        sb.append('=');
        sb.append(((this.houseConditionRefList == null)?"<null>":this.houseConditionRefList));
        sb.append(',');
        sb.append("houseTypeRefList");
        sb.append('=');
        sb.append(((this.houseTypeRefList == null)?"<null>":this.houseTypeRefList));
        sb.append(',');
        sb.append("houseManagementTypeRefList");
        sb.append('=');
        sb.append(((this.houseManagementTypeRefList == null)?"<null>":this.houseManagementTypeRefList));
        sb.append(',');
        sb.append("cadastreNumber");
        sb.append('=');
        sb.append(((this.cadastreNumber == null)?"<null>":this.cadastreNumber));
        sb.append(',');
        sb.append("oktmo");
        sb.append('=');
        sb.append(((this.oktmo == null)?"<null>":this.oktmo));
        sb.append(',');
        sb.append("statuses");
        sb.append('=');
        sb.append(((this.statuses == null)?"<null>":this.statuses));
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
        result = ((result* 31)+((this.houseTypeRefList == null)? 0 :this.houseTypeRefList.hashCode()));
        result = ((result* 31)+((this.calcCount == null)? 0 :this.calcCount.hashCode()));
        result = ((result* 31)+((this.cityCode == null)? 0 :this.cityCode.hashCode()));
        result = ((result* 31)+((this.streetCode == null)? 0 :this.streetCode.hashCode()));
        result = ((result* 31)+((this.cadastreNumber == null)? 0 :this.cadastreNumber.hashCode()));
        result = ((result* 31)+((this.regionCode == null)? 0 :this.regionCode.hashCode()));
        result = ((result* 31)+((this.fiasHouseCodeList == null)? 0 :this.fiasHouseCodeList.hashCode()));
        result = ((result* 31)+((this.estStatus == null)? 0 :this.estStatus.hashCode()));
        result = ((result* 31)+((this.oktmo == null)? 0 :this.oktmo.hashCode()));
        result = ((result* 31)+((this.houseManagementTypeRefList == null)? 0 :this.houseManagementTypeRefList.hashCode()));
        result = ((result* 31)+((this.statuses == null)? 0 :this.statuses.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.strStatus == null)? 0 :this.strStatus.hashCode()));
        result = ((result* 31)+((this.houseConditionRefList == null)? 0 :this.houseConditionRefList.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HouseInfoRequestDto) == false) {
            return false;
        }
        HouseInfoRequestDto rhs = ((HouseInfoRequestDto) other);
        return (((((((((((((((this.houseTypeRefList == rhs.houseTypeRefList)||((this.houseTypeRefList!= null)&&this.houseTypeRefList.equals(rhs.houseTypeRefList)))&&((this.calcCount == rhs.calcCount)||((this.calcCount!= null)&&this.calcCount.equals(rhs.calcCount))))&&((this.cityCode == rhs.cityCode)||((this.cityCode!= null)&&this.cityCode.equals(rhs.cityCode))))&&((this.streetCode == rhs.streetCode)||((this.streetCode!= null)&&this.streetCode.equals(rhs.streetCode))))&&((this.cadastreNumber == rhs.cadastreNumber)||((this.cadastreNumber!= null)&&this.cadastreNumber.equals(rhs.cadastreNumber))))&&((this.regionCode == rhs.regionCode)||((this.regionCode!= null)&&this.regionCode.equals(rhs.regionCode))))&&((this.fiasHouseCodeList == rhs.fiasHouseCodeList)||((this.fiasHouseCodeList!= null)&&this.fiasHouseCodeList.equals(rhs.fiasHouseCodeList))))&&((this.estStatus == rhs.estStatus)||((this.estStatus!= null)&&this.estStatus.equals(rhs.estStatus))))&&((this.oktmo == rhs.oktmo)||((this.oktmo!= null)&&this.oktmo.equals(rhs.oktmo))))&&((this.houseManagementTypeRefList == rhs.houseManagementTypeRefList)||((this.houseManagementTypeRefList!= null)&&this.houseManagementTypeRefList.equals(rhs.houseManagementTypeRefList))))&&((this.statuses == rhs.statuses)||((this.statuses!= null)&&this.statuses.equals(rhs.statuses))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.strStatus == rhs.strStatus)||((this.strStatus!= null)&&this.strStatus.equals(rhs.strStatus))))&&((this.houseConditionRefList == rhs.houseConditionRefList)||((this.houseConditionRefList!= null)&&this.houseConditionRefList.equals(rhs.houseConditionRefList))));
    }

}
