
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
    "branches",
    "database_entrances",
    "nearest_parking",
    "nearest_stations",
    "providers",
    "servicing"
})
@Generated("jsonschema2pojo")
public class LinksDto {

    @JsonProperty("branches")
    private BranchesDto branches;
    @JsonProperty("database_entrances")
    private List<DatabaseEntranceDto> databaseEntrances = new ArrayList<DatabaseEntranceDto>();
    @JsonProperty("nearest_parking")
    private List<NearestParkingDto> nearestParking = new ArrayList<NearestParkingDto>();
    @JsonProperty("nearest_stations")
    private List<NearestStationDto> nearestStations = new ArrayList<NearestStationDto>();
    @JsonProperty("providers")
    private ProvidersDto providers;
    @JsonProperty("servicing")
    private ServicingDto servicing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("branches")
    public BranchesDto getBranches() {
        return branches;
    }

    @JsonProperty("branches")
    public void setBranches(BranchesDto branches) {
        this.branches = branches;
    }

    @JsonProperty("database_entrances")
    public List<DatabaseEntranceDto> getDatabaseEntrances() {
        return databaseEntrances;
    }

    @JsonProperty("database_entrances")
    public void setDatabaseEntrances(List<DatabaseEntranceDto> databaseEntrances) {
        this.databaseEntrances = databaseEntrances;
    }

    @JsonProperty("nearest_parking")
    public List<NearestParkingDto> getNearestParking() {
        return nearestParking;
    }

    @JsonProperty("nearest_parking")
    public void setNearestParking(List<NearestParkingDto> nearestParking) {
        this.nearestParking = nearestParking;
    }

    @JsonProperty("nearest_stations")
    public List<NearestStationDto> getNearestStations() {
        return nearestStations;
    }

    @JsonProperty("nearest_stations")
    public void setNearestStations(List<NearestStationDto> nearestStations) {
        this.nearestStations = nearestStations;
    }

    @JsonProperty("providers")
    public ProvidersDto getProviders() {
        return providers;
    }

    @JsonProperty("providers")
    public void setProviders(ProvidersDto providers) {
        this.providers = providers;
    }

    @JsonProperty("servicing")
    public ServicingDto getServicing() {
        return servicing;
    }

    @JsonProperty("servicing")
    public void setServicing(ServicingDto servicing) {
        this.servicing = servicing;
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
        sb.append(LinksDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("branches");
        sb.append('=');
        sb.append(((this.branches == null)?"<null>":this.branches));
        sb.append(',');
        sb.append("databaseEntrances");
        sb.append('=');
        sb.append(((this.databaseEntrances == null)?"<null>":this.databaseEntrances));
        sb.append(',');
        sb.append("nearestParking");
        sb.append('=');
        sb.append(((this.nearestParking == null)?"<null>":this.nearestParking));
        sb.append(',');
        sb.append("nearestStations");
        sb.append('=');
        sb.append(((this.nearestStations == null)?"<null>":this.nearestStations));
        sb.append(',');
        sb.append("providers");
        sb.append('=');
        sb.append(((this.providers == null)?"<null>":this.providers));
        sb.append(',');
        sb.append("servicing");
        sb.append('=');
        sb.append(((this.servicing == null)?"<null>":this.servicing));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.branches == null)? 0 :this.branches.hashCode()));
        result = ((result* 31)+((this.nearestParking == null)? 0 :this.nearestParking.hashCode()));
        result = ((result* 31)+((this.databaseEntrances == null)? 0 :this.databaseEntrances.hashCode()));
        result = ((result* 31)+((this.providers == null)? 0 :this.providers.hashCode()));
        result = ((result* 31)+((this.nearestStations == null)? 0 :this.nearestStations.hashCode()));
        result = ((result* 31)+((this.servicing == null)? 0 :this.servicing.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LinksDto) == false) {
            return false;
        }
        LinksDto rhs = ((LinksDto) other);
        return ((((((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.branches == rhs.branches)||((this.branches!= null)&&this.branches.equals(rhs.branches))))&&((this.nearestParking == rhs.nearestParking)||((this.nearestParking!= null)&&this.nearestParking.equals(rhs.nearestParking))))&&((this.databaseEntrances == rhs.databaseEntrances)||((this.databaseEntrances!= null)&&this.databaseEntrances.equals(rhs.databaseEntrances))))&&((this.providers == rhs.providers)||((this.providers!= null)&&this.providers.equals(rhs.providers))))&&((this.nearestStations == rhs.nearestStations)||((this.nearestStations!= null)&&this.nearestStations.equals(rhs.nearestStations))))&&((this.servicing == rhs.servicing)||((this.servicing!= null)&&this.servicing.equals(rhs.servicing))));
    }

}
