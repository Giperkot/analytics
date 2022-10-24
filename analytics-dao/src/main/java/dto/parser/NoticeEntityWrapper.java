package dto.parser;

import db.entity.parser.NoticeEntity;

import java.util.List;

public class NoticeEntityWrapper {

    private NoticeEntity noticeEntity;

    private List<FeatureDto> featureDtoList;

    private String redirectUrl;

    public NoticeEntityWrapper(NoticeEntity noticeEntity, List<FeatureDto> featureDtoList) {
        this.noticeEntity = noticeEntity;
        this.featureDtoList = featureDtoList;
    }

    public NoticeEntityWrapper(NoticeEntity noticeEntity, List<FeatureDto> featureDtoList, String redirectUrl) {
        this.noticeEntity = noticeEntity;
        this.featureDtoList = featureDtoList;
        this.redirectUrl = redirectUrl;
    }

    public NoticeEntity getNoticeEntity() {
        return noticeEntity;
    }

    public void setNoticeEntity(NoticeEntity noticeEntity) {
        this.noticeEntity = noticeEntity;
    }

    public List<FeatureDto> getFeatureDtoList() {
        return featureDtoList;
    }

    public void setFeatureDtoList(List<FeatureDto> featureDtoList) {
        this.featureDtoList = featureDtoList;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
