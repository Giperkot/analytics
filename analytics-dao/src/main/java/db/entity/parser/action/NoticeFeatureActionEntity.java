package db.entity.parser.action;


import db.annotations.Entity;
import db.annotations.Table;
import db.entity.DiffAction;

@Entity
@Table(name = "notice_feature_action", schema = "parser")
public class NoticeFeatureActionEntity extends NoticeActionEntity {
    public NoticeFeatureActionEntity(DiffAction diffAction, long noticeId) {
        super(diffAction, noticeId);
    }

    public NoticeFeatureActionEntity(String field, String title, String value, long noticeId) {
        super(field, title, value, noticeId);
    }
}
