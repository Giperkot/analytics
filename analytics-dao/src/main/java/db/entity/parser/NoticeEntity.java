package db.entity.parser;

import db.annotations.Column;
import db.annotations.DiffInfo;
import db.annotations.Entity;
import db.annotations.Table;
import db.entity.BaseEntity;
import enums.ENoticeStatus;

import java.sql.Timestamp;

@Entity
@Table(name = "notice", schema = "parser")
public class NoticeEntity extends BaseEntity {

    @Column(name = "parse_task_id")
    private Long parseTaskId;

    @DiffInfo(value = "Заголовок")
    @Column(name = "title")
    private String title;

    @DiffInfo(value = "Цена")
    @Column(name = "sum")
    private Double sum;

    @DiffInfo(value = "Адрес")
    @Column(name = "address")
    private String address;

    @DiffInfo(value = "Описание")
    @Column(name = "description")
    private String description;

    @DiffInfo(value = "Статус объявления")
    @Column(name = "status")
    private ENoticeStatus status;

    @Column(name = "house_id")
    private Long houseId;

    @Column(name = "close_date")
    private Timestamp closeDate;

    public void setParseTaskId(Long parseTaskId) {
        this.parseTaskId = parseTaskId;
    }

    public Long getParseTaskId() {
        return parseTaskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getSum() {
        return sum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public ENoticeStatus getStatus() {
        return status;
    }

    public void setStatus(ENoticeStatus status) {
        this.status = status;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }
}
