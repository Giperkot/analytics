import db.entity.parser.view.VDistrictNoticeEntity;
import db.entity.parser.view.VFeatureValueEntity;
import db.entity.realty.ShortDistrictEntity;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import helper.report.ReportClassifier;
import helper.report.param.FloorClassParam;
import helper.report.param.HouseFloorClassParam;
import helper.report.param.PlacementClassParam;
import interfaces.report.ITitled;

import java.util.ArrayList;
import java.util.List;

public class ClassifierTest {

    public static void main(String[] args) {

        ReportClassifier reportClassifier = new ReportClassifier();

        ShortDistrictEntity dist1 = new ShortDistrictEntity();
        dist1.setId(1);
        dist1.setName("dist1");

        ShortDistrictEntity dist2 = new ShortDistrictEntity();
        dist2.setId(2);
        dist2.setName("dist2");

        ShortDistrictEntity dist3 = new ShortDistrictEntity();
        dist3.setId(3);
        dist3.setName("dist3");

        List<ShortDistrictEntity> shortDistrictEntityList = new ArrayList<>();

        shortDistrictEntityList.add(dist1);
        shortDistrictEntityList.add(dist2);
        shortDistrictEntityList.add(dist3);

        PlacementClassParam placementClassParam = new PlacementClassParam(shortDistrictEntityList);

        reportClassifier.addClassParam(placementClassParam);
        reportClassifier.addClassParam(new FloorClassParam());
        reportClassifier.addClassParam(new HouseFloorClassParam());

        // Объявление.
        VFeatureValueEntity feat1 = new VFeatureValueEntity();
        feat1.setExactName(EFeatureExactName.FLOOR);
        feat1.setValue("25");

        VFeatureValueEntity feat2 = new VFeatureValueEntity();
        feat2.setExactName(EFeatureExactName.HOUSE_FLOORS);
        feat2.setValue("25");

        List<VFeatureValueEntity> featureDtoList = new ArrayList<>();
        featureDtoList.add(feat1);
        featureDtoList.add(feat2);

        VDistrictNoticeEntity noticeEntity = new VDistrictNoticeEntity();
        noticeEntity.setDistrictId(1);

        NoticeWrapper noticeWrapper = new NoticeWrapper(noticeEntity, featureDtoList);

        int res = reportClassifier.getNoticePosition(noticeWrapper);

        System.out.println(res);

        List<ITitled> titledList = reportClassifier.getNoticeInfoByPosition(res);

        for (int i = 0; i < titledList.size(); i++) {
            System.out.println(titledList.get(i).getTitle());
        }

    }

}
