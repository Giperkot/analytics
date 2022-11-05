package dto.realty.excelimport.standart;

import dto.realty.excelimport.ImportExcelRealtyDto;

import java.util.List;

public class EvalutionStandartObjDto {

    private ImportExcelRealtyDto standartObj;

    private List<AdjustmentImportRealtyWrapDto> analogList;

    public ImportExcelRealtyDto getStandartObj() {
        return standartObj;
    }

    public void setStandartObj(ImportExcelRealtyDto standartObj) {
        this.standartObj = standartObj;
    }

    public List<AdjustmentImportRealtyWrapDto> getAnalogList() {
        return analogList;
    }

    public void setAnalogList(List<AdjustmentImportRealtyWrapDto> analogList) {
        this.analogList = analogList;
    }
}
