
package dto.realty.giszkh.finalres;

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
    "rosreestrRootGuid",
    "oktmo",
    "oldOktmo",
    "oktmoReadOnly",
    "parentOktmos",
    "oktmoFromFias",
    "houseType",
    "houseUid",
    "cadastreNumber",
    "oldCadastreNumber",
    "cadastreNumberIsAbsent",
    "floorCount",
    "floorCountReadOnly",
    "totalSquare",
    "totalSquareReadOnly",
    "residentialSquare",
    "landSurveyTotalSquare",
    "landSurveyTotalSquareReadOnly",
    "cadastreRemovalDate",
    "managementOrganization",
    "managementOrganizationRole",
    "houseCondition",
    "lifeCycleStage",
    "houseManagementType",
    "deterioration",
    "buildingYear",
    "operationYear",
    "operationYearReadOnly",
    "planSeries",
    "documentGuid",
    "documentType",
    "managementContractDate",
    "endContractDate",
    "attachments",
    "accountCount",
    "intWallMaterialList",
    "houseEnergyEfficiency",
    "energyInspectionDate",
    "municipalityOrganizationList",
    "culturalHeritage",
    "culturalHeritageReadOnly",
    "landPlotCadastreNumber",
    "prevStateRegNumber",
    "olsonTZ",
    "apartmentsExist",
    "rosreestrVersionsHistoryExists",
    "votingResultGuid",
    "blockCadastreUpdate",
    "canUnlinkFromRosreestr",
    "canAdd",
    "hasMunicipalServices",
    "isLicenseInfoExist",
    "fmsInfo",
    "canViewFms",
    "canCancel",
    "cancellationReason",
    "hasIncorrectObjects",
    "houseMappingInfo",
    "demolitionDate",
    "demolitionReason",
    "demolitionAttachments",
    "emergencyDocumentNumber",
    "emergencyDocumentDate",
    "houseTypeReadOnly",
    "resourceProvisionOrganizationList",
    "reconstructionYear",
    "regionProperty",
    "municipalProperty",
    "hostelType",
    "minFloorCount",
    "undergroundFloorCount",
    "undergroundFloorCountReadOnly",
    "overhaulFundContribution",
    "overhaulFundForming",
    "protocolGuid",
    "rentAgreementList",
    "estateObjectParams",
    "canViewEGRP",
    "residentialPremiseActualCount",
    "nonResidentialPremiseActualCount",
    "residentialPremiseConfirmedCount",
    "nonResidentialPremiseConfirmedCount",
    "buildingSquare",
    "deteriorationDate",
    "chiefFirstName",
    "chiefLastName",
    "chiefMiddleName",
    "approved",
    "canceledOrAsyncProcessed",
    "houseCancelled"
})
@Generated("jsonschema2pojo")
public class HouseFinalInfoDto {

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
    @JsonProperty("rosreestrRootGuid")
    private Object rosreestrRootGuid;
    @JsonProperty("oktmo")
    private OktmoDto oktmo;
    @JsonProperty("oldOktmo")
    private Object oldOktmo;
    @JsonProperty("oktmoReadOnly")
    private Object oktmoReadOnly;
    @JsonProperty("parentOktmos")
    private List<Object> parentOktmos = new ArrayList<Object>();
    @JsonProperty("oktmoFromFias")
    private Object oktmoFromFias;
    @JsonProperty("houseType")
    private HouseTypeDto houseType;
    @JsonProperty("houseUid")
    private String houseUid;
    @JsonProperty("cadastreNumber")
    private String cadastreNumber;
    @JsonProperty("oldCadastreNumber")
    private Object oldCadastreNumber;
    @JsonProperty("cadastreNumberIsAbsent")
    private Object cadastreNumberIsAbsent;
    @JsonProperty("floorCount")
    private Object floorCount;
    @JsonProperty("floorCountReadOnly")
    private Object floorCountReadOnly;
    @JsonProperty("totalSquare")
    private String totalSquare;
    @JsonProperty("totalSquareReadOnly")
    private Object totalSquareReadOnly;
    @JsonProperty("residentialSquare")
    private String residentialSquare;
    @JsonProperty("landSurveyTotalSquare")
    private Object landSurveyTotalSquare;
    @JsonProperty("landSurveyTotalSquareReadOnly")
    private Object landSurveyTotalSquareReadOnly;
    @JsonProperty("cadastreRemovalDate")
    private Object cadastreRemovalDate;
    @JsonProperty("managementOrganization")
    private ManagementOrganizationDto managementOrganization;
    @JsonProperty("managementOrganizationRole")
    private Boolean managementOrganizationRole;
    @JsonProperty("houseCondition")
    private HouseConditionDto houseCondition;
    @JsonProperty("lifeCycleStage")
    private LifeCycleStageDto lifeCycleStage;
    @JsonProperty("houseManagementType")
    private HouseManagementTypeDto houseManagementType;
    @JsonProperty("deterioration")
    private String deterioration;
    @JsonProperty("buildingYear")
    private String buildingYear;
    @JsonProperty("operationYear")
    private Integer operationYear;
    @JsonProperty("operationYearReadOnly")
    private Object operationYearReadOnly;
    @JsonProperty("planSeries")
    private String planSeries;
    @JsonProperty("documentGuid")
    private Object documentGuid;
    @JsonProperty("documentType")
    private Object documentType;
    @JsonProperty("managementContractDate")
    private String managementContractDate;
    @JsonProperty("endContractDate")
    private Object endContractDate;
    @JsonProperty("attachments")
    private Object attachments;
    @JsonProperty("accountCount")
    private Object accountCount;
    @JsonProperty("intWallMaterialList")
    private String intWallMaterialList;
    @JsonProperty("houseEnergyEfficiency")
    private Object houseEnergyEfficiency;
    @JsonProperty("energyInspectionDate")
    private Object energyInspectionDate;
    @JsonProperty("municipalityOrganizationList")
    private List<MunicipalityOrganizationDto> municipalityOrganizationList = new ArrayList<MunicipalityOrganizationDto>();
    @JsonProperty("culturalHeritage")
    private Object culturalHeritage;
    @JsonProperty("culturalHeritageReadOnly")
    private Object culturalHeritageReadOnly;
    @JsonProperty("landPlotCadastreNumber")
    private Object landPlotCadastreNumber;
    @JsonProperty("prevStateRegNumber")
    private Object prevStateRegNumber;
    @JsonProperty("olsonTZ")
    private Object olsonTZ;
    @JsonProperty("apartmentsExist")
    private Object apartmentsExist;
    @JsonProperty("rosreestrVersionsHistoryExists")
    private Object rosreestrVersionsHistoryExists;
    @JsonProperty("votingResultGuid")
    private Object votingResultGuid;
    @JsonProperty("blockCadastreUpdate")
    private Object blockCadastreUpdate;
    @JsonProperty("canUnlinkFromRosreestr")
    private Object canUnlinkFromRosreestr;
    @JsonProperty("canAdd")
    private Object canAdd;
    @JsonProperty("hasMunicipalServices")
    private Object hasMunicipalServices;
    @JsonProperty("isLicenseInfoExist")
    private Object isLicenseInfoExist;
    @JsonProperty("fmsInfo")
    private Object fmsInfo;
    @JsonProperty("canViewFms")
    private Object canViewFms;
    @JsonProperty("canCancel")
    private Object canCancel;
    @JsonProperty("cancellationReason")
    private Object cancellationReason;
    @JsonProperty("hasIncorrectObjects")
    private Object hasIncorrectObjects;
    @JsonProperty("houseMappingInfo")
    private Object houseMappingInfo;
    @JsonProperty("demolitionDate")
    private Object demolitionDate;
    @JsonProperty("demolitionReason")
    private Object demolitionReason;
    @JsonProperty("demolitionAttachments")
    private List<Object> demolitionAttachments = new ArrayList<Object>();
    @JsonProperty("emergencyDocumentNumber")
    private Object emergencyDocumentNumber;
    @JsonProperty("emergencyDocumentDate")
    private Object emergencyDocumentDate;
    @JsonProperty("houseTypeReadOnly")
    private Boolean houseTypeReadOnly;
    @JsonProperty("resourceProvisionOrganizationList")
    private List<ResourceProvisionOrganizationDto> resourceProvisionOrganizationList = new ArrayList<ResourceProvisionOrganizationDto>();
    @JsonProperty("reconstructionYear")
    private Object reconstructionYear;
    @JsonProperty("regionProperty")
    private Boolean regionProperty;
    @JsonProperty("municipalProperty")
    private Boolean municipalProperty;
    @JsonProperty("hostelType")
    private Object hostelType;
    @JsonProperty("minFloorCount")
    private Object minFloorCount;
    @JsonProperty("undergroundFloorCount")
    private Object undergroundFloorCount;
    @JsonProperty("undergroundFloorCountReadOnly")
    private Object undergroundFloorCountReadOnly;
    @JsonProperty("overhaulFundContribution")
    private Object overhaulFundContribution;
    @JsonProperty("overhaulFundForming")
    private Object overhaulFundForming;
    @JsonProperty("protocolGuid")
    private Object protocolGuid;
    @JsonProperty("rentAgreementList")
    private Object rentAgreementList;
    @JsonProperty("estateObjectParams")
    private Object estateObjectParams;
    @JsonProperty("canViewEGRP")
    private Object canViewEGRP;
    @JsonProperty("residentialPremiseActualCount")
    private Object residentialPremiseActualCount;
    @JsonProperty("nonResidentialPremiseActualCount")
    private Object nonResidentialPremiseActualCount;
    @JsonProperty("residentialPremiseConfirmedCount")
    private Object residentialPremiseConfirmedCount;
    @JsonProperty("nonResidentialPremiseConfirmedCount")
    private Object nonResidentialPremiseConfirmedCount;
    @JsonProperty("buildingSquare")
    private Object buildingSquare;
    @JsonProperty("deteriorationDate")
    private Object deteriorationDate;
    @JsonProperty("chiefFirstName")
    private Object chiefFirstName;
    @JsonProperty("chiefLastName")
    private Object chiefLastName;
    @JsonProperty("chiefMiddleName")
    private Object chiefMiddleName;
    @JsonProperty("approved")
    private Boolean approved;
    @JsonProperty("canceledOrAsyncProcessed")
    private Boolean canceledOrAsyncProcessed;
    @JsonProperty("houseCancelled")
    private Boolean houseCancelled;
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

    @JsonProperty("rosreestrRootGuid")
    public Object getRosreestrRootGuid() {
        return rosreestrRootGuid;
    }

    @JsonProperty("rosreestrRootGuid")
    public void setRosreestrRootGuid(Object rosreestrRootGuid) {
        this.rosreestrRootGuid = rosreestrRootGuid;
    }

    @JsonProperty("oktmo")
    public OktmoDto getOktmo() {
        return oktmo;
    }

    @JsonProperty("oktmo")
    public void setOktmo(OktmoDto oktmo) {
        this.oktmo = oktmo;
    }

    @JsonProperty("oldOktmo")
    public Object getOldOktmo() {
        return oldOktmo;
    }

    @JsonProperty("oldOktmo")
    public void setOldOktmo(Object oldOktmo) {
        this.oldOktmo = oldOktmo;
    }

    @JsonProperty("oktmoReadOnly")
    public Object getOktmoReadOnly() {
        return oktmoReadOnly;
    }

    @JsonProperty("oktmoReadOnly")
    public void setOktmoReadOnly(Object oktmoReadOnly) {
        this.oktmoReadOnly = oktmoReadOnly;
    }

    @JsonProperty("parentOktmos")
    public List<Object> getParentOktmos() {
        return parentOktmos;
    }

    @JsonProperty("parentOktmos")
    public void setParentOktmos(List<Object> parentOktmos) {
        this.parentOktmos = parentOktmos;
    }

    @JsonProperty("oktmoFromFias")
    public Object getOktmoFromFias() {
        return oktmoFromFias;
    }

    @JsonProperty("oktmoFromFias")
    public void setOktmoFromFias(Object oktmoFromFias) {
        this.oktmoFromFias = oktmoFromFias;
    }

    @JsonProperty("houseType")
    public HouseTypeDto getHouseType() {
        return houseType;
    }

    @JsonProperty("houseType")
    public void setHouseType(HouseTypeDto houseType) {
        this.houseType = houseType;
    }

    @JsonProperty("houseUid")
    public String getHouseUid() {
        return houseUid;
    }

    @JsonProperty("houseUid")
    public void setHouseUid(String houseUid) {
        this.houseUid = houseUid;
    }

    @JsonProperty("cadastreNumber")
    public String getCadastreNumber() {
        return cadastreNumber;
    }

    @JsonProperty("cadastreNumber")
    public void setCadastreNumber(String cadastreNumber) {
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

    @JsonProperty("cadastreNumberIsAbsent")
    public Object getCadastreNumberIsAbsent() {
        return cadastreNumberIsAbsent;
    }

    @JsonProperty("cadastreNumberIsAbsent")
    public void setCadastreNumberIsAbsent(Object cadastreNumberIsAbsent) {
        this.cadastreNumberIsAbsent = cadastreNumberIsAbsent;
    }

    @JsonProperty("floorCount")
    public Object getFloorCount() {
        return floorCount;
    }

    @JsonProperty("floorCount")
    public void setFloorCount(Object floorCount) {
        this.floorCount = floorCount;
    }

    @JsonProperty("floorCountReadOnly")
    public Object getFloorCountReadOnly() {
        return floorCountReadOnly;
    }

    @JsonProperty("floorCountReadOnly")
    public void setFloorCountReadOnly(Object floorCountReadOnly) {
        this.floorCountReadOnly = floorCountReadOnly;
    }

    @JsonProperty("totalSquare")
    public String getTotalSquare() {
        return totalSquare;
    }

    @JsonProperty("totalSquare")
    public void setTotalSquare(String totalSquare) {
        this.totalSquare = totalSquare;
    }

    @JsonProperty("totalSquareReadOnly")
    public Object getTotalSquareReadOnly() {
        return totalSquareReadOnly;
    }

    @JsonProperty("totalSquareReadOnly")
    public void setTotalSquareReadOnly(Object totalSquareReadOnly) {
        this.totalSquareReadOnly = totalSquareReadOnly;
    }

    @JsonProperty("residentialSquare")
    public String getResidentialSquare() {
        return residentialSquare;
    }

    @JsonProperty("residentialSquare")
    public void setResidentialSquare(String residentialSquare) {
        this.residentialSquare = residentialSquare;
    }

    @JsonProperty("landSurveyTotalSquare")
    public Object getLandSurveyTotalSquare() {
        return landSurveyTotalSquare;
    }

    @JsonProperty("landSurveyTotalSquare")
    public void setLandSurveyTotalSquare(Object landSurveyTotalSquare) {
        this.landSurveyTotalSquare = landSurveyTotalSquare;
    }

    @JsonProperty("landSurveyTotalSquareReadOnly")
    public Object getLandSurveyTotalSquareReadOnly() {
        return landSurveyTotalSquareReadOnly;
    }

    @JsonProperty("landSurveyTotalSquareReadOnly")
    public void setLandSurveyTotalSquareReadOnly(Object landSurveyTotalSquareReadOnly) {
        this.landSurveyTotalSquareReadOnly = landSurveyTotalSquareReadOnly;
    }

    @JsonProperty("cadastreRemovalDate")
    public Object getCadastreRemovalDate() {
        return cadastreRemovalDate;
    }

    @JsonProperty("cadastreRemovalDate")
    public void setCadastreRemovalDate(Object cadastreRemovalDate) {
        this.cadastreRemovalDate = cadastreRemovalDate;
    }

    @JsonProperty("managementOrganization")
    public ManagementOrganizationDto getManagementOrganization() {
        return managementOrganization;
    }

    @JsonProperty("managementOrganization")
    public void setManagementOrganization(ManagementOrganizationDto managementOrganization) {
        this.managementOrganization = managementOrganization;
    }

    @JsonProperty("managementOrganizationRole")
    public Boolean getManagementOrganizationRole() {
        return managementOrganizationRole;
    }

    @JsonProperty("managementOrganizationRole")
    public void setManagementOrganizationRole(Boolean managementOrganizationRole) {
        this.managementOrganizationRole = managementOrganizationRole;
    }

    @JsonProperty("houseCondition")
    public HouseConditionDto getHouseCondition() {
        return houseCondition;
    }

    @JsonProperty("houseCondition")
    public void setHouseCondition(HouseConditionDto houseCondition) {
        this.houseCondition = houseCondition;
    }

    @JsonProperty("lifeCycleStage")
    public LifeCycleStageDto getLifeCycleStage() {
        return lifeCycleStage;
    }

    @JsonProperty("lifeCycleStage")
    public void setLifeCycleStage(LifeCycleStageDto lifeCycleStage) {
        this.lifeCycleStage = lifeCycleStage;
    }

    @JsonProperty("houseManagementType")
    public HouseManagementTypeDto getHouseManagementType() {
        return houseManagementType;
    }

    @JsonProperty("houseManagementType")
    public void setHouseManagementType(HouseManagementTypeDto houseManagementType) {
        this.houseManagementType = houseManagementType;
    }

    @JsonProperty("deterioration")
    public String getDeterioration() {
        return deterioration;
    }

    @JsonProperty("deterioration")
    public void setDeterioration(String deterioration) {
        this.deterioration = deterioration;
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

    @JsonProperty("operationYearReadOnly")
    public Object getOperationYearReadOnly() {
        return operationYearReadOnly;
    }

    @JsonProperty("operationYearReadOnly")
    public void setOperationYearReadOnly(Object operationYearReadOnly) {
        this.operationYearReadOnly = operationYearReadOnly;
    }

    @JsonProperty("planSeries")
    public String getPlanSeries() {
        return planSeries;
    }

    @JsonProperty("planSeries")
    public void setPlanSeries(String planSeries) {
        this.planSeries = planSeries;
    }

    @JsonProperty("documentGuid")
    public Object getDocumentGuid() {
        return documentGuid;
    }

    @JsonProperty("documentGuid")
    public void setDocumentGuid(Object documentGuid) {
        this.documentGuid = documentGuid;
    }

    @JsonProperty("documentType")
    public Object getDocumentType() {
        return documentType;
    }

    @JsonProperty("documentType")
    public void setDocumentType(Object documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("managementContractDate")
    public String getManagementContractDate() {
        return managementContractDate;
    }

    @JsonProperty("managementContractDate")
    public void setManagementContractDate(String managementContractDate) {
        this.managementContractDate = managementContractDate;
    }

    @JsonProperty("endContractDate")
    public Object getEndContractDate() {
        return endContractDate;
    }

    @JsonProperty("endContractDate")
    public void setEndContractDate(Object endContractDate) {
        this.endContractDate = endContractDate;
    }

    @JsonProperty("attachments")
    public Object getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(Object attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("accountCount")
    public Object getAccountCount() {
        return accountCount;
    }

    @JsonProperty("accountCount")
    public void setAccountCount(Object accountCount) {
        this.accountCount = accountCount;
    }

    @JsonProperty("intWallMaterialList")
    public String getIntWallMaterialList() {
        return intWallMaterialList;
    }

    @JsonProperty("intWallMaterialList")
    public void setIntWallMaterialList(String intWallMaterialList) {
        this.intWallMaterialList = intWallMaterialList;
    }

    @JsonProperty("houseEnergyEfficiency")
    public Object getHouseEnergyEfficiency() {
        return houseEnergyEfficiency;
    }

    @JsonProperty("houseEnergyEfficiency")
    public void setHouseEnergyEfficiency(Object houseEnergyEfficiency) {
        this.houseEnergyEfficiency = houseEnergyEfficiency;
    }

    @JsonProperty("energyInspectionDate")
    public Object getEnergyInspectionDate() {
        return energyInspectionDate;
    }

    @JsonProperty("energyInspectionDate")
    public void setEnergyInspectionDate(Object energyInspectionDate) {
        this.energyInspectionDate = energyInspectionDate;
    }

    @JsonProperty("municipalityOrganizationList")
    public List<MunicipalityOrganizationDto> getMunicipalityOrganizationList() {
        return municipalityOrganizationList;
    }

    @JsonProperty("municipalityOrganizationList")
    public void setMunicipalityOrganizationList(List<MunicipalityOrganizationDto> municipalityOrganizationList) {
        this.municipalityOrganizationList = municipalityOrganizationList;
    }

    @JsonProperty("culturalHeritage")
    public Object getCulturalHeritage() {
        return culturalHeritage;
    }

    @JsonProperty("culturalHeritage")
    public void setCulturalHeritage(Object culturalHeritage) {
        this.culturalHeritage = culturalHeritage;
    }

    @JsonProperty("culturalHeritageReadOnly")
    public Object getCulturalHeritageReadOnly() {
        return culturalHeritageReadOnly;
    }

    @JsonProperty("culturalHeritageReadOnly")
    public void setCulturalHeritageReadOnly(Object culturalHeritageReadOnly) {
        this.culturalHeritageReadOnly = culturalHeritageReadOnly;
    }

    @JsonProperty("landPlotCadastreNumber")
    public Object getLandPlotCadastreNumber() {
        return landPlotCadastreNumber;
    }

    @JsonProperty("landPlotCadastreNumber")
    public void setLandPlotCadastreNumber(Object landPlotCadastreNumber) {
        this.landPlotCadastreNumber = landPlotCadastreNumber;
    }

    @JsonProperty("prevStateRegNumber")
    public Object getPrevStateRegNumber() {
        return prevStateRegNumber;
    }

    @JsonProperty("prevStateRegNumber")
    public void setPrevStateRegNumber(Object prevStateRegNumber) {
        this.prevStateRegNumber = prevStateRegNumber;
    }

    @JsonProperty("olsonTZ")
    public Object getOlsonTZ() {
        return olsonTZ;
    }

    @JsonProperty("olsonTZ")
    public void setOlsonTZ(Object olsonTZ) {
        this.olsonTZ = olsonTZ;
    }

    @JsonProperty("apartmentsExist")
    public Object getApartmentsExist() {
        return apartmentsExist;
    }

    @JsonProperty("apartmentsExist")
    public void setApartmentsExist(Object apartmentsExist) {
        this.apartmentsExist = apartmentsExist;
    }

    @JsonProperty("rosreestrVersionsHistoryExists")
    public Object getRosreestrVersionsHistoryExists() {
        return rosreestrVersionsHistoryExists;
    }

    @JsonProperty("rosreestrVersionsHistoryExists")
    public void setRosreestrVersionsHistoryExists(Object rosreestrVersionsHistoryExists) {
        this.rosreestrVersionsHistoryExists = rosreestrVersionsHistoryExists;
    }

    @JsonProperty("votingResultGuid")
    public Object getVotingResultGuid() {
        return votingResultGuid;
    }

    @JsonProperty("votingResultGuid")
    public void setVotingResultGuid(Object votingResultGuid) {
        this.votingResultGuid = votingResultGuid;
    }

    @JsonProperty("blockCadastreUpdate")
    public Object getBlockCadastreUpdate() {
        return blockCadastreUpdate;
    }

    @JsonProperty("blockCadastreUpdate")
    public void setBlockCadastreUpdate(Object blockCadastreUpdate) {
        this.blockCadastreUpdate = blockCadastreUpdate;
    }

    @JsonProperty("canUnlinkFromRosreestr")
    public Object getCanUnlinkFromRosreestr() {
        return canUnlinkFromRosreestr;
    }

    @JsonProperty("canUnlinkFromRosreestr")
    public void setCanUnlinkFromRosreestr(Object canUnlinkFromRosreestr) {
        this.canUnlinkFromRosreestr = canUnlinkFromRosreestr;
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

    @JsonProperty("isLicenseInfoExist")
    public Object getIsLicenseInfoExist() {
        return isLicenseInfoExist;
    }

    @JsonProperty("isLicenseInfoExist")
    public void setIsLicenseInfoExist(Object isLicenseInfoExist) {
        this.isLicenseInfoExist = isLicenseInfoExist;
    }

    @JsonProperty("fmsInfo")
    public Object getFmsInfo() {
        return fmsInfo;
    }

    @JsonProperty("fmsInfo")
    public void setFmsInfo(Object fmsInfo) {
        this.fmsInfo = fmsInfo;
    }

    @JsonProperty("canViewFms")
    public Object getCanViewFms() {
        return canViewFms;
    }

    @JsonProperty("canViewFms")
    public void setCanViewFms(Object canViewFms) {
        this.canViewFms = canViewFms;
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

    @JsonProperty("houseMappingInfo")
    public Object getHouseMappingInfo() {
        return houseMappingInfo;
    }

    @JsonProperty("houseMappingInfo")
    public void setHouseMappingInfo(Object houseMappingInfo) {
        this.houseMappingInfo = houseMappingInfo;
    }

    @JsonProperty("demolitionDate")
    public Object getDemolitionDate() {
        return demolitionDate;
    }

    @JsonProperty("demolitionDate")
    public void setDemolitionDate(Object demolitionDate) {
        this.demolitionDate = demolitionDate;
    }

    @JsonProperty("demolitionReason")
    public Object getDemolitionReason() {
        return demolitionReason;
    }

    @JsonProperty("demolitionReason")
    public void setDemolitionReason(Object demolitionReason) {
        this.demolitionReason = demolitionReason;
    }

    @JsonProperty("demolitionAttachments")
    public List<Object> getDemolitionAttachments() {
        return demolitionAttachments;
    }

    @JsonProperty("demolitionAttachments")
    public void setDemolitionAttachments(List<Object> demolitionAttachments) {
        this.demolitionAttachments = demolitionAttachments;
    }

    @JsonProperty("emergencyDocumentNumber")
    public Object getEmergencyDocumentNumber() {
        return emergencyDocumentNumber;
    }

    @JsonProperty("emergencyDocumentNumber")
    public void setEmergencyDocumentNumber(Object emergencyDocumentNumber) {
        this.emergencyDocumentNumber = emergencyDocumentNumber;
    }

    @JsonProperty("emergencyDocumentDate")
    public Object getEmergencyDocumentDate() {
        return emergencyDocumentDate;
    }

    @JsonProperty("emergencyDocumentDate")
    public void setEmergencyDocumentDate(Object emergencyDocumentDate) {
        this.emergencyDocumentDate = emergencyDocumentDate;
    }

    @JsonProperty("houseTypeReadOnly")
    public Boolean getHouseTypeReadOnly() {
        return houseTypeReadOnly;
    }

    @JsonProperty("houseTypeReadOnly")
    public void setHouseTypeReadOnly(Boolean houseTypeReadOnly) {
        this.houseTypeReadOnly = houseTypeReadOnly;
    }

    @JsonProperty("resourceProvisionOrganizationList")
    public List<ResourceProvisionOrganizationDto> getResourceProvisionOrganizationList() {
        return resourceProvisionOrganizationList;
    }

    @JsonProperty("resourceProvisionOrganizationList")
    public void setResourceProvisionOrganizationList(List<ResourceProvisionOrganizationDto> resourceProvisionOrganizationList) {
        this.resourceProvisionOrganizationList = resourceProvisionOrganizationList;
    }

    @JsonProperty("reconstructionYear")
    public Object getReconstructionYear() {
        return reconstructionYear;
    }

    @JsonProperty("reconstructionYear")
    public void setReconstructionYear(Object reconstructionYear) {
        this.reconstructionYear = reconstructionYear;
    }

    @JsonProperty("regionProperty")
    public Boolean getRegionProperty() {
        return regionProperty;
    }

    @JsonProperty("regionProperty")
    public void setRegionProperty(Boolean regionProperty) {
        this.regionProperty = regionProperty;
    }

    @JsonProperty("municipalProperty")
    public Boolean getMunicipalProperty() {
        return municipalProperty;
    }

    @JsonProperty("municipalProperty")
    public void setMunicipalProperty(Boolean municipalProperty) {
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

    @JsonProperty("minFloorCount")
    public Object getMinFloorCount() {
        return minFloorCount;
    }

    @JsonProperty("minFloorCount")
    public void setMinFloorCount(Object minFloorCount) {
        this.minFloorCount = minFloorCount;
    }

    @JsonProperty("undergroundFloorCount")
    public Object getUndergroundFloorCount() {
        return undergroundFloorCount;
    }

    @JsonProperty("undergroundFloorCount")
    public void setUndergroundFloorCount(Object undergroundFloorCount) {
        this.undergroundFloorCount = undergroundFloorCount;
    }

    @JsonProperty("undergroundFloorCountReadOnly")
    public Object getUndergroundFloorCountReadOnly() {
        return undergroundFloorCountReadOnly;
    }

    @JsonProperty("undergroundFloorCountReadOnly")
    public void setUndergroundFloorCountReadOnly(Object undergroundFloorCountReadOnly) {
        this.undergroundFloorCountReadOnly = undergroundFloorCountReadOnly;
    }

    @JsonProperty("overhaulFundContribution")
    public Object getOverhaulFundContribution() {
        return overhaulFundContribution;
    }

    @JsonProperty("overhaulFundContribution")
    public void setOverhaulFundContribution(Object overhaulFundContribution) {
        this.overhaulFundContribution = overhaulFundContribution;
    }

    @JsonProperty("overhaulFundForming")
    public Object getOverhaulFundForming() {
        return overhaulFundForming;
    }

    @JsonProperty("overhaulFundForming")
    public void setOverhaulFundForming(Object overhaulFundForming) {
        this.overhaulFundForming = overhaulFundForming;
    }

    @JsonProperty("protocolGuid")
    public Object getProtocolGuid() {
        return protocolGuid;
    }

    @JsonProperty("protocolGuid")
    public void setProtocolGuid(Object protocolGuid) {
        this.protocolGuid = protocolGuid;
    }

    @JsonProperty("rentAgreementList")
    public Object getRentAgreementList() {
        return rentAgreementList;
    }

    @JsonProperty("rentAgreementList")
    public void setRentAgreementList(Object rentAgreementList) {
        this.rentAgreementList = rentAgreementList;
    }

    @JsonProperty("estateObjectParams")
    public Object getEstateObjectParams() {
        return estateObjectParams;
    }

    @JsonProperty("estateObjectParams")
    public void setEstateObjectParams(Object estateObjectParams) {
        this.estateObjectParams = estateObjectParams;
    }

    @JsonProperty("canViewEGRP")
    public Object getCanViewEGRP() {
        return canViewEGRP;
    }

    @JsonProperty("canViewEGRP")
    public void setCanViewEGRP(Object canViewEGRP) {
        this.canViewEGRP = canViewEGRP;
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

    @JsonProperty("buildingSquare")
    public Object getBuildingSquare() {
        return buildingSquare;
    }

    @JsonProperty("buildingSquare")
    public void setBuildingSquare(Object buildingSquare) {
        this.buildingSquare = buildingSquare;
    }

    @JsonProperty("deteriorationDate")
    public Object getDeteriorationDate() {
        return deteriorationDate;
    }

    @JsonProperty("deteriorationDate")
    public void setDeteriorationDate(Object deteriorationDate) {
        this.deteriorationDate = deteriorationDate;
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

    @JsonProperty("houseCancelled")
    public Boolean getHouseCancelled() {
        return houseCancelled;
    }

    @JsonProperty("houseCancelled")
    public void setHouseCancelled(Boolean houseCancelled) {
        this.houseCancelled = houseCancelled;
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
        sb.append(HouseFinalInfoDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("rosreestrRootGuid");
        sb.append('=');
        sb.append(((this.rosreestrRootGuid == null)?"<null>":this.rosreestrRootGuid));
        sb.append(',');
        sb.append("oktmo");
        sb.append('=');
        sb.append(((this.oktmo == null)?"<null>":this.oktmo));
        sb.append(',');
        sb.append("oldOktmo");
        sb.append('=');
        sb.append(((this.oldOktmo == null)?"<null>":this.oldOktmo));
        sb.append(',');
        sb.append("oktmoReadOnly");
        sb.append('=');
        sb.append(((this.oktmoReadOnly == null)?"<null>":this.oktmoReadOnly));
        sb.append(',');
        sb.append("parentOktmos");
        sb.append('=');
        sb.append(((this.parentOktmos == null)?"<null>":this.parentOktmos));
        sb.append(',');
        sb.append("oktmoFromFias");
        sb.append('=');
        sb.append(((this.oktmoFromFias == null)?"<null>":this.oktmoFromFias));
        sb.append(',');
        sb.append("houseType");
        sb.append('=');
        sb.append(((this.houseType == null)?"<null>":this.houseType));
        sb.append(',');
        sb.append("houseUid");
        sb.append('=');
        sb.append(((this.houseUid == null)?"<null>":this.houseUid));
        sb.append(',');
        sb.append("cadastreNumber");
        sb.append('=');
        sb.append(((this.cadastreNumber == null)?"<null>":this.cadastreNumber));
        sb.append(',');
        sb.append("oldCadastreNumber");
        sb.append('=');
        sb.append(((this.oldCadastreNumber == null)?"<null>":this.oldCadastreNumber));
        sb.append(',');
        sb.append("cadastreNumberIsAbsent");
        sb.append('=');
        sb.append(((this.cadastreNumberIsAbsent == null)?"<null>":this.cadastreNumberIsAbsent));
        sb.append(',');
        sb.append("floorCount");
        sb.append('=');
        sb.append(((this.floorCount == null)?"<null>":this.floorCount));
        sb.append(',');
        sb.append("floorCountReadOnly");
        sb.append('=');
        sb.append(((this.floorCountReadOnly == null)?"<null>":this.floorCountReadOnly));
        sb.append(',');
        sb.append("totalSquare");
        sb.append('=');
        sb.append(((this.totalSquare == null)?"<null>":this.totalSquare));
        sb.append(',');
        sb.append("totalSquareReadOnly");
        sb.append('=');
        sb.append(((this.totalSquareReadOnly == null)?"<null>":this.totalSquareReadOnly));
        sb.append(',');
        sb.append("residentialSquare");
        sb.append('=');
        sb.append(((this.residentialSquare == null)?"<null>":this.residentialSquare));
        sb.append(',');
        sb.append("landSurveyTotalSquare");
        sb.append('=');
        sb.append(((this.landSurveyTotalSquare == null)?"<null>":this.landSurveyTotalSquare));
        sb.append(',');
        sb.append("landSurveyTotalSquareReadOnly");
        sb.append('=');
        sb.append(((this.landSurveyTotalSquareReadOnly == null)?"<null>":this.landSurveyTotalSquareReadOnly));
        sb.append(',');
        sb.append("cadastreRemovalDate");
        sb.append('=');
        sb.append(((this.cadastreRemovalDate == null)?"<null>":this.cadastreRemovalDate));
        sb.append(',');
        sb.append("managementOrganization");
        sb.append('=');
        sb.append(((this.managementOrganization == null)?"<null>":this.managementOrganization));
        sb.append(',');
        sb.append("managementOrganizationRole");
        sb.append('=');
        sb.append(((this.managementOrganizationRole == null)?"<null>":this.managementOrganizationRole));
        sb.append(',');
        sb.append("houseCondition");
        sb.append('=');
        sb.append(((this.houseCondition == null)?"<null>":this.houseCondition));
        sb.append(',');
        sb.append("lifeCycleStage");
        sb.append('=');
        sb.append(((this.lifeCycleStage == null)?"<null>":this.lifeCycleStage));
        sb.append(',');
        sb.append("houseManagementType");
        sb.append('=');
        sb.append(((this.houseManagementType == null)?"<null>":this.houseManagementType));
        sb.append(',');
        sb.append("deterioration");
        sb.append('=');
        sb.append(((this.deterioration == null)?"<null>":this.deterioration));
        sb.append(',');
        sb.append("buildingYear");
        sb.append('=');
        sb.append(((this.buildingYear == null)?"<null>":this.buildingYear));
        sb.append(',');
        sb.append("operationYear");
        sb.append('=');
        sb.append(((this.operationYear == null)?"<null>":this.operationYear));
        sb.append(',');
        sb.append("operationYearReadOnly");
        sb.append('=');
        sb.append(((this.operationYearReadOnly == null)?"<null>":this.operationYearReadOnly));
        sb.append(',');
        sb.append("planSeries");
        sb.append('=');
        sb.append(((this.planSeries == null)?"<null>":this.planSeries));
        sb.append(',');
        sb.append("documentGuid");
        sb.append('=');
        sb.append(((this.documentGuid == null)?"<null>":this.documentGuid));
        sb.append(',');
        sb.append("documentType");
        sb.append('=');
        sb.append(((this.documentType == null)?"<null>":this.documentType));
        sb.append(',');
        sb.append("managementContractDate");
        sb.append('=');
        sb.append(((this.managementContractDate == null)?"<null>":this.managementContractDate));
        sb.append(',');
        sb.append("endContractDate");
        sb.append('=');
        sb.append(((this.endContractDate == null)?"<null>":this.endContractDate));
        sb.append(',');
        sb.append("attachments");
        sb.append('=');
        sb.append(((this.attachments == null)?"<null>":this.attachments));
        sb.append(',');
        sb.append("accountCount");
        sb.append('=');
        sb.append(((this.accountCount == null)?"<null>":this.accountCount));
        sb.append(',');
        sb.append("intWallMaterialList");
        sb.append('=');
        sb.append(((this.intWallMaterialList == null)?"<null>":this.intWallMaterialList));
        sb.append(',');
        sb.append("houseEnergyEfficiency");
        sb.append('=');
        sb.append(((this.houseEnergyEfficiency == null)?"<null>":this.houseEnergyEfficiency));
        sb.append(',');
        sb.append("energyInspectionDate");
        sb.append('=');
        sb.append(((this.energyInspectionDate == null)?"<null>":this.energyInspectionDate));
        sb.append(',');
        sb.append("municipalityOrganizationList");
        sb.append('=');
        sb.append(((this.municipalityOrganizationList == null)?"<null>":this.municipalityOrganizationList));
        sb.append(',');
        sb.append("culturalHeritage");
        sb.append('=');
        sb.append(((this.culturalHeritage == null)?"<null>":this.culturalHeritage));
        sb.append(',');
        sb.append("culturalHeritageReadOnly");
        sb.append('=');
        sb.append(((this.culturalHeritageReadOnly == null)?"<null>":this.culturalHeritageReadOnly));
        sb.append(',');
        sb.append("landPlotCadastreNumber");
        sb.append('=');
        sb.append(((this.landPlotCadastreNumber == null)?"<null>":this.landPlotCadastreNumber));
        sb.append(',');
        sb.append("prevStateRegNumber");
        sb.append('=');
        sb.append(((this.prevStateRegNumber == null)?"<null>":this.prevStateRegNumber));
        sb.append(',');
        sb.append("olsonTZ");
        sb.append('=');
        sb.append(((this.olsonTZ == null)?"<null>":this.olsonTZ));
        sb.append(',');
        sb.append("apartmentsExist");
        sb.append('=');
        sb.append(((this.apartmentsExist == null)?"<null>":this.apartmentsExist));
        sb.append(',');
        sb.append("rosreestrVersionsHistoryExists");
        sb.append('=');
        sb.append(((this.rosreestrVersionsHistoryExists == null)?"<null>":this.rosreestrVersionsHistoryExists));
        sb.append(',');
        sb.append("votingResultGuid");
        sb.append('=');
        sb.append(((this.votingResultGuid == null)?"<null>":this.votingResultGuid));
        sb.append(',');
        sb.append("blockCadastreUpdate");
        sb.append('=');
        sb.append(((this.blockCadastreUpdate == null)?"<null>":this.blockCadastreUpdate));
        sb.append(',');
        sb.append("canUnlinkFromRosreestr");
        sb.append('=');
        sb.append(((this.canUnlinkFromRosreestr == null)?"<null>":this.canUnlinkFromRosreestr));
        sb.append(',');
        sb.append("canAdd");
        sb.append('=');
        sb.append(((this.canAdd == null)?"<null>":this.canAdd));
        sb.append(',');
        sb.append("hasMunicipalServices");
        sb.append('=');
        sb.append(((this.hasMunicipalServices == null)?"<null>":this.hasMunicipalServices));
        sb.append(',');
        sb.append("isLicenseInfoExist");
        sb.append('=');
        sb.append(((this.isLicenseInfoExist == null)?"<null>":this.isLicenseInfoExist));
        sb.append(',');
        sb.append("fmsInfo");
        sb.append('=');
        sb.append(((this.fmsInfo == null)?"<null>":this.fmsInfo));
        sb.append(',');
        sb.append("canViewFms");
        sb.append('=');
        sb.append(((this.canViewFms == null)?"<null>":this.canViewFms));
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
        sb.append("houseMappingInfo");
        sb.append('=');
        sb.append(((this.houseMappingInfo == null)?"<null>":this.houseMappingInfo));
        sb.append(',');
        sb.append("demolitionDate");
        sb.append('=');
        sb.append(((this.demolitionDate == null)?"<null>":this.demolitionDate));
        sb.append(',');
        sb.append("demolitionReason");
        sb.append('=');
        sb.append(((this.demolitionReason == null)?"<null>":this.demolitionReason));
        sb.append(',');
        sb.append("demolitionAttachments");
        sb.append('=');
        sb.append(((this.demolitionAttachments == null)?"<null>":this.demolitionAttachments));
        sb.append(',');
        sb.append("emergencyDocumentNumber");
        sb.append('=');
        sb.append(((this.emergencyDocumentNumber == null)?"<null>":this.emergencyDocumentNumber));
        sb.append(',');
        sb.append("emergencyDocumentDate");
        sb.append('=');
        sb.append(((this.emergencyDocumentDate == null)?"<null>":this.emergencyDocumentDate));
        sb.append(',');
        sb.append("houseTypeReadOnly");
        sb.append('=');
        sb.append(((this.houseTypeReadOnly == null)?"<null>":this.houseTypeReadOnly));
        sb.append(',');
        sb.append("resourceProvisionOrganizationList");
        sb.append('=');
        sb.append(((this.resourceProvisionOrganizationList == null)?"<null>":this.resourceProvisionOrganizationList));
        sb.append(',');
        sb.append("reconstructionYear");
        sb.append('=');
        sb.append(((this.reconstructionYear == null)?"<null>":this.reconstructionYear));
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
        sb.append("minFloorCount");
        sb.append('=');
        sb.append(((this.minFloorCount == null)?"<null>":this.minFloorCount));
        sb.append(',');
        sb.append("undergroundFloorCount");
        sb.append('=');
        sb.append(((this.undergroundFloorCount == null)?"<null>":this.undergroundFloorCount));
        sb.append(',');
        sb.append("undergroundFloorCountReadOnly");
        sb.append('=');
        sb.append(((this.undergroundFloorCountReadOnly == null)?"<null>":this.undergroundFloorCountReadOnly));
        sb.append(',');
        sb.append("overhaulFundContribution");
        sb.append('=');
        sb.append(((this.overhaulFundContribution == null)?"<null>":this.overhaulFundContribution));
        sb.append(',');
        sb.append("overhaulFundForming");
        sb.append('=');
        sb.append(((this.overhaulFundForming == null)?"<null>":this.overhaulFundForming));
        sb.append(',');
        sb.append("protocolGuid");
        sb.append('=');
        sb.append(((this.protocolGuid == null)?"<null>":this.protocolGuid));
        sb.append(',');
        sb.append("rentAgreementList");
        sb.append('=');
        sb.append(((this.rentAgreementList == null)?"<null>":this.rentAgreementList));
        sb.append(',');
        sb.append("estateObjectParams");
        sb.append('=');
        sb.append(((this.estateObjectParams == null)?"<null>":this.estateObjectParams));
        sb.append(',');
        sb.append("canViewEGRP");
        sb.append('=');
        sb.append(((this.canViewEGRP == null)?"<null>":this.canViewEGRP));
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
        sb.append("buildingSquare");
        sb.append('=');
        sb.append(((this.buildingSquare == null)?"<null>":this.buildingSquare));
        sb.append(',');
        sb.append("deteriorationDate");
        sb.append('=');
        sb.append(((this.deteriorationDate == null)?"<null>":this.deteriorationDate));
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
        sb.append("approved");
        sb.append('=');
        sb.append(((this.approved == null)?"<null>":this.approved));
        sb.append(',');
        sb.append("canceledOrAsyncProcessed");
        sb.append('=');
        sb.append(((this.canceledOrAsyncProcessed == null)?"<null>":this.canceledOrAsyncProcessed));
        sb.append(',');
        sb.append("houseCancelled");
        sb.append('=');
        sb.append(((this.houseCancelled == null)?"<null>":this.houseCancelled));
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
        result = ((result* 31)+((this.rosreestrRootGuid == null)? 0 :this.rosreestrRootGuid.hashCode()));
        result = ((result* 31)+((this.demolitionReason == null)? 0 :this.demolitionReason.hashCode()));
        result = ((result* 31)+((this.prevStateRegNumber == null)? 0 :this.prevStateRegNumber.hashCode()));
        result = ((result* 31)+((this.undergroundFloorCount == null)? 0 :this.undergroundFloorCount.hashCode()));
        result = ((result* 31)+((this.asyncProcessType == null)? 0 :this.asyncProcessType.hashCode()));
        result = ((result* 31)+((this.cadastreNumber == null)? 0 :this.cadastreNumber.hashCode()));
        result = ((result* 31)+((this.cadastreRemovalDate == null)? 0 :this.cadastreRemovalDate.hashCode()));
        result = ((result* 31)+((this.managementOrganization == null)? 0 :this.managementOrganization.hashCode()));
        result = ((result* 31)+((this.blockCadastreUpdate == null)? 0 :this.blockCadastreUpdate.hashCode()));
        result = ((result* 31)+((this.rosreestrVersionsHistoryExists == null)? 0 :this.rosreestrVersionsHistoryExists.hashCode()));
        result = ((result* 31)+((this.approved == null)? 0 :this.approved.hashCode()));
        result = ((result* 31)+((this.chiefMiddleName == null)? 0 :this.chiefMiddleName.hashCode()));
        result = ((result* 31)+((this.culturalHeritageReadOnly == null)? 0 :this.culturalHeritageReadOnly.hashCode()));
        result = ((result* 31)+((this.emergencyDocumentNumber == null)? 0 :this.emergencyDocumentNumber.hashCode()));
        result = ((result* 31)+((this.chiefLastName == null)? 0 :this.chiefLastName.hashCode()));
        result = ((result* 31)+((this.nonResidentialPremiseActualCount == null)? 0 :this.nonResidentialPremiseActualCount.hashCode()));
        result = ((result* 31)+((this.landPlotCadastreNumber == null)? 0 :this.landPlotCadastreNumber.hashCode()));
        result = ((result* 31)+((this.oldOktmo == null)? 0 :this.oldOktmo.hashCode()));
        result = ((result* 31)+((this.chiefFirstName == null)? 0 :this.chiefFirstName.hashCode()));
        result = ((result* 31)+((this.oldCadastreNumber == null)? 0 :this.oldCadastreNumber.hashCode()));
        result = ((result* 31)+((this.active == null)? 0 :this.active.hashCode()));
        result = ((result* 31)+((this.readOnly == null)? 0 :this.readOnly.hashCode()));
        result = ((result* 31)+((this.culturalHeritage == null)? 0 :this.culturalHeritage.hashCode()));
        result = ((result* 31)+((this.houseCondition == null)? 0 :this.houseCondition.hashCode()));
        result = ((result* 31)+((this.overhaulFundForming == null)? 0 :this.overhaulFundForming.hashCode()));
        result = ((result* 31)+((this.residentialPremiseConfirmedCount == null)? 0 :this.residentialPremiseConfirmedCount.hashCode()));
        result = ((result* 31)+((this.operationYear == null)? 0 :this.operationYear.hashCode()));
        result = ((result* 31)+((this.overhaulFundContribution == null)? 0 :this.overhaulFundContribution.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.attachments == null)? 0 :this.attachments.hashCode()));
        result = ((result* 31)+((this.hostelType == null)? 0 :this.hostelType.hashCode()));
        result = ((result* 31)+((this.regionProperty == null)? 0 :this.regionProperty.hashCode()));
        result = ((result* 31)+((this.houseManagementType == null)? 0 :this.houseManagementType.hashCode()));
        result = ((result* 31)+((this.lastUpdateUnixTime == null)? 0 :this.lastUpdateUnixTime.hashCode()));
        result = ((result* 31)+((this.parentOktmos == null)? 0 :this.parentOktmos.hashCode()));
        result = ((result* 31)+((this.deteriorationDate == null)? 0 :this.deteriorationDate.hashCode()));
        result = ((result* 31)+((this.managementOrganizationRole == null)? 0 :this.managementOrganizationRole.hashCode()));
        result = ((result* 31)+((this.createDate == null)? 0 :this.createDate.hashCode()));
        result = ((result* 31)+((this.cancellationDate == null)? 0 :this.cancellationDate.hashCode()));
        result = ((result* 31)+((this.residentialSquare == null)? 0 :this.residentialSquare.hashCode()));
        result = ((result* 31)+((this.operationYearReadOnly == null)? 0 :this.operationYearReadOnly.hashCode()));
        result = ((result* 31)+((this.endContractDate == null)? 0 :this.endContractDate.hashCode()));
        result = ((result* 31)+((this.buildingYear == null)? 0 :this.buildingYear.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.accountCount == null)? 0 :this.accountCount.hashCode()));
        result = ((result* 31)+((this.energyInspectionDate == null)? 0 :this.energyInspectionDate.hashCode()));
        result = ((result* 31)+((this.cancellationReason == null)? 0 :this.cancellationReason.hashCode()));
        result = ((result* 31)+((this.votingResultGuid == null)? 0 :this.votingResultGuid.hashCode()));
        result = ((result* 31)+((this.floorCount == null)? 0 :this.floorCount.hashCode()));
        result = ((result* 31)+((this.houseUid == null)? 0 :this.houseUid.hashCode()));
        result = ((result* 31)+((this.landSurveyTotalSquareReadOnly == null)? 0 :this.landSurveyTotalSquareReadOnly.hashCode()));
        result = ((result* 31)+((this.fmsInfo == null)? 0 :this.fmsInfo.hashCode()));
        result = ((result* 31)+((this.documentGuid == null)? 0 :this.documentGuid.hashCode()));
        result = ((result* 31)+((this.rentAgreementList == null)? 0 :this.rentAgreementList.hashCode()));
        result = ((result* 31)+((this.canUnlinkFromRosreestr == null)? 0 :this.canUnlinkFromRosreestr.hashCode()));
        result = ((result* 31)+((this.olsonTZ == null)? 0 :this.olsonTZ.hashCode()));
        result = ((result* 31)+((this.totalSquare == null)? 0 :this.totalSquare.hashCode()));
        result = ((result* 31)+((this.houseTypeReadOnly == null)? 0 :this.houseTypeReadOnly.hashCode()));
        result = ((result* 31)+((this.resourceProvisionOrganizationList == null)? 0 :this.resourceProvisionOrganizationList.hashCode()));
        result = ((result* 31)+((this.rootGuid == null)? 0 :this.rootGuid.hashCode()));
        result = ((result* 31)+((this.lastUpdateDate == null)? 0 :this.lastUpdateDate.hashCode()));
        result = ((result* 31)+((this.cancellationComment == null)? 0 :this.cancellationComment.hashCode()));
        result = ((result* 31)+((this.houseEnergyEfficiency == null)? 0 :this.houseEnergyEfficiency.hashCode()));
        result = ((result* 31)+((this.isLicenseInfoExist == null)? 0 :this.isLicenseInfoExist.hashCode()));
        result = ((result* 31)+((this.hasIncorrectObjects == null)? 0 :this.hasIncorrectObjects.hashCode()));
        result = ((result* 31)+((this.undergroundFloorCountReadOnly == null)? 0 :this.undergroundFloorCountReadOnly.hashCode()));
        result = ((result* 31)+((this.demolitionAttachments == null)? 0 :this.demolitionAttachments.hashCode()));
        result = ((result* 31)+((this.estateObjectParams == null)? 0 :this.estateObjectParams.hashCode()));
        result = ((result* 31)+((this.minFloorCount == null)? 0 :this.minFloorCount.hashCode()));
        result = ((result* 31)+((this.intWallMaterialList == null)? 0 :this.intWallMaterialList.hashCode()));
        result = ((result* 31)+((this.oktmoFromFias == null)? 0 :this.oktmoFromFias.hashCode()));
        result = ((result* 31)+((this.floorCountReadOnly == null)? 0 :this.floorCountReadOnly.hashCode()));
        result = ((result* 31)+((this.planSeries == null)? 0 :this.planSeries.hashCode()));
        result = ((result* 31)+((this.buildingSquare == null)? 0 :this.buildingSquare.hashCode()));
        result = ((result* 31)+((this.residentialPremiseActualCount == null)? 0 :this.residentialPremiseActualCount.hashCode()));
        result = ((result* 31)+((this.nonResidentialPremiseConfirmedCount == null)? 0 :this.nonResidentialPremiseConfirmedCount.hashCode()));
        result = ((result* 31)+((this.totalSquareReadOnly == null)? 0 :this.totalSquareReadOnly.hashCode()));
        result = ((result* 31)+((this.canViewEGRP == null)? 0 :this.canViewEGRP.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((this.houseCancelled == null)? 0 :this.houseCancelled.hashCode()));
        result = ((result* 31)+((this.demolitionDate == null)? 0 :this.demolitionDate.hashCode()));
        result = ((result* 31)+((this.cadastreNumberIsAbsent == null)? 0 :this.cadastreNumberIsAbsent.hashCode()));
        result = ((result* 31)+((this.documentType == null)? 0 :this.documentType.hashCode()));
        result = ((result* 31)+((this.hasMunicipalServices == null)? 0 :this.hasMunicipalServices.hashCode()));
        result = ((result* 31)+((this.reconstructionYear == null)? 0 :this.reconstructionYear.hashCode()));
        result = ((result* 31)+((this.canAdd == null)? 0 :this.canAdd.hashCode()));
        result = ((result* 31)+((this.municipalProperty == null)? 0 :this.municipalProperty.hashCode()));
        result = ((result* 31)+((this.cancellationReasonCode == null)? 0 :this.cancellationReasonCode.hashCode()));
        result = ((result* 31)+((this.protocolGuid == null)? 0 :this.protocolGuid.hashCode()));
        result = ((result* 31)+((this.canViewFms == null)? 0 :this.canViewFms.hashCode()));
        result = ((result* 31)+((this.landSurveyTotalSquare == null)? 0 :this.landSurveyTotalSquare.hashCode()));
        result = ((result* 31)+((this.asyncProcessing == null)? 0 :this.asyncProcessing.hashCode()));
        result = ((result* 31)+((this.apartmentsExist == null)? 0 :this.apartmentsExist.hashCode()));
        result = ((result* 31)+((this.emergencyDocumentDate == null)? 0 :this.emergencyDocumentDate.hashCode()));
        result = ((result* 31)+((this.houseMappingInfo == null)? 0 :this.houseMappingInfo.hashCode()));
        result = ((result* 31)+((this.houseType == null)? 0 :this.houseType.hashCode()));
        result = ((result* 31)+((this.lifeCycleStage == null)? 0 :this.lifeCycleStage.hashCode()));
        result = ((result* 31)+((this.versionNumber == null)? 0 :this.versionNumber.hashCode()));
        result = ((result* 31)+((this.canceledOrAsyncProcessed == null)? 0 :this.canceledOrAsyncProcessed.hashCode()));
        result = ((result* 31)+((this.oktmo == null)? 0 :this.oktmo.hashCode()));
        result = ((result* 31)+((this.deterioration == null)? 0 :this.deterioration.hashCode()));
        result = ((result* 31)+((this.oktmoReadOnly == null)? 0 :this.oktmoReadOnly.hashCode()));
        result = ((result* 31)+((this.managementContractDate == null)? 0 :this.managementContractDate.hashCode()));
        result = ((result* 31)+((this.municipalityOrganizationList == null)? 0 :this.municipalityOrganizationList.hashCode()));
        result = ((result* 31)+((this.canCancel == null)? 0 :this.canCancel.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HouseFinalInfoDto) == false) {
            return false;
        }
        HouseFinalInfoDto rhs = ((HouseFinalInfoDto) other);
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.rosreestrRootGuid == rhs.rosreestrRootGuid)||((this.rosreestrRootGuid!= null)&&this.rosreestrRootGuid.equals(rhs.rosreestrRootGuid)))&&((this.demolitionReason == rhs.demolitionReason)||((this.demolitionReason!= null)&&this.demolitionReason.equals(rhs.demolitionReason))))&&((this.prevStateRegNumber == rhs.prevStateRegNumber)||((this.prevStateRegNumber!= null)&&this.prevStateRegNumber.equals(rhs.prevStateRegNumber))))&&((this.undergroundFloorCount == rhs.undergroundFloorCount)||((this.undergroundFloorCount!= null)&&this.undergroundFloorCount.equals(rhs.undergroundFloorCount))))&&((this.asyncProcessType == rhs.asyncProcessType)||((this.asyncProcessType!= null)&&this.asyncProcessType.equals(rhs.asyncProcessType))))&&((this.cadastreNumber == rhs.cadastreNumber)||((this.cadastreNumber!= null)&&this.cadastreNumber.equals(rhs.cadastreNumber))))&&((this.cadastreRemovalDate == rhs.cadastreRemovalDate)||((this.cadastreRemovalDate!= null)&&this.cadastreRemovalDate.equals(rhs.cadastreRemovalDate))))&&((this.managementOrganization == rhs.managementOrganization)||((this.managementOrganization!= null)&&this.managementOrganization.equals(rhs.managementOrganization))))&&((this.blockCadastreUpdate == rhs.blockCadastreUpdate)||((this.blockCadastreUpdate!= null)&&this.blockCadastreUpdate.equals(rhs.blockCadastreUpdate))))&&((this.rosreestrVersionsHistoryExists == rhs.rosreestrVersionsHistoryExists)||((this.rosreestrVersionsHistoryExists!= null)&&this.rosreestrVersionsHistoryExists.equals(rhs.rosreestrVersionsHistoryExists))))&&((this.approved == rhs.approved)||((this.approved!= null)&&this.approved.equals(rhs.approved))))&&((this.chiefMiddleName == rhs.chiefMiddleName)||((this.chiefMiddleName!= null)&&this.chiefMiddleName.equals(rhs.chiefMiddleName))))&&((this.culturalHeritageReadOnly == rhs.culturalHeritageReadOnly)||((this.culturalHeritageReadOnly!= null)&&this.culturalHeritageReadOnly.equals(rhs.culturalHeritageReadOnly))))&&((this.emergencyDocumentNumber == rhs.emergencyDocumentNumber)||((this.emergencyDocumentNumber!= null)&&this.emergencyDocumentNumber.equals(rhs.emergencyDocumentNumber))))&&((this.chiefLastName == rhs.chiefLastName)||((this.chiefLastName!= null)&&this.chiefLastName.equals(rhs.chiefLastName))))&&((this.nonResidentialPremiseActualCount == rhs.nonResidentialPremiseActualCount)||((this.nonResidentialPremiseActualCount!= null)&&this.nonResidentialPremiseActualCount.equals(rhs.nonResidentialPremiseActualCount))))&&((this.landPlotCadastreNumber == rhs.landPlotCadastreNumber)||((this.landPlotCadastreNumber!= null)&&this.landPlotCadastreNumber.equals(rhs.landPlotCadastreNumber))))&&((this.oldOktmo == rhs.oldOktmo)||((this.oldOktmo!= null)&&this.oldOktmo.equals(rhs.oldOktmo))))&&((this.chiefFirstName == rhs.chiefFirstName)||((this.chiefFirstName!= null)&&this.chiefFirstName.equals(rhs.chiefFirstName))))&&((this.oldCadastreNumber == rhs.oldCadastreNumber)||((this.oldCadastreNumber!= null)&&this.oldCadastreNumber.equals(rhs.oldCadastreNumber))))&&((this.active == rhs.active)||((this.active!= null)&&this.active.equals(rhs.active))))&&((this.readOnly == rhs.readOnly)||((this.readOnly!= null)&&this.readOnly.equals(rhs.readOnly))))&&((this.culturalHeritage == rhs.culturalHeritage)||((this.culturalHeritage!= null)&&this.culturalHeritage.equals(rhs.culturalHeritage))))&&((this.houseCondition == rhs.houseCondition)||((this.houseCondition!= null)&&this.houseCondition.equals(rhs.houseCondition))))&&((this.overhaulFundForming == rhs.overhaulFundForming)||((this.overhaulFundForming!= null)&&this.overhaulFundForming.equals(rhs.overhaulFundForming))))&&((this.residentialPremiseConfirmedCount == rhs.residentialPremiseConfirmedCount)||((this.residentialPremiseConfirmedCount!= null)&&this.residentialPremiseConfirmedCount.equals(rhs.residentialPremiseConfirmedCount))))&&((this.operationYear == rhs.operationYear)||((this.operationYear!= null)&&this.operationYear.equals(rhs.operationYear))))&&((this.overhaulFundContribution == rhs.overhaulFundContribution)||((this.overhaulFundContribution!= null)&&this.overhaulFundContribution.equals(rhs.overhaulFundContribution))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.attachments == rhs.attachments)||((this.attachments!= null)&&this.attachments.equals(rhs.attachments))))&&((this.hostelType == rhs.hostelType)||((this.hostelType!= null)&&this.hostelType.equals(rhs.hostelType))))&&((this.regionProperty == rhs.regionProperty)||((this.regionProperty!= null)&&this.regionProperty.equals(rhs.regionProperty))))&&((this.houseManagementType == rhs.houseManagementType)||((this.houseManagementType!= null)&&this.houseManagementType.equals(rhs.houseManagementType))))&&((this.lastUpdateUnixTime == rhs.lastUpdateUnixTime)||((this.lastUpdateUnixTime!= null)&&this.lastUpdateUnixTime.equals(rhs.lastUpdateUnixTime))))&&((this.parentOktmos == rhs.parentOktmos)||((this.parentOktmos!= null)&&this.parentOktmos.equals(rhs.parentOktmos))))&&((this.deteriorationDate == rhs.deteriorationDate)||((this.deteriorationDate!= null)&&this.deteriorationDate.equals(rhs.deteriorationDate))))&&((this.managementOrganizationRole == rhs.managementOrganizationRole)||((this.managementOrganizationRole!= null)&&this.managementOrganizationRole.equals(rhs.managementOrganizationRole))))&&((this.createDate == rhs.createDate)||((this.createDate!= null)&&this.createDate.equals(rhs.createDate))))&&((this.cancellationDate == rhs.cancellationDate)||((this.cancellationDate!= null)&&this.cancellationDate.equals(rhs.cancellationDate))))&&((this.residentialSquare == rhs.residentialSquare)||((this.residentialSquare!= null)&&this.residentialSquare.equals(rhs.residentialSquare))))&&((this.operationYearReadOnly == rhs.operationYearReadOnly)||((this.operationYearReadOnly!= null)&&this.operationYearReadOnly.equals(rhs.operationYearReadOnly))))&&((this.endContractDate == rhs.endContractDate)||((this.endContractDate!= null)&&this.endContractDate.equals(rhs.endContractDate))))&&((this.buildingYear == rhs.buildingYear)||((this.buildingYear!= null)&&this.buildingYear.equals(rhs.buildingYear))))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.accountCount == rhs.accountCount)||((this.accountCount!= null)&&this.accountCount.equals(rhs.accountCount))))&&((this.energyInspectionDate == rhs.energyInspectionDate)||((this.energyInspectionDate!= null)&&this.energyInspectionDate.equals(rhs.energyInspectionDate))))&&((this.cancellationReason == rhs.cancellationReason)||((this.cancellationReason!= null)&&this.cancellationReason.equals(rhs.cancellationReason))))&&((this.votingResultGuid == rhs.votingResultGuid)||((this.votingResultGuid!= null)&&this.votingResultGuid.equals(rhs.votingResultGuid))))&&((this.floorCount == rhs.floorCount)||((this.floorCount!= null)&&this.floorCount.equals(rhs.floorCount))))&&((this.houseUid == rhs.houseUid)||((this.houseUid!= null)&&this.houseUid.equals(rhs.houseUid))))&&((this.landSurveyTotalSquareReadOnly == rhs.landSurveyTotalSquareReadOnly)||((this.landSurveyTotalSquareReadOnly!= null)&&this.landSurveyTotalSquareReadOnly.equals(rhs.landSurveyTotalSquareReadOnly))))&&((this.fmsInfo == rhs.fmsInfo)||((this.fmsInfo!= null)&&this.fmsInfo.equals(rhs.fmsInfo))))&&((this.documentGuid == rhs.documentGuid)||((this.documentGuid!= null)&&this.documentGuid.equals(rhs.documentGuid))))&&((this.rentAgreementList == rhs.rentAgreementList)||((this.rentAgreementList!= null)&&this.rentAgreementList.equals(rhs.rentAgreementList))))&&((this.canUnlinkFromRosreestr == rhs.canUnlinkFromRosreestr)||((this.canUnlinkFromRosreestr!= null)&&this.canUnlinkFromRosreestr.equals(rhs.canUnlinkFromRosreestr))))&&((this.olsonTZ == rhs.olsonTZ)||((this.olsonTZ!= null)&&this.olsonTZ.equals(rhs.olsonTZ))))&&((this.totalSquare == rhs.totalSquare)||((this.totalSquare!= null)&&this.totalSquare.equals(rhs.totalSquare))))&&((this.houseTypeReadOnly == rhs.houseTypeReadOnly)||((this.houseTypeReadOnly!= null)&&this.houseTypeReadOnly.equals(rhs.houseTypeReadOnly))))&&((this.resourceProvisionOrganizationList == rhs.resourceProvisionOrganizationList)||((this.resourceProvisionOrganizationList!= null)&&this.resourceProvisionOrganizationList.equals(rhs.resourceProvisionOrganizationList))))&&((this.rootGuid == rhs.rootGuid)||((this.rootGuid!= null)&&this.rootGuid.equals(rhs.rootGuid))))&&((this.lastUpdateDate == rhs.lastUpdateDate)||((this.lastUpdateDate!= null)&&this.lastUpdateDate.equals(rhs.lastUpdateDate))))&&((this.cancellationComment == rhs.cancellationComment)||((this.cancellationComment!= null)&&this.cancellationComment.equals(rhs.cancellationComment))))&&((this.houseEnergyEfficiency == rhs.houseEnergyEfficiency)||((this.houseEnergyEfficiency!= null)&&this.houseEnergyEfficiency.equals(rhs.houseEnergyEfficiency))))&&((this.isLicenseInfoExist == rhs.isLicenseInfoExist)||((this.isLicenseInfoExist!= null)&&this.isLicenseInfoExist.equals(rhs.isLicenseInfoExist))))&&((this.hasIncorrectObjects == rhs.hasIncorrectObjects)||((this.hasIncorrectObjects!= null)&&this.hasIncorrectObjects.equals(rhs.hasIncorrectObjects))))&&((this.undergroundFloorCountReadOnly == rhs.undergroundFloorCountReadOnly)||((this.undergroundFloorCountReadOnly!= null)&&this.undergroundFloorCountReadOnly.equals(rhs.undergroundFloorCountReadOnly))))&&((this.demolitionAttachments == rhs.demolitionAttachments)||((this.demolitionAttachments!= null)&&this.demolitionAttachments.equals(rhs.demolitionAttachments))))&&((this.estateObjectParams == rhs.estateObjectParams)||((this.estateObjectParams!= null)&&this.estateObjectParams.equals(rhs.estateObjectParams))))&&((this.minFloorCount == rhs.minFloorCount)||((this.minFloorCount!= null)&&this.minFloorCount.equals(rhs.minFloorCount))))&&((this.intWallMaterialList == rhs.intWallMaterialList)||((this.intWallMaterialList!= null)&&this.intWallMaterialList.equals(rhs.intWallMaterialList))))&&((this.oktmoFromFias == rhs.oktmoFromFias)||((this.oktmoFromFias!= null)&&this.oktmoFromFias.equals(rhs.oktmoFromFias))))&&((this.floorCountReadOnly == rhs.floorCountReadOnly)||((this.floorCountReadOnly!= null)&&this.floorCountReadOnly.equals(rhs.floorCountReadOnly))))&&((this.planSeries == rhs.planSeries)||((this.planSeries!= null)&&this.planSeries.equals(rhs.planSeries))))&&((this.buildingSquare == rhs.buildingSquare)||((this.buildingSquare!= null)&&this.buildingSquare.equals(rhs.buildingSquare))))&&((this.residentialPremiseActualCount == rhs.residentialPremiseActualCount)||((this.residentialPremiseActualCount!= null)&&this.residentialPremiseActualCount.equals(rhs.residentialPremiseActualCount))))&&((this.nonResidentialPremiseConfirmedCount == rhs.nonResidentialPremiseConfirmedCount)||((this.nonResidentialPremiseConfirmedCount!= null)&&this.nonResidentialPremiseConfirmedCount.equals(rhs.nonResidentialPremiseConfirmedCount))))&&((this.totalSquareReadOnly == rhs.totalSquareReadOnly)||((this.totalSquareReadOnly!= null)&&this.totalSquareReadOnly.equals(rhs.totalSquareReadOnly))))&&((this.canViewEGRP == rhs.canViewEGRP)||((this.canViewEGRP!= null)&&this.canViewEGRP.equals(rhs.canViewEGRP))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&((this.houseCancelled == rhs.houseCancelled)||((this.houseCancelled!= null)&&this.houseCancelled.equals(rhs.houseCancelled))))&&((this.demolitionDate == rhs.demolitionDate)||((this.demolitionDate!= null)&&this.demolitionDate.equals(rhs.demolitionDate))))&&((this.cadastreNumberIsAbsent == rhs.cadastreNumberIsAbsent)||((this.cadastreNumberIsAbsent!= null)&&this.cadastreNumberIsAbsent.equals(rhs.cadastreNumberIsAbsent))))&&((this.documentType == rhs.documentType)||((this.documentType!= null)&&this.documentType.equals(rhs.documentType))))&&((this.hasMunicipalServices == rhs.hasMunicipalServices)||((this.hasMunicipalServices!= null)&&this.hasMunicipalServices.equals(rhs.hasMunicipalServices))))&&((this.reconstructionYear == rhs.reconstructionYear)||((this.reconstructionYear!= null)&&this.reconstructionYear.equals(rhs.reconstructionYear))))&&((this.canAdd == rhs.canAdd)||((this.canAdd!= null)&&this.canAdd.equals(rhs.canAdd))))&&((this.municipalProperty == rhs.municipalProperty)||((this.municipalProperty!= null)&&this.municipalProperty.equals(rhs.municipalProperty))))&&((this.cancellationReasonCode == rhs.cancellationReasonCode)||((this.cancellationReasonCode!= null)&&this.cancellationReasonCode.equals(rhs.cancellationReasonCode))))&&((this.protocolGuid == rhs.protocolGuid)||((this.protocolGuid!= null)&&this.protocolGuid.equals(rhs.protocolGuid))))&&((this.canViewFms == rhs.canViewFms)||((this.canViewFms!= null)&&this.canViewFms.equals(rhs.canViewFms))))&&((this.landSurveyTotalSquare == rhs.landSurveyTotalSquare)||((this.landSurveyTotalSquare!= null)&&this.landSurveyTotalSquare.equals(rhs.landSurveyTotalSquare))))&&((this.asyncProcessing == rhs.asyncProcessing)||((this.asyncProcessing!= null)&&this.asyncProcessing.equals(rhs.asyncProcessing))))&&((this.apartmentsExist == rhs.apartmentsExist)||((this.apartmentsExist!= null)&&this.apartmentsExist.equals(rhs.apartmentsExist))))&&((this.emergencyDocumentDate == rhs.emergencyDocumentDate)||((this.emergencyDocumentDate!= null)&&this.emergencyDocumentDate.equals(rhs.emergencyDocumentDate))))&&((this.houseMappingInfo == rhs.houseMappingInfo)||((this.houseMappingInfo!= null)&&this.houseMappingInfo.equals(rhs.houseMappingInfo))))&&((this.houseType == rhs.houseType)||((this.houseType!= null)&&this.houseType.equals(rhs.houseType))))&&((this.lifeCycleStage == rhs.lifeCycleStage)||((this.lifeCycleStage!= null)&&this.lifeCycleStage.equals(rhs.lifeCycleStage))))&&((this.versionNumber == rhs.versionNumber)||((this.versionNumber!= null)&&this.versionNumber.equals(rhs.versionNumber))))&&((this.canceledOrAsyncProcessed == rhs.canceledOrAsyncProcessed)||((this.canceledOrAsyncProcessed!= null)&&this.canceledOrAsyncProcessed.equals(rhs.canceledOrAsyncProcessed))))&&((this.oktmo == rhs.oktmo)||((this.oktmo!= null)&&this.oktmo.equals(rhs.oktmo))))&&((this.deterioration == rhs.deterioration)||((this.deterioration!= null)&&this.deterioration.equals(rhs.deterioration))))&&((this.oktmoReadOnly == rhs.oktmoReadOnly)||((this.oktmoReadOnly!= null)&&this.oktmoReadOnly.equals(rhs.oktmoReadOnly))))&&((this.managementContractDate == rhs.managementContractDate)||((this.managementContractDate!= null)&&this.managementContractDate.equals(rhs.managementContractDate))))&&((this.municipalityOrganizationList == rhs.municipalityOrganizationList)||((this.municipalityOrganizationList!= null)&&this.municipalityOrganizationList.equals(rhs.municipalityOrganizationList))))&&((this.canCancel == rhs.canCancel)||((this.canCancel!= null)&&this.canCancel.equals(rhs.canCancel))));
    }

}
