
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
    "algorithm_id",
    "auction_id",
    "branch_id",
    "content",
    "is_ads",
    "project_id",
    "rubric_id"
})
@Generated("jsonschema2pojo")
public class AdAttributesDto {

    @JsonProperty("algorithm_id")
    private Integer algorithmId;
    @JsonProperty("auction_id")
    private String auctionId;
    @JsonProperty("branch_id")
    private String branchId;
    @JsonProperty("content")
    private ContentDto content;
    @JsonProperty("is_ads")
    private Boolean isAds;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("rubric_id")
    private Integer rubricId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("algorithm_id")
    public Integer getAlgorithmId() {
        return algorithmId;
    }

    @JsonProperty("algorithm_id")
    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }

    @JsonProperty("auction_id")
    public String getAuctionId() {
        return auctionId;
    }

    @JsonProperty("auction_id")
    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    @JsonProperty("branch_id")
    public String getBranchId() {
        return branchId;
    }

    @JsonProperty("branch_id")
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @JsonProperty("content")
    public ContentDto getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(ContentDto content) {
        this.content = content;
    }

    @JsonProperty("is_ads")
    public Boolean getIsAds() {
        return isAds;
    }

    @JsonProperty("is_ads")
    public void setIsAds(Boolean isAds) {
        this.isAds = isAds;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("rubric_id")
    public Integer getRubricId() {
        return rubricId;
    }

    @JsonProperty("rubric_id")
    public void setRubricId(Integer rubricId) {
        this.rubricId = rubricId;
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
        sb.append(AdAttributesDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("algorithmId");
        sb.append('=');
        sb.append(((this.algorithmId == null)?"<null>":this.algorithmId));
        sb.append(',');
        sb.append("auctionId");
        sb.append('=');
        sb.append(((this.auctionId == null)?"<null>":this.auctionId));
        sb.append(',');
        sb.append("branchId");
        sb.append('=');
        sb.append(((this.branchId == null)?"<null>":this.branchId));
        sb.append(',');
        sb.append("content");
        sb.append('=');
        sb.append(((this.content == null)?"<null>":this.content));
        sb.append(',');
        sb.append("isAds");
        sb.append('=');
        sb.append(((this.isAds == null)?"<null>":this.isAds));
        sb.append(',');
        sb.append("projectId");
        sb.append('=');
        sb.append(((this.projectId == null)?"<null>":this.projectId));
        sb.append(',');
        sb.append("rubricId");
        sb.append('=');
        sb.append(((this.rubricId == null)?"<null>":this.rubricId));
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
        result = ((result* 31)+((this.auctionId == null)? 0 :this.auctionId.hashCode()));
        result = ((result* 31)+((this.branchId == null)? 0 :this.branchId.hashCode()));
        result = ((result* 31)+((this.rubricId == null)? 0 :this.rubricId.hashCode()));
        result = ((result* 31)+((this.isAds == null)? 0 :this.isAds.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.projectId == null)? 0 :this.projectId.hashCode()));
        result = ((result* 31)+((this.content == null)? 0 :this.content.hashCode()));
        result = ((result* 31)+((this.algorithmId == null)? 0 :this.algorithmId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdAttributesDto) == false) {
            return false;
        }
        AdAttributesDto rhs = ((AdAttributesDto) other);
        return (((((((((this.auctionId == rhs.auctionId)||((this.auctionId!= null)&&this.auctionId.equals(rhs.auctionId)))&&((this.branchId == rhs.branchId)||((this.branchId!= null)&&this.branchId.equals(rhs.branchId))))&&((this.rubricId == rhs.rubricId)||((this.rubricId!= null)&&this.rubricId.equals(rhs.rubricId))))&&((this.isAds == rhs.isAds)||((this.isAds!= null)&&this.isAds.equals(rhs.isAds))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.projectId == rhs.projectId)||((this.projectId!= null)&&this.projectId.equals(rhs.projectId))))&&((this.content == rhs.content)||((this.content!= null)&&this.content.equals(rhs.content))))&&((this.algorithmId == rhs.algorithmId)||((this.algorithmId!= null)&&this.algorithmId.equals(rhs.algorithmId))));
    }

}
