package dto.realty;

import db.entity.realty.DistrictEntity;

import java.util.ArrayList;
import java.util.List;

public class DistrictWrapper {

    private DistrictEntity districtEntity;

    private List<DistrictWrapper> districtWrapperList;

    public static DistrictWrapper create(DistrictEntity districtEntity) {
        DistrictWrapper wrapper = new DistrictWrapper();
        wrapper.districtEntity = districtEntity;
        wrapper.districtWrapperList = new ArrayList<>();

        return wrapper;
    }

    public DistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public void setDistrictEntity(DistrictEntity districtEntity) {
        this.districtEntity = districtEntity;
    }

    public List<DistrictWrapper> getDistrictWrapperList() {
        return districtWrapperList;
    }

    public void setDistrictWrapperList(List<DistrictWrapper> districtWrapperList) {
        this.districtWrapperList = districtWrapperList;
    }
}
