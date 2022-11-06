package dto.realty.excelimport.standart;

import java.util.List;

public class StandartRequestWrapper {

    private long requestId;

    private List<EvalutionStandartObjDto> evalutionStandartObjDtoList;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public List<EvalutionStandartObjDto> getEvalutionStandartObjDtoList() {
        return evalutionStandartObjDtoList;
    }

    public void setEvalutionStandartObjDtoList(List<EvalutionStandartObjDto> evalutionStandartObjDtoList) {
        this.evalutionStandartObjDtoList = evalutionStandartObjDtoList;
    }
}
