package jcefutils;

import enums.console.EConsoleStatusCode;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefLoadHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoGisLoadHandler extends CefLoadHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwoGisLoadHandler.class);

    private static String lastUrl = "";

    private int loadCount = 0;

    @Override
    public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {

        browser.getText((str) -> {
            LOGGER.info("Сценарии отработаны");
            LOGGER.info("RESULT[" + EConsoleStatusCode.SUCCESS_OK.getTitle() + "]. " + str);
        });


        if (lastUrl.equals(browser.getURL()) || loadCount < 1) {
            // Чтобы убрать двойную обработку...
            loadCount++;
            return;
        }




        lastUrl = browser.getURL();

        LOGGER.info("Page is loaded: " + browser.getURL());

        loadCount++;
    }

    public int getLoadCount() {
        return loadCount;
    }
}
