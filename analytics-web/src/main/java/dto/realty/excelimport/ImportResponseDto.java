package dto.realty.excelimport;

import java.util.List;

public class ImportResponseDto {

    private long requestId;

    private List<ImportExcelRealtyDto> importExcelRealtyDtoList;

    private String exception;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public List<ImportExcelRealtyDto> getImportExcelRealtyDtoList() {
        return importExcelRealtyDtoList;
    }

    public void setImportExcelRealtyDtoList(List<ImportExcelRealtyDto> importExcelRealtyDtoList) {
        this.importExcelRealtyDtoList = importExcelRealtyDtoList;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
