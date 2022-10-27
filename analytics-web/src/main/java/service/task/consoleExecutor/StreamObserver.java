package service.task.consoleExecutor;

import dto.task.ConsoleResultWrapperDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamObserver extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamObserver.class);

    private InputStream is;
    private String type;
    private ConsoleExecutor parentObserver;
    IFinishExecuteCondition finishExecuteCondition;
    private Process proc;

/*    public StreamObserver(InputStream is, String type, IFinishExecuteCondition finishExecuteCondition) {
        this.is = is;
        this.type = type;
        this.finishExecuteCondition = finishExecuteCondition;
    }*/

    public StreamObserver(InputStream is, String type, ConsoleExecutor parentObserver, Process proc, IFinishExecuteCondition finishExecuteCondition) {
        this.is = is;
        this.type = type;
        this.parentObserver = parentObserver;
        this.proc = proc;
        this.finishExecuteCondition = finishExecuteCondition;
    }

    public void run() {
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);){


            String line=null;
            while ( (line = br.readLine()) != null) {
                LOGGER.info(type + ">" + line);

                ConsoleResultWrapperDto consoleResultWrapper = finishExecuteCondition.getResult(line);

                if (consoleResultWrapper != null) {
                    parentObserver.setResultObject(consoleResultWrapper);

                    proc.destroy();
                }
            }
//                System.out.println(type + ">" + line);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
