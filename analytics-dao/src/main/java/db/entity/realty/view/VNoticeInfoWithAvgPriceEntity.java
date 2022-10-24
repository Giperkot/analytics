package db.entity.realty.view;


import db.annotations.Column;
import db.annotations.Entity;
import db.annotations.Table;

@Entity
@Table(name = "v_notice_info_with_avg_price", schema = "realty")
public class VNoticeInfoWithAvgPriceEntity {

    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "sum")
    private double sum;

    @Column(name = "url")
    private String url;

    @Column(name = "street")
    private String street;

    @Column(name = "house_num")
    private String houseNum;

    @Column(name = "name")
    private String name;

    @Column(name = "square_value")
    private double squareValue;

    @Column(name = "average_sum")
    private double averageSum;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(double squareValue) {
        this.squareValue = squareValue;
    }

    public double getAverageSum() {
        return averageSum;
    }

    public void setAverageSum(double averageSum) {
        this.averageSum = averageSum;
    }
}
