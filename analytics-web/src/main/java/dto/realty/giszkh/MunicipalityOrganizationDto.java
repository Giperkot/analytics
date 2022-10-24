
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
    "guid",
    "createDate",
    "lastEventDate",
    "fullName",
    "shortName",
    "orgAddress",
    "phone",
    "url",
    "organizationType",
    "olsonTZ",
    "okopf",
    "registryOrganizationRootEntityGuid",
    "parentGuid",
    "ogrn"
})
@Generated("jsonschema2pojo")
public class MunicipalityOrganizationDto {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("createDate")
    private Object createDate;
    @JsonProperty("lastEventDate")
    private Object lastEventDate;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("orgAddress")
    private Object orgAddress;
    @JsonProperty("phone")
    private Object phone;
    @JsonProperty("url")
    private Object url;
    @JsonProperty("organizationType")
    private String organizationType;
    @JsonProperty("olsonTZ")
    private Object olsonTZ;
    @JsonProperty("okopf")
    private OkopfDto__1 okopf;
    @JsonProperty("registryOrganizationRootEntityGuid")
    private Object registryOrganizationRootEntityGuid;
    @JsonProperty("parentGuid")
    private Object parentGuid;
    @JsonProperty("ogrn")
    private String ogrn;
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

    @JsonProperty("createDate")
    public Object getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("lastEventDate")
    public Object getLastEventDate() {
        return lastEventDate;
    }

    @JsonProperty("lastEventDate")
    public void setLastEventDate(Object lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("fullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("orgAddress")
    public Object getOrgAddress() {
        return orgAddress;
    }

    @JsonProperty("orgAddress")
    public void setOrgAddress(Object orgAddress) {
        this.orgAddress = orgAddress;
    }

    @JsonProperty("phone")
    public Object getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(Object phone) {
        this.phone = phone;
    }

    @JsonProperty("url")
    public Object getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(Object url) {
        this.url = url;
    }

    @JsonProperty("organizationType")
    public String getOrganizationType() {
        return organizationType;
    }

    @JsonProperty("organizationType")
    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    @JsonProperty("olsonTZ")
    public Object getOlsonTZ() {
        return olsonTZ;
    }

    @JsonProperty("olsonTZ")
    public void setOlsonTZ(Object olsonTZ) {
        this.olsonTZ = olsonTZ;
    }

    @JsonProperty("okopf")
    public OkopfDto__1 getOkopf() {
        return okopf;
    }

    @JsonProperty("okopf")
    public void setOkopf(OkopfDto__1 okopf) {
        this.okopf = okopf;
    }

    @JsonProperty("registryOrganizationRootEntityGuid")
    public Object getRegistryOrganizationRootEntityGuid() {
        return registryOrganizationRootEntityGuid;
    }

    @JsonProperty("registryOrganizationRootEntityGuid")
    public void setRegistryOrganizationRootEntityGuid(Object registryOrganizationRootEntityGuid) {
        this.registryOrganizationRootEntityGuid = registryOrganizationRootEntityGuid;
    }

    @JsonProperty("parentGuid")
    public Object getParentGuid() {
        return parentGuid;
    }

    @JsonProperty("parentGuid")
    public void setParentGuid(Object parentGuid) {
        this.parentGuid = parentGuid;
    }

    @JsonProperty("ogrn")
    public String getOgrn() {
        return ogrn;
    }

    @JsonProperty("ogrn")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
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
        sb.append(MunicipalityOrganizationDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("guid");
        sb.append('=');
        sb.append(((this.guid == null)?"<null>":this.guid));
        sb.append(',');
        sb.append("createDate");
        sb.append('=');
        sb.append(((this.createDate == null)?"<null>":this.createDate));
        sb.append(',');
        sb.append("lastEventDate");
        sb.append('=');
        sb.append(((this.lastEventDate == null)?"<null>":this.lastEventDate));
        sb.append(',');
        sb.append("fullName");
        sb.append('=');
        sb.append(((this.fullName == null)?"<null>":this.fullName));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("orgAddress");
        sb.append('=');
        sb.append(((this.orgAddress == null)?"<null>":this.orgAddress));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null)?"<null>":this.phone));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("organizationType");
        sb.append('=');
        sb.append(((this.organizationType == null)?"<null>":this.organizationType));
        sb.append(',');
        sb.append("olsonTZ");
        sb.append('=');
        sb.append(((this.olsonTZ == null)?"<null>":this.olsonTZ));
        sb.append(',');
        sb.append("okopf");
        sb.append('=');
        sb.append(((this.okopf == null)?"<null>":this.okopf));
        sb.append(',');
        sb.append("registryOrganizationRootEntityGuid");
        sb.append('=');
        sb.append(((this.registryOrganizationRootEntityGuid == null)?"<null>":this.registryOrganizationRootEntityGuid));
        sb.append(',');
        sb.append("parentGuid");
        sb.append('=');
        sb.append(((this.parentGuid == null)?"<null>":this.parentGuid));
        sb.append(',');
        sb.append("ogrn");
        sb.append('=');
        sb.append(((this.ogrn == null)?"<null>":this.ogrn));
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
        result = ((result* 31)+((this.ogrn == null)? 0 :this.ogrn.hashCode()));
        result = ((result* 31)+((this.okopf == null)? 0 :this.okopf.hashCode()));
        result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
        result = ((result* 31)+((this.orgAddress == null)? 0 :this.orgAddress.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.organizationType == null)? 0 :this.organizationType.hashCode()));
        result = ((result* 31)+((this.phone == null)? 0 :this.phone.hashCode()));
        result = ((result* 31)+((this.lastEventDate == null)? 0 :this.lastEventDate.hashCode()));
        result = ((result* 31)+((this.registryOrganizationRootEntityGuid == null)? 0 :this.registryOrganizationRootEntityGuid.hashCode()));
        result = ((result* 31)+((this.parentGuid == null)? 0 :this.parentGuid.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.olsonTZ == null)? 0 :this.olsonTZ.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MunicipalityOrganizationDto) == false) {
            return false;
        }
        MunicipalityOrganizationDto rhs = ((MunicipalityOrganizationDto) other);
        return ((((((((((((((((this.ogrn == rhs.ogrn)||((this.ogrn!= null)&&this.ogrn.equals(rhs.ogrn)))&&((this.okopf == rhs.okopf)||((this.okopf!= null)&&this.okopf.equals(rhs.okopf))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.orgAddress == rhs.orgAddress)||((this.orgAddress!= null)&&this.orgAddress.equals(rhs.orgAddress))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.organizationType == rhs.organizationType)||((this.organizationType!= null)&&this.organizationType.equals(rhs.organizationType))))&&((this.phone == rhs.phone)||((this.phone!= null)&&this.phone.equals(rhs.phone))))&&((this.lastEventDate == rhs.lastEventDate)||((this.lastEventDate!= null)&&this.lastEventDate.equals(rhs.lastEventDate))))&&((this.registryOrganizationRootEntityGuid == rhs.registryOrganizationRootEntityGuid)||((this.registryOrganizationRootEntityGuid!= null)&&this.registryOrganizationRootEntityGuid.equals(rhs.registryOrganizationRootEntityGuid))))&&((this.parentGuid == rhs.parentGuid)||((this.parentGuid!= null)&&this.parentGuid.equals(rhs.parentGuid))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.olsonTZ == rhs.olsonTZ)||((this.olsonTZ!= null)&&this.olsonTZ.equals(rhs.olsonTZ))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))));
    }

}
