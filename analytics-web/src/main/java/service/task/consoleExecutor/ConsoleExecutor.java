package service.task.consoleExecutor;

import dto.task.ConsoleResultWrapperDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class ConsoleExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleExecutor.class);

    private ConsoleResultWrapperDto resultObject;

    public ConsoleResultWrapperDto startObserve (String[] command, String[] env, IFinishExecuteCondition outputCondition, IFinishExecuteCondition errorCondition) throws Exception {

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(command, env);

        try (InputStream errorStream = proc.getErrorStream();
             InputStream inputStream = proc.getInputStream();
        ) {
            // any error message?
            StreamObserver errorGobbler = new StreamObserver(errorStream, "ERROR", this, proc, errorCondition);
            // any output?
            StreamObserver outputGobbler = new StreamObserver(inputStream, "OUTPUT", this, proc, outputCondition);

            // kick them off
            errorGobbler.start();
            outputGobbler.start();

            // any error???
            int exitVal = proc.waitFor();
            LOGGER.warn("ExitValue: " + exitVal);

            if (exitVal != 0 && resultObject == null) {
                throw new RuntimeException("Ошибка получения консольного результата");
            }

            return resultObject;
        }
    }

    public ConsoleResultWrapperDto getResultObject() {
        return resultObject;
    }

    public void setResultObject(ConsoleResultWrapperDto resultObject) {
        this.resultObject = resultObject;
    }

}
