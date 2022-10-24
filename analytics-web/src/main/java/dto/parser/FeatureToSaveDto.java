package dto.parser;

import db.entity.parser.FeatureEntity;

import java.util.List;
import java.util.Map;

public class FeatureToSaveDto {

    private List<FeatureDto> featureForCreatingList;

    private Map<String, FeatureEntity> featureMapInDB;

    private Map<String, FeatureComplexValue> featureValueMap;

    public List<FeatureDto> getFeatureForCreatingList() {
        return featureForCreatingList;
    }

    public void setFeatureForCreatingList(List<FeatureDto> featureForCreatingList) {
        this.featureForCreatingList = featureForCreatingList;
    }

    public Map<String, FeatureEntity> getFeatureMapInDB() {
        return featureMapInDB;
    }

    public void setFeatureMapInDB(Map<String, FeatureEntity> featureMapInDB) {
        this.featureMapInDB = featureMapInDB;
    }

    public Map<String, FeatureComplexValue> getFeatureValueMap() {
        return featureValueMap;
    }

    public void setFeatureValueMap(Map<String, FeatureComplexValue> featureValueMap) {
        this.featureValueMap = featureValueMap;
    }
}
