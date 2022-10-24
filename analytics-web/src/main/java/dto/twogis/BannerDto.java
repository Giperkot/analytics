
package dto.twogis;

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
    "name_ex",
    "segment_id",
    "region_id",
    "hash",
    "ad_attributes",
    "main_rubric_id",
    "org"
})
@Generated("jsonschema2pojo")
public class BannerDto {

    @JsonProperty("name_ex")
    private NameExDto nameEx;
    @JsonProperty("segment_id")
    private String segmentId;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("ad_attributes")
    private AdAttributesDto adAttributes;
    @JsonProperty("main_rubric_id")
    private Integer mainRubricId;
    @JsonProperty("org")
    private OrgDto org;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name_ex")
    public NameExDto getNameEx() {
        return nameEx;
    }

    @JsonProperty("name_ex")
    public void setNameEx(NameExDto nameEx) {
        this.nameEx = nameEx;
    }

    @JsonProperty("segment_id")
    public String getSegmentId() {
        return segmentId;
    }

    @JsonProperty("segment_id")
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    @JsonProperty("region_id")
    public String getRegionId() {
        return regionId;
    }

    @JsonProperty("region_id")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("ad_attributes")
    public AdAttributesDto getAdAttributes() {
        return adAttributes;
    }

    @JsonProperty("ad_attributes")
    public void setAdAttributes(AdAttributesDto adAttributes) {
        this.adAttributes = adAttributes;
    }

    @JsonProperty("main_rubric_id")
    public Integer getMainRubricId() {
        return mainRubricId;
    }

    @JsonProperty("main_rubric_id")
    public void setMainRubricId(Integer mainRubricId) {
        this.mainRubricId = mainRubricId;
    }

    @JsonProperty("org")
    public OrgDto getOrg() {
        return org;
    }

    @JsonProperty("org")
    public void setOrg(OrgDto org) {
        this.org = org;
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
        sb.append(BannerDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nameEx");
        sb.append('=');
        sb.append(((this.nameEx == null)?"<null>":this.nameEx));
        sb.append(',');
        sb.append("segmentId");
        sb.append('=');
        sb.append(((this.segmentId == null)?"<null>":this.segmentId));
        sb.append(',');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("hash");
        sb.append('=');
        sb.append(((this.hash == null)?"<null>":this.hash));
        sb.append(',');
        sb.append("adAttributes");
        sb.append('=');
        sb.append(((this.adAttributes == null)?"<null>":this.adAttributes));
        sb.append(',');
        sb.append("mainRubricId");
        sb.append('=');
        sb.append(((this.mainRubricId == null)?"<null>":this.mainRubricId));
        sb.append(',');
        sb.append("org");
        sb.append('=');
        sb.append(((this.org == null)?"<null>":this.org));
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
        result = ((result* 31)+((this.nameEx == null)? 0 :this.nameEx.hashCode()));
        result = ((result* 31)+((this.regionId == null)? 0 :this.regionId.hashCode()));
        result = ((result* 31)+((this.org == null)? 0 :this.org.hashCode()));
        result = ((result* 31)+((this.segmentId == null)? 0 :this.segmentId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.hash == null)? 0 :this.hash.hashCode()));
        result = ((result* 31)+((this.adAttributes == null)? 0 :this.adAttributes.hashCode()));
        result = ((result* 31)+((this.mainRubricId == null)? 0 :this.mainRubricId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BannerDto) == false) {
            return false;
        }
        BannerDto rhs = ((BannerDto) other);
        return (((((((((this.nameEx == rhs.nameEx)||((this.nameEx!= null)&&this.nameEx.equals(rhs.nameEx)))&&((this.regionId == rhs.regionId)||((this.regionId!= null)&&this.regionId.equals(rhs.regionId))))&&((this.org == rhs.org)||((this.org!= null)&&this.org.equals(rhs.org))))&&((this.segmentId == rhs.segmentId)||((this.segmentId!= null)&&this.segmentId.equals(rhs.segmentId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.hash == rhs.hash)||((this.hash!= null)&&this.hash.equals(rhs.hash))))&&((this.adAttributes == rhs.adAttributes)||((this.adAttributes!= null)&&this.adAttributes.equals(rhs.adAttributes))))&&((this.mainRubricId == rhs.mainRubricId)||((this.mainRubricId!= null)&&this.mainRubricId.equals(rhs.mainRubricId))));
    }

}
