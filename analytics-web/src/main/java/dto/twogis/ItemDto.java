
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
    "address",
    "address_name",
    "adm_div",
    "city_alias",
    "context",
    "external_content",
    "flags",
    "floors",
    "full_name",
    "geometry",
    "has_apartments_info",
    "has_realty",
    "id",
    "links",
    "locale",
    "name",
    "point",
    "purpose_name",
    "region_id",
    "reviews",
    "segment_id",
    "stat",
    "structure_info",
    "type"
})
@Generated("jsonschema2pojo")
public class ItemDto {

    @JsonProperty("address")
    private AddressDto address;
    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("adm_div")
    private List<AdmDivDto> admDiv = new ArrayList<AdmDivDto>();
    @JsonProperty("city_alias")
    private String cityAlias;
    @JsonProperty("context")
    private ContextDto context;
    @JsonProperty("external_content")
    private List<ExternalContentDto> externalContent = new ArrayList<ExternalContentDto>();
    @JsonProperty("flags")
    private FlagsDto__1 flags;
    @JsonProperty("floors")
    private FloorsDto floors;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("geometry")
    private GeometryDto geometry;
    @JsonProperty("has_apartments_info")
    private Boolean hasApartmentsInfo;
    @JsonProperty("has_realty")
    private Boolean hasRealty;
    @JsonProperty("id")
    private String id;
    @JsonProperty("links")
    private LinksDto links;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("name")
    private String name;
    @JsonProperty("point")
    private PointDto point;
    @JsonProperty("purpose_name")
    private String purposeName;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("reviews")
    private ReviewsDto reviews;
    @JsonProperty("segment_id")
    private String segmentId;
    @JsonProperty("stat")
    private StatDto stat;
    @JsonProperty("structure_info")
    private StructureInfoDto structureInfo;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address")
    public AddressDto getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @JsonProperty("address_name")
    public String getAddressName() {
        return addressName;
    }

    @JsonProperty("address_name")
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @JsonProperty("adm_div")
    public List<AdmDivDto> getAdmDiv() {
        return admDiv;
    }

    @JsonProperty("adm_div")
    public void setAdmDiv(List<AdmDivDto> admDiv) {
        this.admDiv = admDiv;
    }

    @JsonProperty("city_alias")
    public String getCityAlias() {
        return cityAlias;
    }

    @JsonProperty("city_alias")
    public void setCityAlias(String cityAlias) {
        this.cityAlias = cityAlias;
    }

    @JsonProperty("context")
    public ContextDto getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(ContextDto context) {
        this.context = context;
    }

    @JsonProperty("external_content")
    public List<ExternalContentDto> getExternalContent() {
        return externalContent;
    }

    @JsonProperty("external_content")
    public void setExternalContent(List<ExternalContentDto> externalContent) {
        this.externalContent = externalContent;
    }

    @JsonProperty("flags")
    public FlagsDto__1 getFlags() {
        return flags;
    }

    @JsonProperty("flags")
    public void setFlags(FlagsDto__1 flags) {
        this.flags = flags;
    }

    @JsonProperty("floors")
    public FloorsDto getFloors() {
        return floors;
    }

    @JsonProperty("floors")
    public void setFloors(FloorsDto floors) {
        this.floors = floors;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("geometry")
    public GeometryDto getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(GeometryDto geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("has_apartments_info")
    public Boolean getHasApartmentsInfo() {
        return hasApartmentsInfo;
    }

    @JsonProperty("has_apartments_info")
    public void setHasApartmentsInfo(Boolean hasApartmentsInfo) {
        this.hasApartmentsInfo = hasApartmentsInfo;
    }

    @JsonProperty("has_realty")
    public Boolean getHasRealty() {
        return hasRealty;
    }

    @JsonProperty("has_realty")
    public void setHasRealty(Boolean hasRealty) {
        this.hasRealty = hasRealty;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("links")
    public LinksDto getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(LinksDto links) {
        this.links = links;
    }

    @JsonProperty("locale")
    public String getLocale() {
        return locale;
    }

    @JsonProperty("locale")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("point")
    public PointDto getPoint() {
        return point;
    }

    @JsonProperty("point")
    public void setPoint(PointDto point) {
        this.point = point;
    }

    @JsonProperty("purpose_name")
    public String getPurposeName() {
        return purposeName;
    }

    @JsonProperty("purpose_name")
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    @JsonProperty("region_id")
    public String getRegionId() {
        return regionId;
    }

    @JsonProperty("region_id")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @JsonProperty("reviews")
    public ReviewsDto getReviews() {
        return reviews;
    }

    @JsonProperty("reviews")
    public void setReviews(ReviewsDto reviews) {
        this.reviews = reviews;
    }

    @JsonProperty("segment_id")
    public String getSegmentId() {
        return segmentId;
    }

    @JsonProperty("segment_id")
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    @JsonProperty("stat")
    public StatDto getStat() {
        return stat;
    }

    @JsonProperty("stat")
    public void setStat(StatDto stat) {
        this.stat = stat;
    }

    @JsonProperty("structure_info")
    public StructureInfoDto getStructureInfo() {
        return structureInfo;
    }

    @JsonProperty("structure_info")
    public void setStructureInfo(StructureInfoDto structureInfo) {
        this.structureInfo = structureInfo;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("addressName");
        sb.append('=');
        sb.append(((this.addressName == null)?"<null>":this.addressName));
        sb.append(',');
        sb.append("admDiv");
        sb.append('=');
        sb.append(((this.admDiv == null)?"<null>":this.admDiv));
        sb.append(',');
        sb.append("cityAlias");
        sb.append('=');
        sb.append(((this.cityAlias == null)?"<null>":this.cityAlias));
        sb.append(',');
        sb.append("context");
        sb.append('=');
        sb.append(((this.context == null)?"<null>":this.context));
        sb.append(',');
        sb.append("externalContent");
        sb.append('=');
        sb.append(((this.externalContent == null)?"<null>":this.externalContent));
        sb.append(',');
        sb.append("flags");
        sb.append('=');
        sb.append(((this.flags == null)?"<null>":this.flags));
        sb.append(',');
        sb.append("floors");
        sb.append('=');
        sb.append(((this.floors == null)?"<null>":this.floors));
        sb.append(',');
        sb.append("fullName");
        sb.append('=');
        sb.append(((this.fullName == null)?"<null>":this.fullName));
        sb.append(',');
        sb.append("geometry");
        sb.append('=');
        sb.append(((this.geometry == null)?"<null>":this.geometry));
        sb.append(',');
        sb.append("hasApartmentsInfo");
        sb.append('=');
        sb.append(((this.hasApartmentsInfo == null)?"<null>":this.hasApartmentsInfo));
        sb.append(',');
        sb.append("hasRealty");
        sb.append('=');
        sb.append(((this.hasRealty == null)?"<null>":this.hasRealty));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("locale");
        sb.append('=');
        sb.append(((this.locale == null)?"<null>":this.locale));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("point");
        sb.append('=');
        sb.append(((this.point == null)?"<null>":this.point));
        sb.append(',');
        sb.append("purposeName");
        sb.append('=');
        sb.append(((this.purposeName == null)?"<null>":this.purposeName));
        sb.append(',');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("reviews");
        sb.append('=');
        sb.append(((this.reviews == null)?"<null>":this.reviews));
        sb.append(',');
        sb.append("segmentId");
        sb.append('=');
        sb.append(((this.segmentId == null)?"<null>":this.segmentId));
        sb.append(',');
        sb.append("stat");
        sb.append('=');
        sb.append(((this.stat == null)?"<null>":this.stat));
        sb.append(',');
        sb.append("structureInfo");
        sb.append('=');
        sb.append(((this.structureInfo == null)?"<null>":this.structureInfo));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.cityAlias == null)? 0 :this.cityAlias.hashCode()));
        result = ((result* 31)+((this.flags == null)? 0 :this.flags.hashCode()));
        result = ((result* 31)+((this.locale == null)? 0 :this.locale.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.point == null)? 0 :this.point.hashCode()));
        result = ((result* 31)+((this.externalContent == null)? 0 :this.externalContent.hashCode()));
        result = ((result* 31)+((this.floors == null)? 0 :this.floors.hashCode()));
        result = ((result* 31)+((this.reviews == null)? 0 :this.reviews.hashCode()));
        result = ((result* 31)+((this.segmentId == null)? 0 :this.segmentId.hashCode()));
        result = ((result* 31)+((this.context == null)? 0 :this.context.hashCode()));
        result = ((result* 31)+((this.links == null)? 0 :this.links.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.stat == null)? 0 :this.stat.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.purposeName == null)? 0 :this.purposeName.hashCode()));
        result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
        result = ((result* 31)+((this.hasRealty == null)? 0 :this.hasRealty.hashCode()));
        result = ((result* 31)+((this.hasApartmentsInfo == null)? 0 :this.hasApartmentsInfo.hashCode()));
        result = ((result* 31)+((this.regionId == null)? 0 :this.regionId.hashCode()));
        result = ((result* 31)+((this.structureInfo == null)? 0 :this.structureInfo.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.addressName == null)? 0 :this.addressName.hashCode()));
        result = ((result* 31)+((this.geometry == null)? 0 :this.geometry.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.admDiv == null)? 0 :this.admDiv.hashCode()));
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
        return ((((((((((((((((((((((((((this.cityAlias == rhs.cityAlias)||((this.cityAlias!= null)&&this.cityAlias.equals(rhs.cityAlias)))&&((this.flags == rhs.flags)||((this.flags!= null)&&this.flags.equals(rhs.flags))))&&((this.locale == rhs.locale)||((this.locale!= null)&&this.locale.equals(rhs.locale))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.point == rhs.point)||((this.point!= null)&&this.point.equals(rhs.point))))&&((this.externalContent == rhs.externalContent)||((this.externalContent!= null)&&this.externalContent.equals(rhs.externalContent))))&&((this.floors == rhs.floors)||((this.floors!= null)&&this.floors.equals(rhs.floors))))&&((this.reviews == rhs.reviews)||((this.reviews!= null)&&this.reviews.equals(rhs.reviews))))&&((this.segmentId == rhs.segmentId)||((this.segmentId!= null)&&this.segmentId.equals(rhs.segmentId))))&&((this.context == rhs.context)||((this.context!= null)&&this.context.equals(rhs.context))))&&((this.links == rhs.links)||((this.links!= null)&&this.links.equals(rhs.links))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.stat == rhs.stat)||((this.stat!= null)&&this.stat.equals(rhs.stat))))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.purposeName == rhs.purposeName)||((this.purposeName!= null)&&this.purposeName.equals(rhs.purposeName))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.hasRealty == rhs.hasRealty)||((this.hasRealty!= null)&&this.hasRealty.equals(rhs.hasRealty))))&&((this.hasApartmentsInfo == rhs.hasApartmentsInfo)||((this.hasApartmentsInfo!= null)&&this.hasApartmentsInfo.equals(rhs.hasApartmentsInfo))))&&((this.regionId == rhs.regionId)||((this.regionId!= null)&&this.regionId.equals(rhs.regionId))))&&((this.structureInfo == rhs.structureInfo)||((this.structureInfo!= null)&&this.structureInfo.equals(rhs.structureInfo))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.addressName == rhs.addressName)||((this.addressName!= null)&&this.addressName.equals(rhs.addressName))))&&((this.geometry == rhs.geometry)||((this.geometry!= null)&&this.geometry.equals(rhs.geometry))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.admDiv == rhs.admDiv)||((this.admDiv!= null)&&this.admDiv.equals(rhs.admDiv))));
    }

}
