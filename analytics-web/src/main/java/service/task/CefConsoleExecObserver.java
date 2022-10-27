package service.task;

import consts.RegexpConst;
import core.AnalyticsContext;
import core.config.AnalyticsConfig;
import dto.task.ConsoleResultWrapperDto;
import enums.console.EConsoleMode;
import enums.console.EConsoleStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.task.consoleExecutor.ConsoleExecutor;
import service.task.consoleExecutor.IFinishExecuteCondition;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;


public class CefConsoleExecObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CefConsoleExecObserver.class);

    // Условие получения результата
    private final static IFinishExecuteCondition finishExecuteCondition = (String line) -> {
        Matcher matcher = RegexpConst.resultPattern.matcher(line);

        if (!matcher.matches()) {
            return null;
        }

        ConsoleResultWrapperDto result = new ConsoleResultWrapperDto();

        result.setResultCode(EConsoleStatusCode.getByString(matcher.group(1)));
        result.setResultObjectStr(matcher.group(2).trim());

        result.setSuccess(result.getResultCode() == EConsoleStatusCode.SUCCESS_OK);

        return result;
    };

    public ConsoleResultWrapperDto getHouseInfo (String url) {
        AnalyticsConfig.ConsoleScript consoleScriptConfig = AnalyticsContext.getConfig().getConsoleScriptConf();
        String jarPath = new String(consoleScriptConfig.getterJarPath.getBytes(), StandardCharsets.UTF_8);
        String libraryPath = consoleScriptConfig.getterLibraryPath;

//        String[] command;
        String[] env;
        String[] command = {consoleScriptConfig.javaPath, "-Djava.library.path=" + libraryPath, "-Dfile.encoding=UTF-8", "-jar", jarPath, EConsoleMode.TWO_GIS_BY_ID.getName(), url};
//        String[] commandDebug = {consoleScriptConfig.javaPath, "-Djava.library.path=" + libraryPath, "-Dfile.encoding=UTF-8", "-jar", jarPath, EConsoleMode.TWO_GIS_BY_ID.getName(), url};
        String[] envProd = {"DISPLAY=:100", "LD_LIBRARY_PATH=/additions/jdk8/jre/lib/amd64/"};

        if (AnalyticsContext.getConfig().getDebugProp().debugScheduler) {
            // В графическом режиме
            env = null;
        } else {
            env = envProd;
        }

//        LOGGER.info("Console comand: " + consoleScriptConfig.javaPath + " -Djava.library.path=" + libraryPath + " -Dfile.encoding=UTF-8 -jar " + jarPath + " " + EConsoleMode.ORDER_MODE.getName() + " " + credentials + " " + infoToGetterScriptDto.getCadastrialNumber() + " \"" + URLEncoder.encode(infoToGetterScriptDto.getMacroRegion()) + "\"");


        try {
            ConsoleExecutor consoleExecutor = new ConsoleExecutor();
            return consoleExecutor.startObserve(command, env, finishExecuteCondition, finishExecuteCondition);
        } catch (Exception t) {
            LOGGER.error("Ошибка при получении информации о доме: " + url, t);
            throw new RuntimeException("Ошибка при получении информации о доме: " + url, t);
        }
    }
}
