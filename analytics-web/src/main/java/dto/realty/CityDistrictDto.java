package dto.realty;

public class CityDistrictDto extends CityRequestDto {

    private long[] parentIdArr;

    private int startLevel = 1;

    public long[] getParentIdArr() {
        return parentIdArr;
    }

    public void setParentIdArr(long[] parentIdArr) {
        this.parentIdArr = parentIdArr;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
    }
}
