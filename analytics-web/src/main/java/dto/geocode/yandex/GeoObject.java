package dto.geocode.yandex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoObject {

    /*@JacksonXmlProperty(localName = "GeoObject")
    private GeoObject geoObject;*/

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "name")
    private String name;

    private BoundedByDto boundedBy;

    @JacksonXmlProperty(localName = "Point")
    private PointDto point;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoundedByDto getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(BoundedByDto boundedBy) {
        this.boundedBy = boundedBy;
    }

    public PointDto getPoint() {
        return point;
    }

    public void setPoint(PointDto point) {
        this.point = point;
    }
}
