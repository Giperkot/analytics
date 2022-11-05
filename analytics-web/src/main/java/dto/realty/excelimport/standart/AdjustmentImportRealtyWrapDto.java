package dto.realty.excelimport.standart;

import dto.realty.AdjustCoeffsDto;
import dto.realty.excelimport.ImportExcelRealtyDto;

import java.util.Map;

public class AdjustmentImportRealtyWrapDto {

    private ImportExcelRealtyDto object;

    private Map<String, AdjustCoeffsDto> adjustCoeffsMap;

    public ImportExcelRealtyDto getObject() {
        return object;
    }

    public void setObject(ImportExcelRealtyDto object) {
        this.object = object;
    }

    public Map<String, AdjustCoeffsDto> getAdjustCoeffsMap() {
        return adjustCoeffsMap;
    }

    public void setAdjustCoeffsMap(Map<String, AdjustCoeffsDto> adjustCoeffsMap) {
        this.adjustCoeffsMap = adjustCoeffsMap;
    }
}
