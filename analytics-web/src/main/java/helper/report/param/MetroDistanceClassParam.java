package helper.report.param;

import consts.ProjectConst;
import db.entity.parser.view.VFeatureValueEntity;
import dto.parser.KeyValue;
import dto.report.NoticeWrapper;
import enums.EFeatureExactName;
import enums.report.EMetroDistance;
import helper.report.IClassParam;
import interfaces.report.ITitled;

import java.io.IOException;

public class MetroDistanceClassParam implements IClassParam {
    @Override
    public int getOrderByValue(NoticeWrapper value) {

        VFeatureValueEntity metroFeature = value.getFeatureByExactName(EFeatureExactName.METRO);

        if (metroFeature == null || metroFeature.getValue() == null) {
            return EMetroDistance.UNKNOWN.getId();
        }

        try {
            KeyValue[] metroArr = ProjectConst.MAPPER.readValue(metroFeature.getValue(), KeyValue[].class);

            if (metroArr.length > 0) {
                if ("до 5 мин.".equals(metroArr[0].value)) {
                    return EMetroDistance.LESS5.getId();
                }

                if ("6–10 мин.".equals(metroArr[0].value)) {
                    return EMetroDistance.FROM5TO10.getId();
                }

                if ("11–15 мин.".equals(metroArr[0].value)) {
                    return EMetroDistance.FROM10TO15.getId();
                }

                if ("21–30 мин.".equals(metroArr[0].value)) {
                    return EMetroDistance.FROM15TO30.getId();
                }

                if ("от 31 мин.".equals(metroArr[0].value)) {
                    return EMetroDistance.FROM30TO60.getId();
                }
            }

            return EMetroDistance.UNKNOWN.getId();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при парсинге расстояния до метро");
        }
    }

    @Override
    public ITitled getValueByOrdinal(int ordinal) {
        return EMetroDistance.getByOrdinal(ordinal);
    }

    @Override
    public int length() {
        return EMetroDistance.getValues().length;
    }
}
