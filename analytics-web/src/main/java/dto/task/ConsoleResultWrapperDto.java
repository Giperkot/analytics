package dto.task;

import core.pipeline.dto.IExternalResponseDto;
import enums.console.EConsoleStatusCode;

public class ConsoleResultWrapperDto implements IExternalResponseDto {

    private boolean success;

    private EConsoleStatusCode resultCode;

    private String resultObjectStr;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultObjectStr() {
        return resultObjectStr;
    }

    public void setResultObjectStr(String resultObjectStr) {
        this.resultObjectStr = resultObjectStr;
    }

    public EConsoleStatusCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(EConsoleStatusCode resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public boolean isSuccessResponse() {
        return success;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public String getResponseMessage() {
        return resultCode.getTitle() + ". " + resultObjectStr;
    }

    @Override
    public String getMethodTitle() {
        return null;
    }
}
