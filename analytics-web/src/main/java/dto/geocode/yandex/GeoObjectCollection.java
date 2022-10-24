package dto.geocode.yandex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoObjectCollection {

    @JacksonXmlProperty(localName = "metaDataProperty")
    private MetaDataProperty metaDataProperty;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "featureMember")
    private List<FeatureMember> featureMemberList;

    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(MetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }

    public List<FeatureMember> getFeatureMemberList() {
        return featureMemberList;
    }

    public void setFeatureMemberList(List<FeatureMember> featureMemberList) {
        this.featureMemberList = featureMemberList;
    }
}
