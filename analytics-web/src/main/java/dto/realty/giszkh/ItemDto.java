
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
    "rootGuid",
    "versionNumber",
    "lastUpdateUnixTime",
    "lastUpdateDate",
    "createDate",
    "readOnly",
    "active",
    "asyncProcessing",
    "asyncProcessType",
    "status",
    "cancellationReasonCode",
    "cancellationComment",
    "cancellationDate",
    "address",
    "houseGuid",
    "planSeries",
    "planType",
    "buildingYear",
    "operationYear",
    "residentialPremiseCount",
    "nonResidentialPremiseCount",
    "residentialPremiseActualCount",
    "nonResidentialPremiseActualCount",
    "residentialPremiseConfirmedCount",
    "nonResidentialPremiseConfirmedCount",
    "managementOrganization",
    "municipalityOrganization",
    "maxFloorCount",
    "deterioration",
    "houseType",
    "totalSquare",
    "residentialSquare",
    "accountCount",
    "houseCondition",
    "devices",
    "accountRefs",
    "apartmentsWithoutAccountCount",
    "ableEnterDeviceMetering",
    "houseManagementType",
    "oktmo",
    "canAdd",
    "hasMunicipalServices",
    "hasDummyShare",
    "hasShare",
    "userOrganizationRole",
    "blockedHouse",
    "cadastreNumber",
    "oldCadastreNumber",
    "canCancel",
    "cancellationReason",
    "hasIncorrectObjects",
    "hasActiveDevices",
    "canReadShare",
    "regionProperty",
    "municipalProperty",
    "hostelType",
    "chiefFirstName",
    "chiefLastName",
    "chiefMiddleName",
    "houseCancelled",
    "approved",
    "canceledOrAsyncProcessed"
})
@Generated("jsonschema2pojo")
public class ItemDto {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("rootGuid")
    private Object rootGuid;
    @JsonProperty("versionNumber")
    private Object versionNumber;
    @JsonProperty("lastUpdateUnixTime")
    private Object lastUpdateUnixTime;
    @JsonProperty("lastUpdateDate")
    private Object lastUpdateDate;
    @JsonProperty("createDate")
    private Object createDate;
    @JsonProperty("readOnly")
    private Object readOnly;
    @JsonProperty("active")
    private Object active;
    @JsonProperty("asyncProcessing")
    private Object asyncProcessing;
    @JsonProperty("asyncProcessType")
    private Object asyncProcessType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("cancellationReasonCode")
    private Object cancellationReasonCode;
    @JsonProperty("cancellationComment")
    private Object cancellationComment;
    @JsonProperty("cancellationDate")
    private Object cancellationDate;
    @JsonProperty("address")
    private AddressDto address;
    @JsonProperty("houseGuid")
    private String houseGuid;
    @JsonProperty("planSeries")
    private String planSeries;
    @JsonProperty("planType")
    private Object planType;
    @JsonProperty("buildingYear")
    private String buildingYear;
    @JsonProperty("operationYear")
    private Integer operationYear;
    @JsonProperty("residentialPremiseCount")
    private String residentialPremiseCount;
    @JsonProperty("nonResidentialPremiseCount")
    private String nonResidentialPremiseCount;
    @JsonProperty("residentialPremiseActualCount")
    private Object residentialPremiseActualCount;
    @JsonProperty("nonResidentialPremiseActualCount")
    private Object nonResidentialPremiseActualCount;
    @JsonProperty("residentialPremiseConfirmedCount")
    private Object residentialPremiseConfirmedCount;
    @JsonProperty("nonResidentialPremiseConfirmedCount")
    private Object nonResidentialPremiseConfirmedCount;
    @JsonProperty("managementOrganization")
    private ManagementOrganizationDto managementOrganization;
    @JsonProperty("municipalityOrganization")
    private MunicipalityOrganizationDto municipalityOrganization;
    @JsonProperty("maxFloorCount")
    private String maxFloorCount;
    @JsonProperty("deterioration")
    private String deterioration;
    @JsonProperty("houseType")
    private HouseTypeDto houseType;
    @JsonProperty("totalSquare")
    private Object totalSquare;
    @JsonProperty("residentialSquare")
    private Object residentialSquare;
    @JsonProperty("accountCount")
    private Object accountCount;
    @JsonProperty("houseCondition")
    private HouseConditionDto houseCondition;
    @JsonProperty("devices")
    private Object devices;
    @JsonProperty("accountRefs")
    private Object accountRefs;
    @JsonProperty("apartmentsWithoutAccountCount")
    private Object apartmentsWithoutAccountCount;
    @JsonProperty("ableEnterDeviceMetering")
    private Boolean ableEnterDeviceMetering;
    @JsonProperty("houseManagementType")
    private Object houseManagementType;
    @JsonProperty("oktmo")
    private OktmoDto oktmo;
    @JsonProperty("canAdd")
    private Object canAdd;
    @JsonProperty("hasMunicipalServices")
    private Object hasMunicipalServices;
    @JsonProperty("hasDummyShare")
    private Object hasDummyShare;
    @JsonProperty("hasShare")
    private Object hasShare;
    @JsonProperty("userOrganizationRole")
    private Object userOrganizationRole;
    @JsonProperty("blockedHouse")
    private Boolean blockedHouse;
    @JsonProperty("cadastreNumber")
    private Object cadastreNumber;
    @JsonProperty("oldCadastreNumber")
    private Object oldCadastreNumber;
    @JsonProperty("canCancel")
    private Object canCancel;
    @JsonProperty("cancellationReason")
    private Object cancellationReason;
    @JsonProperty("hasIncorrectObjects")
    private Object hasIncorrectObjects;
    @JsonProperty("hasActiveDevices")
    private Object hasActiveDevices;
    @JsonProperty("canReadShare")
    private Object canReadShare;
    @JsonProperty("regionProperty")
    private Object regionProperty;
    @JsonProperty("municipalProperty")
    private Object municipalProperty;
    @JsonProperty("hostelType")
    private Object hostelType;
    @JsonProperty("chiefFirstName")
    private Object chiefFirstName;
    @JsonProperty("chiefLastName")
    private Object chiefLastName;
    @JsonProperty("chiefMiddleName")
    private Object chiefMiddleName;
    @JsonProperty("houseCancelled")
    private Boolean houseCancelled;
    @JsonProperty("approved")
    private Boolean approved;
    @JsonProperty("canceledOrAsyncProcessed")
    private Boolean canceledOrAsyncProcessed;
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

    @JsonProperty("rootGuid")
    public Object getRootGuid() {
        return rootGuid;
    }

    @JsonProperty("rootGuid")
    public void setRootGuid(Object rootGuid) {
        this.rootGuid = rootGuid;
    }

    @JsonProperty("versionNumber")
    public Object getVersionNumber() {
        return versionNumber;
    }

    @JsonProperty("versionNumber")
    public void setVersionNumber(Object versionNumber) {
        this.versionNumber = versionNumber;
    }

    @JsonProperty("lastUpdateUnixTime")
    public Object getLastUpdateUnixTime() {
        return lastUpdateUnixTime;
    }

    @JsonProperty("lastUpdateUnixTime")
    public void setLastUpdateUnixTime(Object lastUpdateUnixTime) {
        this.lastUpdateUnixTime = lastUpdateUnixTime;
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

    @JsonProperty("readOnly")
    public Object getReadOnly() {
        return readOnly;
    }

    @JsonProperty("readOnly")
    public void setReadOnly(Object readOnly) {
        this.readOnly = readOnly;
    }

    @JsonProperty("active")
    public Object getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Object active) {
        this.active = active;
    }

    @JsonProperty("asyncProcessing")
    public Object getAsyncProcessing() {
        return asyncProcessing;
    }

    @JsonProperty("asyncProcessing")
    public void setAsyncProcessing(Object asyncProcessing) {
        this.asyncProcessing = asyncProcessing;
    }

    @JsonProperty("asyncProcessType")
    public Object getAsyncProcessType() {
        return asyncProcessType;
    }

    @JsonProperty("asyncProcessType")
    public void setAsyncProcessType(Object asyncProcessType) {
        this.asyncProcessType = asyncProcessType;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("cancellationReasonCode")
    public Object getCancellationReasonCode() {
        return cancellationReasonCode;
    }

    @JsonProperty("cancellationReasonCode")
    public void setCancellationReasonCode(Object cancellationReasonCode) {
        this.cancellationReasonCode = cancellationReasonCode;
    }

    @JsonProperty("cancellationComment")
    public Object getCancellationComment() {
        return cancellationComment;
    }

    @JsonProperty("cancellationComment")
    public void setCancellationComment(Object cancellationComment) {
        this.cancellationComment = cancellationComment;
    }

    @JsonProperty("cancellationDate")
    public Object getCancellationDate() {
        return cancellationDate;
    }

    @JsonProperty("cancellationDate")
    public void setCancellationDate(Object cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    @JsonProperty("address")
    public AddressDto getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @JsonProperty("houseGuid")
    public String getHouseGuid() {
        return houseGuid;
    }

    @JsonProperty("houseGuid")
    public void setHouseGuid(String houseGuid) {
        this.houseGuid = houseGuid;
    }

    @JsonProperty("planSeries")
    public String getPlanSeries() {
        return planSeries;
    }

    @JsonProperty("planSeries")
    public void setPlanSeries(String planSeries) {
        this.planSeries = planSeries;
    }

    @JsonProperty("planType")
    public Object getPlanType() {
        return planType;
    }

    @JsonProperty("planType")
    public void setPlanType(Object planType) {
        this.planType = planType;
    }

    @JsonProperty("buildingYear")
    public String getBuildingYear() {
        return buildingYear;
    }

    @JsonProperty("buildingYear")
    public void setBuildingYear(String buildingYear) {
        this.buildingYear = buildingYear;
    }

    @JsonProperty("operationYear")
    public Integer getOperationYear() {
        return operationYear;
    }

    @JsonProperty("operationYear")
    public void setOperationYear(Integer operationYear) {
        this.operationYear = operationYear;
    }

    @JsonProperty("residentialPremiseCount")
    public String getResidentialPremiseCount() {
        return residentialPremiseCount;
    }

    @JsonProperty("residentialPremiseCount")
    public void setResidentialPremiseCount(String residentialPremiseCount) {
        this.residentialPremiseCount = residentialPremiseCount;
    }

    @JsonProperty("nonResidentialPremiseCount")
    public String getNonResidentialPremiseCount() {
        return nonResidentialPremiseCount;
    }

    @JsonProperty("nonResidentialPremiseCount")
    public void setNonResidentialPremiseCount(String nonResidentialPremiseCount) {
        this.nonResidentialPremiseCount = nonResidentialPremiseCount;
    }

    @JsonProperty("residentialPremiseActualCount")
    public Object getResidentialPremiseActualCount() {
        return residentialPremiseActualCount;
    }

    @JsonProperty("residentialPremiseActualCount")
    public void setResidentialPremiseActualCount(Object residentialPremiseActualCount) {
        this.residentialPremiseActualCount = residentialPremiseActualCount;
    }

    @JsonProperty("nonResidentialPremiseActualCount")
    public Object getNonResidentialPremiseActualCount() {
        return nonResidentialPremiseActualCount;
    }

    @JsonProperty("nonResidentialPremiseActualCount")
    public void setNonResidentialPremiseActualCount(Object nonResidentialPremiseActualCount) {
        this.nonResidentialPremiseActualCount = nonResidentialPremiseActualCount;
    }

    @JsonProperty("residentialPremiseConfirmedCount")
    public Object getResidentialPremiseConfirmedCount() {
        return residentialPremiseConfirmedCount;
    }

    @JsonProperty("residentialPremiseConfirmedCount")
    public void setResidentialPremiseConfirmedCount(Object residentialPremiseConfirmedCount) {
        this.residentialPremiseConfirmedCount = residentialPremiseConfirmedCount;
    }

    @JsonProperty("nonResidentialPremiseConfirmedCount")
    public Object getNonResidentialPremiseConfirmedCount() {
        return nonResidentialPremiseConfirmedCount;
    }

    @JsonProperty("nonResidentialPremiseConfirmedCount")
    public void setNonResidentialPremiseConfirmedCount(Object nonResidentialPremiseConfirmedCount) {
        this.nonResidentialPremiseConfirmedCount = nonResidentialPremiseConfirmedCount;
    }

    @JsonProperty("managementOrganization")
    public ManagementOrganizationDto getManagementOrganization() {
        return managementOrganization;
    }

    @JsonProperty("managementOrganization")
    public void setManagementOrganization(ManagementOrganizationDto managementOrganization) {
        this.managementOrganization = managementOrganization;
    }

    @JsonProperty("municipalityOrganization")
    public MunicipalityOrganizationDto getMunicipalityOrganization() {
        return municipalityOrganization;
    }

    @JsonProperty("municipalityOrganization")
    public void setMunicipalityOrganization(MunicipalityOrganizationDto municipalityOrganization) {
        this.municipalityOrganization = municipalityOrganization;
    }

    @JsonProperty("maxFloorCount")
    public String getMaxFloorCount() {
        return maxFloorCount;
    }

    @JsonProperty("maxFloorCount")
    public void setMaxFloorCount(String maxFloorCount) {
        this.maxFloorCount = maxFloorCount;
    }

    @JsonProperty("deterioration")
    public String getDeterioration() {
        return deterioration;
    }

    @JsonProperty("deterioration")
    public void setDeterioration(String deterioration) {
        this.deterioration = deterioration;
    }

    @JsonProperty("houseType")
    public HouseTypeDto getHouseType() {
        return houseType;
    }

    @JsonProperty("houseType")
    public void setHouseType(HouseTypeDto houseType) {
        this.houseType = houseType;
    }

    @JsonProperty("totalSquare")
    public Object getTotalSquare() {
        return totalSquare;
    }

    @JsonProperty("totalSquare")
    public void setTotalSquare(Object totalSquare) {
        this.totalSquare = totalSquare;
    }

    @JsonProperty("residentialSquare")
    public Object getResidentialSquare() {
        return residentialSquare;
    }

    @JsonProperty("residentialSquare")
    public void setResidentialSquare(Object residentialSquare) {
        this.residentialSquare = residentialSquare;
    }

    @JsonProperty("accountCount")
    public Object getAccountCount() {
        return accountCount;
    }

    @JsonProperty("accountCount")
    public void setAccountCount(Object accountCount) {
        this.accountCount = accountCount;
    }

    @JsonProperty("houseCondition")
    public HouseConditionDto getHouseCondition() {
        return houseCondition;
    }

    @JsonProperty("houseCondition")
    public void setHouseCondition(HouseConditionDto houseCondition) {
        this.houseCondition = houseCondition;
    }

    @JsonProperty("devices")
    public Object getDevices() {
        return devices;
    }

    @JsonProperty("devices")
    public void setDevices(Object devices) {
        this.devices = devices;
    }

    @JsonProperty("accountRefs")
    public Object getAccountRefs() {
        return accountRefs;
    }

    @JsonProperty("accountRefs")
    public void setAccountRefs(Object accountRefs) {
        this.accountRefs = accountRefs;
    }

    @JsonProperty("apartmentsWithoutAccountCount")
    public Object getApartmentsWithoutAccountCount() {
        return apartmentsWithoutAccountCount;
    }

    @JsonProperty("apartmentsWithoutAccountCount")
    public void setApartmentsWithoutAccountCount(Object apartmentsWithoutAccountCount) {
        this.apartmentsWithoutAccountCount = apartmentsWithoutAccountCount;
    }

    @JsonProperty("ableEnterDeviceMetering")
    public Boolean getAbleEnterDeviceMetering() {
        return ableEnterDeviceMetering;
    }

    @JsonProperty("ableEnterDeviceMetering")
    public void setAbleEnterDeviceMetering(Boolean ableEnterDeviceMetering) {
        this.ableEnterDeviceMetering = ableEnterDeviceMetering;
    }

    @JsonProperty("houseManagementType")
    public Object getHouseManagementType() {
        return houseManagementType;
    }

    @JsonProperty("houseManagementType")
    public void setHouseManagementType(Object houseManagementType) {
        this.houseManagementType = houseManagementType;
    }

    @JsonProperty("oktmo")
    public OktmoDto getOktmo() {
        return oktmo;
    }

    @JsonProperty("oktmo")
    public void setOktmo(OktmoDto oktmo) {
        this.oktmo = oktmo;
    }

    @JsonProperty("canAdd")
    public Object getCanAdd() {
        return canAdd;
    }

    @JsonProperty("canAdd")
    public void setCanAdd(Object canAdd) {
        this.canAdd = canAdd;
    }

    @JsonProperty("hasMunicipalServices")
    public Object getHasMunicipalServices() {
        return hasMunicipalServices;
    }

    @JsonProperty("hasMunicipalServices")
    public void setHasMunicipalServices(Object hasMunicipalServices) {
        this.hasMunicipalServices = hasMunicipalServices;
    }

    @JsonProperty("hasDummyShare")
    public Object getHasDummyShare() {
        return hasDummyShare;
    }

    @JsonProperty("hasDummyShare")
    public void setHasDummyShare(Object hasDummyShare) {
        this.hasDummyShare = hasDummyShare;
    }

    @JsonProperty("hasShare")
    public Object getHasShare() {
        return hasShare;
    }

    @JsonProperty("hasShare")
    public void setHasShare(Object hasShare) {
        this.hasShare = hasShare;
    }

    @JsonProperty("userOrganizationRole")
    public Object getUserOrganizationRole() {
        return userOrganizationRole;
    }

    @JsonProperty("userOrganizationRole")
    public void setUserOrganizationRole(Object userOrganizationRole) {
        this.userOrganizationRole = userOrganizationRole;
    }

    @JsonProperty("blockedHouse")
    public Boolean getBlockedHouse() {
        return blockedHouse;
    }

    @JsonProperty("blockedHouse")
    public void setBlockedHouse(Boolean blockedHouse) {
        this.blockedHouse = blockedHouse;
    }

    @JsonProperty("cadastreNumber")
    public Object getCadastreNumber() {
        return cadastreNumber;
    }

    @JsonProperty("cadastreNumber")
    public void setCadastreNumber(Object cadastreNumber) {
        this.cadastreNumber = cadastreNumber;
    }

    @JsonProperty("oldCadastreNumber")
    public Object getOldCadastreNumber() {
        return oldCadastreNumber;
    }

    @JsonProperty("oldCadastreNumber")
    public void setOldCadastreNumber(Object oldCadastreNumber) {
        this.oldCadastreNumber = oldCadastreNumber;
    }

    @JsonProperty("canCancel")
    public Object getCanCancel() {
        return canCancel;
    }

    @JsonProperty("canCancel")
    public void setCanCancel(Object canCancel) {
        this.canCancel = canCancel;
    }

    @JsonProperty("cancellationReason")
    public Object getCancellationReason() {
        return cancellationReason;
    }

    @JsonProperty("cancellationReason")
    public void setCancellationReason(Object cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    @JsonProperty("hasIncorrectObjects")
    public Object getHasIncorrectObjects() {
        return hasIncorrectObjects;
    }

    @JsonProperty("hasIncorrectObjects")
    public void setHasIncorrectObjects(Object hasIncorrectObjects) {
        this.hasIncorrectObjects = hasIncorrectObjects;
    }

    @JsonProperty("hasActiveDevices")
    public Object getHasActiveDevices() {
        return hasActiveDevices;
    }

    @JsonProperty("hasActiveDevices")
    public void setHasActiveDevices(Object hasActiveDevices) {
        this.hasActiveDevices = hasActiveDevices;
    }

    @JsonProperty("canReadShare")
    public Object getCanReadShare() {
        return canReadShare;
    }

    @JsonProperty("canReadShare")
    public void setCanReadShare(Object canReadShare) {
        this.canReadShare = canReadShare;
    }

    @JsonProperty("regionProperty")
    public Object getRegionProperty() {
        return regionProperty;
    }

    @JsonProperty("regionProperty")
    public void setRegionProperty(Object regionProperty) {
        this.regionProperty = regionProperty;
    }

    @JsonProperty("municipalProperty")
    public Object getMunicipalProperty() {
        return municipalProperty;
    }

    @JsonProperty("municipalProperty")
    public void setMunicipalProperty(Object municipalProperty) {
        this.municipalProperty = municipalProperty;
    }

    @JsonProperty("hostelType")
    public Object getHostelType() {
        return hostelType;
    }

    @JsonProperty("hostelType")
    public void setHostelType(Object hostelType) {
        this.hostelType = hostelType;
    }

    @JsonProperty("chiefFirstName")
    public Object getChiefFirstName() {
        return chiefFirstName;
    }

    @JsonProperty("chiefFirstName")
    public void setChiefFirstName(Object chiefFirstName) {
        this.chiefFirstName = chiefFirstName;
    }

    @JsonProperty("chiefLastName")
    public Object getChiefLastName() {
        return chiefLastName;
    }

    @JsonProperty("chiefLastName")
    public void setChiefLastName(Object chiefLastName) {
        this.chiefLastName = chiefLastName;
    }

    @JsonProperty("chiefMiddleName")
    public Object getChiefMiddleName() {
        return chiefMiddleName;
    }

    @JsonProperty("chiefMiddleName")
    public void setChiefMiddleName(Object chiefMiddleName) {
        this.chiefMiddleName = chiefMiddleName;
    }

    @JsonProperty("houseCancelled")
    public Boolean getHouseCancelled() {
        return houseCancelled;
    }

    @JsonProperty("houseCancelled")
    public void setHouseCancelled(Boolean houseCancelled) {
        this.houseCancelled = houseCancelled;
    }

    @JsonProperty("approved")
    public Boolean getApproved() {
        return approved;
    }

    @JsonProperty("approved")
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @JsonProperty("canceledOrAsyncProcessed")
    public Boolean getCanceledOrAsyncProcessed() {
        return canceledOrAsyncProcessed;
    }

    @JsonProperty("canceledOrAsyncProcessed")
    public void setCanceledOrAsyncProcessed(Boolean canceledOrAsyncProcessed) {
        this.canceledOrAsyncProcessed = canceledOrAsyncProcessed;
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
        sb.append(ItemDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("guid");
        sb.append('=');
        sb.append(((this.guid == null)?"<null>":this.guid));
        sb.append(',');
        sb.append("rootGuid");
        sb.append('=');
        sb.append(((this.rootGuid == null)?"<null>":this.rootGuid));
        sb.append(',');
        sb.append("versionNumber");
        sb.append('=');
        sb.append(((this.versionNumber == null)?"<null>":this.versionNumber));
        sb.append(',');
        sb.append("lastUpdateUnixTime");
        sb.append('=');
        sb.append(((this.lastUpdateUnixTime == null)?"<null>":this.lastUpdateUnixTime));
        sb.append(',');
        sb.append("lastUpdateDate");
        sb.append('=');
        sb.append(((this.lastUpdateDate == null)?"<null>":this.lastUpdateDate));
        sb.append(',');
        sb.append("createDate");
        sb.append('=');
        sb.append(((this.createDate == null)?"<null>":this.createDate));
        sb.append(',');
        sb.append("readOnly");
        sb.append('=');
        sb.append(((this.readOnly == null)?"<null>":this.readOnly));
        sb.append(',');
        sb.append("active");
        sb.append('=');
        sb.append(((this.active == null)?"<null>":this.active));
        sb.append(',');
        sb.append("asyncProcessing");
        sb.append('=');
        sb.append(((this.asyncProcessing == null)?"<null>":this.asyncProcessing));
        sb.append(',');
        sb.append("asyncProcessType");
        sb.append('=');
        sb.append(((this.asyncProcessType == null)?"<null>":this.asyncProcessType));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("cancellationReasonCode");
        sb.append('=');
        sb.append(((this.cancellationReasonCode == null)?"<null>":this.cancellationReasonCode));
        sb.append(',');
        sb.append("cancellationComment");
        sb.append('=');
        sb.append(((this.cancellationComment == null)?"<null>":this.cancellationComment));
        sb.append(',');
        sb.append("cancellationDate");
        sb.append('=');
        sb.append(((this.cancellationDate == null)?"<null>":this.cancellationDate));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("houseGuid");
        sb.append('=');
        sb.append(((this.houseGuid == null)?"<null>":this.houseGuid));
        sb.append(',');
        sb.append("planSeries");
        sb.append('=');
        sb.append(((this.planSeries == null)?"<null>":this.planSeries));
        sb.append(',');
        sb.append("planType");
        sb.append('=');
        sb.append(((this.planType == null)?"<null>":this.planType));
        sb.append(',');
        sb.append("buildingYear");
        sb.append('=');
        sb.append(((this.buildingYear == null)?"<null>":this.buildingYear));
        sb.append(',');
        sb.append("operationYear");
        sb.append('=');
        sb.append(((this.operationYear == null)?"<null>":this.operationYear));
        sb.append(',');
        sb.append("residentialPremiseCount");
        sb.append('=');
        sb.append(((this.residentialPremiseCount == null)?"<null>":this.residentialPremiseCount));
        sb.append(',');
        sb.append("nonResidentialPremiseCount");
        sb.append('=');
        sb.append(((this.nonResidentialPremiseCount == null)?"<null>":this.nonResidentialPremiseCount));
        sb.append(',');
        sb.append("residentialPremiseActualCount");
        sb.append('=');
        sb.append(((this.residentialPremiseActualCount == null)?"<null>":this.residentialPremiseActualCount));
        sb.append(',');
        sb.append("nonResidentialPremiseActualCount");
        sb.append('=');
        sb.append(((this.nonResidentialPremiseActualCount == null)?"<null>":this.nonResidentialPremiseActualCount));
        sb.append(',');
        sb.append("residentialPremiseConfirmedCount");
        sb.append('=');
        sb.append(((this.residentialPremiseConfirmedCount == null)?"<null>":this.residentialPremiseConfirmedCount));
        sb.append(',');
        sb.append("nonResidentialPremiseConfirmedCount");
        sb.append('=');
        sb.append(((this.nonResidentialPremiseConfirmedCount == null)?"<null>":this.nonResidentialPremiseConfirmedCount));
        sb.append(',');
        sb.append("managementOrganization");
        sb.append('=');
        sb.append(((this.managementOrganization == null)?"<null>":this.managementOrganization));
        sb.append(',');
        sb.append("municipalityOrganization");
        sb.append('=');
        sb.append(((this.municipalityOrganization == null)?"<null>":this.municipalityOrganization));
        sb.append(',');
        sb.append("maxFloorCount");
        sb.append('=');
        sb.append(((this.maxFloorCount == null)?"<null>":this.maxFloorCount));
        sb.append(',');
        sb.append("deterioration");
        sb.append('=');
        sb.append(((this.deterioration == null)?"<null>":this.deterioration));
        sb.append(',');
        sb.append("houseType");
        sb.append('=');
        sb.append(((this.houseType == null)?"<null>":this.houseType));
        sb.append(',');
        sb.append("totalSquare");
        sb.append('=');
        sb.append(((this.totalSquare == null)?"<null>":this.totalSquare));
        sb.append(',');
        sb.append("residentialSquare");
        sb.append('=');
        sb.append(((this.residentialSquare == null)?"<null>":this.residentialSquare));
        sb.append(',');
        sb.append("accountCount");
        sb.append('=');
        sb.append(((this.accountCount == null)?"<null>":this.accountCount));
        sb.append(',');
        sb.append("houseCondition");
        sb.append('=');
        sb.append(((this.houseCondition == null)?"<null>":this.houseCondition));
        sb.append(',');
        sb.append("devices");
        sb.append('=');
        sb.append(((this.devices == null)?"<null>":this.devices));
        sb.append(',');
        sb.append("accountRefs");
        sb.append('=');
        sb.append(((this.accountRefs == null)?"<null>":this.accountRefs));
        sb.append(',');
        sb.append("apartmentsWithoutAccountCount");
        sb.append('=');
        sb.append(((this.apartmentsWithoutAccountCount == null)?"<null>":this.apartmentsWithoutAccountCount));
        sb.append(',');
        sb.append("ableEnterDeviceMetering");
        sb.append('=');
        sb.append(((this.ableEnterDeviceMetering == null)?"<null>":this.ableEnterDeviceMetering));
        sb.append(',');
        sb.append("houseManagementType");
        sb.append('=');
        sb.append(((this.houseManagementType == null)?"<null>":this.houseManagementType));
        sb.append(',');
        sb.append("oktmo");
        sb.append('=');
        sb.append(((this.oktmo == null)?"<null>":this.oktmo));
        sb.append(',');
        sb.append("canAdd");
        sb.append('=');
        sb.append(((this.canAdd == null)?"<null>":this.canAdd));
        sb.append(',');
        sb.append("hasMunicipalServices");
        sb.append('=');
        sb.append(((this.hasMunicipalServices == null)?"<null>":this.hasMunicipalServices));
        sb.append(',');
        sb.append("hasDummyShare");
        sb.append('=');
        sb.append(((this.hasDummyShare == null)?"<null>":this.hasDummyShare));
        sb.append(',');
        sb.append("hasShare");
        sb.append('=');
        sb.append(((this.hasShare == null)?"<null>":this.hasShare));
        sb.append(',');
        sb.append("userOrganizationRole");
        sb.append('=');
        sb.append(((this.userOrganizationRole == null)?"<null>":this.userOrganizationRole));
        sb.append(',');
        sb.append("blockedHouse");
        sb.append('=');
        sb.append(((this.blockedHouse == null)?"<null>":this.blockedHouse));
        sb.append(',');
        sb.append("cadastreNumber");
        sb.append('=');
        sb.append(((this.cadastreNumber == null)?"<null>":this.cadastreNumber));
        sb.append(',');
        sb.append("oldCadastreNumber");
        sb.append('=');
        sb.append(((this.oldCadastreNumber == null)?"<null>":this.oldCadastreNumber));
        sb.append(',');
        sb.append("canCancel");
        sb.append('=');
        sb.append(((this.canCancel == null)?"<null>":this.canCancel));
        sb.append(',');
        sb.append("cancellationReason");
        sb.append('=');
        sb.append(((this.cancellationReason == null)?"<null>":this.cancellationReason));
        sb.append(',');
        sb.append("hasIncorrectObjects");
        sb.append('=');
        sb.append(((this.hasIncorrectObjects == null)?"<null>":this.hasIncorrectObjects));
        sb.append(',');
        sb.append("hasActiveDevices");
        sb.append('=');
        sb.append(((this.hasActiveDevices == null)?"<null>":this.hasActiveDevices));
        sb.append(',');
        sb.append("canReadShare");
        sb.append('=');
        sb.append(((this.canReadShare == null)?"<null>":this.canReadShare));
        sb.append(',');
        sb.append("regionProperty");
        sb.append('=');
        sb.append(((this.regionProperty == null)?"<null>":this.regionProperty));
        sb.append(',');
        sb.append("municipalProperty");
        sb.append('=');
        sb.append(((this.municipalProperty == null)?"<null>":this.municipalProperty));
        sb.append(',');
        sb.append("hostelType");
        sb.append('=');
        sb.append(((this.hostelType == null)?"<null>":this.hostelType));
        sb.append(',');
        sb.append("chiefFirstName");
        sb.append('=');
        sb.append(((this.chiefFirstName == null)?"<null>":this.chiefFirstName));
        sb.append(',');
        sb.append("chiefLastName");
        sb.append('=');
        sb.append(((this.chiefLastName == null)?"<null>":this.chiefLastName));
        sb.append(',');
        sb.append("chiefMiddleName");
        sb.append('=');
        sb.append(((this.chiefMiddleName == null)?"<null>":this.chiefMiddleName));
        sb.append(',');
        sb.append("houseCancelled");
        sb.append('=');
        sb.append(((this.houseCancelled == null)?"<null>":this.houseCancelled));
        sb.append(',');
        sb.append("approved");
        sb.append('=');
        sb.append(((this.approved == null)?"<null>":this.approved));
        sb.append(',');
        sb.append("canceledOrAsyncProcessed");
        sb.append('=');
        sb.append(((this.canceledOrAsyncProcessed == null)?"<null>":this.canceledOrAsyncProcessed));
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
        result = ((result* 31)+((this.rootGuid == null)? 0 :this.rootGuid.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.asyncProcessType == null)? 0 :this.asyncProcessType.hashCode()));
        result = ((result* 31)+((this.cadastreNumber == null)? 0 :this.cadastreNumber.hashCode()));
        result = ((result* 31)+((this.cancellationComment == null)? 0 :this.cancellationComment.hashCode()));
        result = ((result* 31)+((this.managementOrganization == null)? 0 :this.managementOrganization.hashCode()));
        result = ((result* 31)+((this.accountRefs == null)? 0 :this.accountRefs.hashCode()));
        result = ((result* 31)+((this.approved == null)? 0 :this.approved.hashCode()));
        result = ((result* 31)+((this.hasIncorrectObjects == null)? 0 :this.hasIncorrectObjects.hashCode()));
        result = ((result* 31)+((this.chiefMiddleName == null)? 0 :this.chiefMiddleName.hashCode()));
        result = ((result* 31)+((this.blockedHouse == null)? 0 :this.blockedHouse.hashCode()));
        result = ((result* 31)+((this.chiefLastName == null)? 0 :this.chiefLastName.hashCode()));
        result = ((result* 31)+((this.nonResidentialPremiseActualCount == null)? 0 :this.nonResidentialPremiseActualCount.hashCode()));
        result = ((result* 31)+((this.chiefFirstName == null)? 0 :this.chiefFirstName.hashCode()));
        result = ((result* 31)+((this.oldCadastreNumber == null)? 0 :this.oldCadastreNumber.hashCode()));
        result = ((result* 31)+((this.devices == null)? 0 :this.devices.hashCode()));
        result = ((result* 31)+((this.planSeries == null)? 0 :this.planSeries.hashCode()));
        result = ((result* 31)+((this.active == null)? 0 :this.active.hashCode()));
        result = ((result* 31)+((this.readOnly == null)? 0 :this.readOnly.hashCode()));
        result = ((result* 31)+((this.nonResidentialPremiseCount == null)? 0 :this.nonResidentialPremiseCount.hashCode()));
        result = ((result* 31)+((this.residentialPremiseActualCount == null)? 0 :this.residentialPremiseActualCount.hashCode()));
        result = ((result* 31)+((this.hasActiveDevices == null)? 0 :this.hasActiveDevices.hashCode()));
        result = ((result* 31)+((this.houseCondition == null)? 0 :this.houseCondition.hashCode()));
        result = ((result* 31)+((this.nonResidentialPremiseConfirmedCount == null)? 0 :this.nonResidentialPremiseConfirmedCount.hashCode()));
        result = ((result* 31)+((this.residentialPremiseConfirmedCount == null)? 0 :this.residentialPremiseConfirmedCount.hashCode()));
        result = ((result* 31)+((this.canReadShare == null)? 0 :this.canReadShare.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.operationYear == null)? 0 :this.operationYear.hashCode()));
        result = ((result* 31)+((this.houseCancelled == null)? 0 :this.houseCancelled.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.userOrganizationRole == null)? 0 :this.userOrganizationRole.hashCode()));
        result = ((result* 31)+((this.maxFloorCount == null)? 0 :this.maxFloorCount.hashCode()));
        result = ((result* 31)+((this.ableEnterDeviceMetering == null)? 0 :this.ableEnterDeviceMetering.hashCode()));
        result = ((result* 31)+((this.hasMunicipalServices == null)? 0 :this.hasMunicipalServices.hashCode()));
        result = ((result* 31)+((this.hostelType == null)? 0 :this.hostelType.hashCode()));
        result = ((result* 31)+((this.canAdd == null)? 0 :this.canAdd.hashCode()));
        result = ((result* 31)+((this.municipalProperty == null)? 0 :this.municipalProperty.hashCode()));
        result = ((result* 31)+((this.residentialPremiseCount == null)? 0 :this.residentialPremiseCount.hashCode()));
        result = ((result* 31)+((this.regionProperty == null)? 0 :this.regionProperty.hashCode()));
        result = ((result* 31)+((this.cancellationReasonCode == null)? 0 :this.cancellationReasonCode.hashCode()));
        result = ((result* 31)+((this.houseManagementType == null)? 0 :this.houseManagementType.hashCode()));
        result = ((result* 31)+((this.lastUpdateUnixTime == null)? 0 :this.lastUpdateUnixTime.hashCode()));
        result = ((result* 31)+((this.hasShare == null)? 0 :this.hasShare.hashCode()));
        result = ((result* 31)+((this.asyncProcessing == null)? 0 :this.asyncProcessing.hashCode()));
        result = ((result* 31)+((this.houseGuid == null)? 0 :this.houseGuid.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        result = ((result* 31)+((this.cancellationDate == null)? 0 :this.cancellationDate.hashCode()));
        result = ((result* 31)+((this.residentialSquare == null)? 0 :this.residentialSquare.hashCode()));
        result = ((result* 31)+((this.buildingYear == null)? 0 :this.buildingYear.hashCode()));
        result = ((result* 31)+((this.hasDummyShare == null)? 0 :this.hasDummyShare.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.planType == null)? 0 :this.planType.hashCode()));
        result = ((result* 31)+((this.accountCount == null)? 0 :this.accountCount.hashCode()));
        result = ((result* 31)+((this.cancellationReason == null)? 0 :this.cancellationReason.hashCode()));
        result = ((result* 31)+((this.houseType == null)? 0 :this.houseType.hashCode()));
        result = ((result* 31)+((this.versionNumber == null)? 0 :this.versionNumber.hashCode()));
        result = ((result* 31)+((this.canceledOrAsyncProcessed == null)? 0 :this.canceledOrAsyncProcessed.hashCode()));
        result = ((result* 31)+((this.deterioration == null)? 0 :this.deterioration.hashCode()));
        result = ((result* 31)+((this.oktmo == null)? 0 :this.oktmo.hashCode()));
        result = ((result* 31)+((this.apartmentsWithoutAccountCount == null)? 0 :this.apartmentsWithoutAccountCount.hashCode()));
        result = ((result* 31)+((this.municipalityOrganization == null)? 0 :this.municipalityOrganization.hashCode()));
        result = ((result* 31)+((this.totalSquare == null)? 0 :this.totalSquare.hashCode()));
        result = ((result* 31)+((this.canCancel == null)? 0 :this.canCancel.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ItemDto) == false) {
            return false;
        }
        ItemDto rhs = ((ItemDto) other);
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.rootGuid == rhs.rootGuid)||((this.rootGuid!= null)&&this.rootGuid.equals(rhs.rootGuid)))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.asyncProcessType == rhs.asyncProcessType)||((this.asyncProcessType!= null)&&this.asyncProcessType.equals(rhs.asyncProcessType))))&&((this.cadastreNumber == rhs.cadastreNumber)||((this.cadastreNumber!= null)&&this.cadastreNumber.equals(rhs.cadastreNumber))))&&((this.cancellationComment == rhs.cancellationComment)||((this.cancellationComment!= null)&&this.cancellationComment.equals(rhs.cancellationComment))))&&((this.managementOrganization == rhs.managementOrganization)||((this.managementOrganization!= null)&&this.managementOrganization.equals(rhs.managementOrganization))))&&((this.accountRefs == rhs.accountRefs)||((this.accountRefs!= null)&&this.accountRefs.equals(rhs.accountRefs))))&&((this.approved == rhs.approved)||((this.approved!= null)&&this.approved.equals(rhs.approved))))&&((this.hasIncorrectObjects == rhs.hasIncorrectObjects)||((this.hasIncorrectObjects!= null)&&this.hasIncorrectObjects.equals(rhs.hasIncorrectObjects))))&&((this.chiefMiddleName == rhs.chiefMiddleName)||((this.chiefMiddleName!= null)&&this.chiefMiddleName.equals(rhs.chiefMiddleName))))&&((this.blockedHouse == rhs.blockedHouse)||((this.blockedHouse!= null)&&this.blockedHouse.equals(rhs.blockedHouse))))&&((this.chiefLastName == rhs.chiefLastName)||((this.chiefLastName!= null)&&this.chiefLastName.equals(rhs.chiefLastName))))&&((this.nonResidentialPremiseActualCount == rhs.nonResidentialPremiseActualCount)||((this.nonResidentialPremiseActualCount!= null)&&this.nonResidentialPremiseActualCount.equals(rhs.nonResidentialPremiseActualCount))))&&((this.chiefFirstName == rhs.chiefFirstName)||((this.chiefFirstName!= null)&&this.chiefFirstName.equals(rhs.chiefFirstName))))&&((this.oldCadastreNumber == rhs.oldCadastreNumber)||((this.oldCadastreNumber!= null)&&this.oldCadastreNumber.equals(rhs.oldCadastreNumber))))&&((this.devices == rhs.devices)||((this.devices!= null)&&this.devices.equals(rhs.devices))))&&((this.planSeries == rhs.planSeries)||((this.planSeries!= null)&&this.planSeries.equals(rhs.planSeries))))&&((this.active == rhs.active)||((this.active!= null)&&this.active.equals(rhs.active))))&&((this.readOnly == rhs.readOnly)||((this.readOnly!= null)&&this.readOnly.equals(rhs.readOnly))))&&((this.nonResidentialPremiseCount == rhs.nonResidentialPremiseCount)||((this.nonResidentialPremiseCount!= null)&&this.nonResidentialPremiseCount.equals(rhs.nonResidentialPremiseCount))))&&((this.residentialPremiseActualCount == rhs.residentialPremiseActualCount)||((this.residentialPremiseActualCount!= null)&&this.residentialPremiseActualCount.equals(rhs.residentialPremiseActualCount))))&&((this.hasActiveDevices == rhs.hasActiveDevices)||((this.hasActiveDevices!= null)&&this.hasActiveDevices.equals(rhs.hasActiveDevices))))&&((this.houseCondition == rhs.houseCondition)||((this.houseCondition!= null)&&this.houseCondition.equals(rhs.houseCondition))))&&((this.nonResidentialPremiseConfirmedCount == rhs.nonResidentialPremiseConfirmedCount)||((this.nonResidentialPremiseConfirmedCount!= null)&&this.nonResidentialPremiseConfirmedCount.equals(rhs.nonResidentialPremiseConfirmedCount))))&&((this.residentialPremiseConfirmedCount == rhs.residentialPremiseConfirmedCount)||((this.residentialPremiseConfirmedCount!= null)&&this.residentialPremiseConfirmedCount.equals(rhs.residentialPremiseConfirmedCount))))&&((this.canReadShare == rhs.canReadShare)||((this.canReadShare!= null)&&this.canReadShare.equals(rhs.canReadShare))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.operationYear == rhs.operationYear)||((this.operationYear!= null)&&this.operationYear.equals(rhs.operationYear))))&&((this.houseCancelled == rhs.houseCancelled)||((this.houseCancelled!= null)&&this.houseCancelled.equals(rhs.houseCancelled))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.userOrganizationRole == rhs.userOrganizationRole)||((this.userOrganizationRole!= null)&&this.userOrganizationRole.equals(rhs.userOrganizationRole))))&&((this.maxFloorCount == rhs.maxFloorCount)||((this.maxFloorCount!= null)&&this.maxFloorCount.equals(rhs.maxFloorCount))))&&((this.ableEnterDeviceMetering == rhs.ableEnterDeviceMetering)||((this.ableEnterDeviceMetering!= null)&&this.ableEnterDeviceMetering.equals(rhs.ableEnterDeviceMetering))))&&((this.hasMunicipalServices == rhs.hasMunicipalServices)||((this.hasMunicipalServices!= null)&&this.hasMunicipalServices.equals(rhs.hasMunicipalServices))))&&((this.hostelType == rhs.hostelType)||((this.hostelType!= null)&&this.hostelType.equals(rhs.hostelType))))&&((this.canAdd == rhs.canAdd)||((this.canAdd!= null)&&this.canAdd.equals(rhs.canAdd))))&&((this.municipalProperty == rhs.municipalProperty)||((this.municipalProperty!= null)&&this.municipalProperty.equals(rhs.municipalProperty))))&&((this.residentialPremiseCount == rhs.residentialPremiseCount)||((this.residentialPremiseCount!= null)&&this.residentialPremiseCount.equals(rhs.residentialPremiseCount))))&&((this.regionProperty == rhs.regionProperty)||((this.regionProperty!= null)&&this.regionProperty.equals(rhs.regionProperty))))&&((this.cancellationReasonCode == rhs.cancellationReasonCode)||((this.cancellationReasonCode!= null)&&this.cancellationReasonCode.equals(rhs.cancellationReasonCode))))&&((this.houseManagementType == rhs.houseManagementType)||((this.houseManagementType!= null)&&this.houseManagementType.equals(rhs.houseManagementType))))&&((this.lastUpdateUnixTime == rhs.lastUpdateUnixTime)||((this.lastUpdateUnixTime!= null)&&this.lastUpdateUnixTime.equals(rhs.lastUpdateUnixTime))))&&((this.hasShare == rhs.hasShare)||((this.hasShare!= null)&&this.hasShare.equals(rhs.hasShare))))&&((this.asyncProcessing == rhs.asyncProcessing)||((this.asyncProcessing!= null)&&this.asyncProcessing.equals(rhs.asyncProcessing))))&&((this.houseGuid == rhs.houseGuid)||((this.houseGuid!= null)&&this.houseGuid.equals(rhs.houseGuid))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.cancellationDate == rhs.cancellationDate)||((this.cancellationDate!= null)&&this.cancellationDate.equals(rhs.cancellationDate))))&&((this.residentialSquare == rhs.residentialSquare)||((this.residentialSquare!= null)&&this.residentialSquare.equals(rhs.residentialSquare))))&&((this.buildingYear == rhs.buildingYear)||((this.buildingYear!= null)&&this.buildingYear.equals(rhs.buildingYear))))&&((this.hasDummyShare == rhs.hasDummyShare)||((this.hasDummyShare!= null)&&this.hasDummyShare.equals(rhs.hasDummyShare))))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.planType == rhs.planType)||((this.planType!= null)&&this.planType.equals(rhs.planType))))&&((this.accountCount == rhs.accountCount)||((this.accountCount!= null)&&this.accountCount.equals(rhs.accountCount))))&&((this.cancellationReason == rhs.cancellationReason)||((this.cancellationReason!= null)&&this.cancellationReason.equals(rhs.cancellationReason))))&&((this.houseType == rhs.houseType)||((this.houseType!= null)&&this.houseType.equals(rhs.houseType))))&&((this.versionNumber == rhs.versionNumber)||((this.versionNumber!= null)&&this.versionNumber.equals(rhs.versionNumber))))&&((this.canceledOrAsyncProcessed == rhs.canceledOrAsyncProcessed)||((this.canceledOrAsyncProcessed!= null)&&this.canceledOrAsyncProcessed.equals(rhs.canceledOrAsyncProcessed))))&&((this.deterioration == rhs.deterioration)||((this.deterioration!= null)&&this.deterioration.equals(rhs.deterioration))))&&((this.oktmo == rhs.oktmo)||((this.oktmo!= null)&&this.oktmo.equals(rhs.oktmo))))&&((this.apartmentsWithoutAccountCount == rhs.apartmentsWithoutAccountCount)||((this.apartmentsWithoutAccountCount!= null)&&this.apartmentsWithoutAccountCount.equals(rhs.apartmentsWithoutAccountCount))))&&((this.municipalityOrganization == rhs.municipalityOrganization)||((this.municipalityOrganization!= null)&&this.municipalityOrganization.equals(rhs.municipalityOrganization))))&&((this.totalSquare == rhs.totalSquare)||((this.totalSquare!= null)&&this.totalSquare.equals(rhs.totalSquare))))&&((this.canCancel == rhs.canCancel)||((this.canCancel!= null)&&this.canCancel.equals(rhs.canCancel))));
    }

}
