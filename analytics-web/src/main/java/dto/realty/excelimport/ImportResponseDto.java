package dto.realty.excelimport;

import java.util.List;

public class ImportResponseDto {

    private long requestId;

    private List<ImportExcelRealtyDto> importExcelRealtyDtoList;

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
}
