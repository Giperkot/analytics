package dto.realty;

public class VNoticeInfoWithAvgPriceDto {

    private Long id;

    private String title;

    private double sum;

    private String url;

    private String street;

    private String houseNum;

    private String name;

    private double squareValue;

    private double averageSum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
