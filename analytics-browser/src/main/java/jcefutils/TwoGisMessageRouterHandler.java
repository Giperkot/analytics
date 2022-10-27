package jcefutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoGisMessageRouterHandler extends CefMessageRouterHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwoGisMessageRouterHandler.class);

    @Override
    public boolean onQuery(CefBrowser browser, CefFrame frame, long queryId, String request, boolean persistent, CefQueryCallback callback) {
        LOGGER.info(request); // prints "Hello World"

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            /*ScenarioResultDto result = objectMapper.readValue(request, ScenarioResultDto.class);

            if (!result.isSuccess()) {
                LOGGER.error("RESULT[" + EConsoleStatusCode.ERROR_IN_JS_SCRIPT.getTitle() + "]. Ошибка js: " + result.getMessage());
                throw new RuntimeException(result.getReturnObj());
            }*/

        } catch (Exception e) {
            LOGGER.error("Ошибка парсинга результата", e);
        }

        return true;
    }
}
