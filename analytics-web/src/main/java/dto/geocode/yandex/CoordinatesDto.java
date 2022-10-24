package dto.geocode.yandex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ymaps")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesDto {

    @JacksonXmlProperty(localName = "GeoObjectCollection")
    private GeoObjectCollection geoObjectCollection;

    public GeoObjectCollection getGeoObjectCollection() {
        return geoObjectCollection;
    }

    public void setGeoObjectCollection(GeoObjectCollection geoObjectCollection) {
        this.geoObjectCollection = geoObjectCollection;
    }
}
