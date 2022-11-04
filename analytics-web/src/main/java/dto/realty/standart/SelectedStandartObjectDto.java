package dto.realty.standart;

public class SelectedStandartObjectDto {

    private long requestId;

    private long[] standardRecords;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long[] getStandardRecords() {
        return standardRecords;
    }

    public void setStandardRecords(long[] standardRecords) {
        this.standardRecords = standardRecords;
    }
}
