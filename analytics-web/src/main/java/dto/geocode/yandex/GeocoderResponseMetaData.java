package dto.geocode.yandex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocoderResponseMetaData {

    @JacksonXmlProperty(localName = "request")
    private String request;

    @JacksonXmlProperty(localName = "found")
    private int found;

    @JacksonXmlProperty(localName = "results")
    private int results;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
