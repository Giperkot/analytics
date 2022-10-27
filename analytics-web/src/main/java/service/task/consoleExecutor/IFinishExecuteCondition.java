package service.task.consoleExecutor;

import dto.task.ConsoleResultWrapperDto;

public interface IFinishExecuteCondition {

    public ConsoleResultWrapperDto getResult (String line);

}
