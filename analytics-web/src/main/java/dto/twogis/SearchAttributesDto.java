
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
    "handling_type",
    "suggest_parts",
    "suggested_text"
})
@Generated("jsonschema2pojo")
public class SearchAttributesDto {

    @JsonProperty("handling_type")
    private Integer handlingType;
    @JsonProperty("suggest_parts")
    private List<SuggestPartDto> suggestParts = new ArrayList<SuggestPartDto>();
    @JsonProperty("suggested_text")
    private String suggestedText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("handling_type")
    public Integer getHandlingType() {
        return handlingType;
    }

    @JsonProperty("handling_type")
    public void setHandlingType(Integer handlingType) {
        this.handlingType = handlingType;
    }

    @JsonProperty("suggest_parts")
    public List<SuggestPartDto> getSuggestParts() {
        return suggestParts;
    }

    @JsonProperty("suggest_parts")
    public void setSuggestParts(List<SuggestPartDto> suggestParts) {
        this.suggestParts = suggestParts;
    }

    @JsonProperty("suggested_text")
    public String getSuggestedText() {
        return suggestedText;
    }

    @JsonProperty("suggested_text")
    public void setSuggestedText(String suggestedText) {
        this.suggestedText = suggestedText;
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
        sb.append(SearchAttributesDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("handlingType");
        sb.append('=');
        sb.append(((this.handlingType == null)?"<null>":this.handlingType));
        sb.append(',');
        sb.append("suggestParts");
        sb.append('=');
        sb.append(((this.suggestParts == null)?"<null>":this.suggestParts));
        sb.append(',');
        sb.append("suggestedText");
        sb.append('=');
        sb.append(((this.suggestedText == null)?"<null>":this.suggestedText));
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
        result = ((result* 31)+((this.suggestedText == null)? 0 :this.suggestedText.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.handlingType == null)? 0 :this.handlingType.hashCode()));
        result = ((result* 31)+((this.suggestParts == null)? 0 :this.suggestParts.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchAttributesDto) == false) {
            return false;
        }
        SearchAttributesDto rhs = ((SearchAttributesDto) other);
        return (((((this.suggestedText == rhs.suggestedText)||((this.suggestedText!= null)&&this.suggestedText.equals(rhs.suggestedText)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.handlingType == rhs.handlingType)||((this.handlingType!= null)&&this.handlingType.equals(rhs.handlingType))))&&((this.suggestParts == rhs.suggestParts)||((this.suggestParts!= null)&&this.suggestParts.equals(rhs.suggestParts))));
    }

}
