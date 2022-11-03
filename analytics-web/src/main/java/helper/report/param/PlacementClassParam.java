package helper.report.param;

import db.entity.realty.ShortDistrictEntity;
import dto.report.NoticeWrapper;
import gnu.trove.map.hash.TLongIntHashMap;
import helper.report.IClassParam;
import interfaces.report.ITitled;

import java.util.List;

public class PlacementClassParam implements IClassParam {

    private final List<ShortDistrictEntity> shortDistrictEntityList;

    private final TLongIntHashMap idToNumberHashMap = new TLongIntHashMap();

    public PlacementClassParam(List<ShortDistrictEntity> shortDistrictEntityList) {
        this.shortDistrictEntityList = shortDistrictEntityList;

        for (int i = 0; i < shortDistrictEntityList.size(); i++) {
            ShortDistrictEntity shortDistrictEntity = shortDistrictEntityList.get(i);
            idToNumberHashMap.put(shortDistrictEntity.getId(), i);
        }
    }

    @Override
    public int getOrderByValue(NoticeWrapper value) {
        long districtId = value.getNoticeEntity().getDistrictId();

        return idToNumberHashMap.get(districtId);
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return shortDistrictEntityList.get(ordinal);
    }

    @Override
    public int length() {
        return shortDistrictEntityList.size();
    }
}
