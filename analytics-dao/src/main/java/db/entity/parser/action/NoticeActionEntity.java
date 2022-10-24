package db.entity.parser.action;

import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.DiffAction;
import db.entity.action.ALiteActionEntity;

@Entity
@Table(name = "notice_action", schema = "parser")
public class NoticeActionEntity extends ALiteActionEntity {

    @Column(name = "notice_id")
    private long noticeId;

    public NoticeActionEntity(DiffAction diffAction, long noticeId) {
        super(diffAction);
        this.noticeId = noticeId;
    }

    public NoticeActionEntity(String field, String title, String value, long noticeId) {
        super(field, title, value);
        this.noticeId = noticeId;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }
}
