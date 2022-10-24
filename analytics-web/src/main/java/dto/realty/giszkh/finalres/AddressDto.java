
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
    "region",
    "area",
    "city",
    "ctar",
    "additionalTerritory",
    "additionalTerStreet",
    "settlement",
    "planningStructureElement",
    "street",
    "house",
    "formattedAddress",
    "deprecated"
})
@Generated("jsonschema2pojo")
public class AddressDto {

    @JsonProperty("region")
    private RegionDto region;
    @JsonProperty("area")
    private Object area;
    @JsonProperty("city")
    private CityDto city;
    @JsonProperty("ctar")
    private Object ctar;
    @JsonProperty("additionalTerritory")
    private Object additionalTerritory;
    @JsonProperty("additionalTerStreet")
    private Object additionalTerStreet;
    @JsonProperty("settlement")
    private Object settlement;
    @JsonProperty("planningStructureElement")
    private Object planningStructureElement;
    @JsonProperty("street")
    private StreetDto street;
    @JsonProperty("house")
    private HouseDto house;
    @JsonProperty("formattedAddress")
    private String formattedAddress;
    @JsonProperty("deprecated")
    private Boolean deprecated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("region")
    public RegionDto getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(RegionDto region) {
        this.region = region;
    }

    @JsonProperty("area")
    public Object getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(Object area) {
        this.area = area;
    }

    @JsonProperty("city")
    public CityDto getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(CityDto city) {
        this.city = city;
    }

    @JsonProperty("ctar")
    public Object getCtar() {
        return ctar;
    }

    @JsonProperty("ctar")
    public void setCtar(Object ctar) {
        this.ctar = ctar;
    }

    @JsonProperty("additionalTerritory")
    public Object getAdditionalTerritory() {
        return additionalTerritory;
    }

    @JsonProperty("additionalTerritory")
    public void setAdditionalTerritory(Object additionalTerritory) {
        this.additionalTerritory = additionalTerritory;
    }

    @JsonProperty("additionalTerStreet")
    public Object getAdditionalTerStreet() {
        return additionalTerStreet;
    }

    @JsonProperty("additionalTerStreet")
    public void setAdditionalTerStreet(Object additionalTerStreet) {
        this.additionalTerStreet = additionalTerStreet;
    }

    @JsonProperty("settlement")
    public Object getSettlement() {
        return settlement;
    }

    @JsonProperty("settlement")
    public void setSettlement(Object settlement) {
        this.settlement = settlement;
    }

    @JsonProperty("planningStructureElement")
    public Object getPlanningStructureElement() {
        return planningStructureElement;
    }

    @JsonProperty("planningStructureElement")
    public void setPlanningStructureElement(Object planningStructureElement) {
        this.planningStructureElement = planningStructureElement;
    }

    @JsonProperty("street")
    public StreetDto getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(StreetDto street) {
        this.street = street;
    }

    @JsonProperty("house")
    public HouseDto getHouse() {
        return house;
    }

    @JsonProperty("house")
    public void setHouse(HouseDto house) {
        this.house = house;
    }

    @JsonProperty("formattedAddress")
    public String getFormattedAddress() {
        return formattedAddress;
    }

    @JsonProperty("formattedAddress")
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @JsonProperty("deprecated")
    public Boolean getDeprecated() {
        return deprecated;
    }

    @JsonProperty("deprecated")
    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
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
        sb.append(AddressDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("region");
        sb.append('=');
        sb.append(((this.region == null)?"<null>":this.region));
        sb.append(',');
        sb.append("area");
        sb.append('=');
        sb.append(((this.area == null)?"<null>":this.area));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("ctar");
        sb.append('=');
        sb.append(((this.ctar == null)?"<null>":this.ctar));
        sb.append(',');
        sb.append("additionalTerritory");
        sb.append('=');
        sb.append(((this.additionalTerritory == null)?"<null>":this.additionalTerritory));
        sb.append(',');
        sb.append("additionalTerStreet");
        sb.append('=');
        sb.append(((this.additionalTerStreet == null)?"<null>":this.additionalTerStreet));
        sb.append(',');
        sb.append("settlement");
        sb.append('=');
        sb.append(((this.settlement == null)?"<null>":this.settlement));
        sb.append(',');
        sb.append("planningStructureElement");
        sb.append('=');
        sb.append(((this.planningStructureElement == null)?"<null>":this.planningStructureElement));
        sb.append(',');
        sb.append("street");
        sb.append('=');
        sb.append(((this.street == null)?"<null>":this.street));
        sb.append(',');
        sb.append("house");
        sb.append('=');
        sb.append(((this.house == null)?"<null>":this.house));
        sb.append(',');
        sb.append("formattedAddress");
        sb.append('=');
        sb.append(((this.formattedAddress == null)?"<null>":this.formattedAddress));
        sb.append(',');
        sb.append("deprecated");
        sb.append('=');
        sb.append(((this.deprecated == null)?"<null>":this.deprecated));
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
        result = ((result* 31)+((this.area == null)? 0 :this.area.hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.planningStructureElement == null)? 0 :this.planningStructureElement.hashCode()));
        result = ((result* 31)+((this.deprecated == null)? 0 :this.deprecated.hashCode()));
        result = ((result* 31)+((this.ctar == null)? 0 :this.ctar.hashCode()));
        result = ((result* 31)+((this.additionalTerritory == null)? 0 :this.additionalTerritory.hashCode()));
        result = ((result* 31)+((this.house == null)? 0 :this.house.hashCode()));
        result = ((result* 31)+((this.additionalTerStreet == null)? 0 :this.additionalTerStreet.hashCode()));
        result = ((result* 31)+((this.settlement == null)? 0 :this.settlement.hashCode()));
        result = ((result* 31)+((this.formattedAddress == null)? 0 :this.formattedAddress.hashCode()));
        result = ((result* 31)+((this.street == null)? 0 :this.street.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.region == null)? 0 :this.region.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddressDto) == false) {
            return false;
        }
        AddressDto rhs = ((AddressDto) other);
        return ((((((((((((((this.area == rhs.area)||((this.area!= null)&&this.area.equals(rhs.area)))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.planningStructureElement == rhs.planningStructureElement)||((this.planningStructureElement!= null)&&this.planningStructureElement.equals(rhs.planningStructureElement))))&&((this.deprecated == rhs.deprecated)||((this.deprecated!= null)&&this.deprecated.equals(rhs.deprecated))))&&((this.ctar == rhs.ctar)||((this.ctar!= null)&&this.ctar.equals(rhs.ctar))))&&((this.additionalTerritory == rhs.additionalTerritory)||((this.additionalTerritory!= null)&&this.additionalTerritory.equals(rhs.additionalTerritory))))&&((this.house == rhs.house)||((this.house!= null)&&this.house.equals(rhs.house))))&&((this.additionalTerStreet == rhs.additionalTerStreet)||((this.additionalTerStreet!= null)&&this.additionalTerStreet.equals(rhs.additionalTerStreet))))&&((this.settlement == rhs.settlement)||((this.settlement!= null)&&this.settlement.equals(rhs.settlement))))&&((this.formattedAddress == rhs.formattedAddress)||((this.formattedAddress!= null)&&this.formattedAddress.equals(rhs.formattedAddress))))&&((this.street == rhs.street)||((this.street!= null)&&this.street.equals(rhs.street))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.region == rhs.region)||((this.region!= null)&&this.region.equals(rhs.region))));
    }

}
