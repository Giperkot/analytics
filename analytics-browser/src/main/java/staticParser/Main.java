package staticParser;


import core.BrowserContext;
import enums.console.EConsoleMode;
import enums.console.EConsoleStatusCode;
import jcefutils.TwoGisLoadHandler;
import jcefutils.TwoGisMessageRouterHandler;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefAppHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;

/**

 */

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

//    public static JFrame frame;
    public static CefBrowser browser;

    public static void main (String... args) throws UnsupportedEncodingException {

        if (!CefApp.startup()) {
            LOGGER.error("Startup initialization failed!");
            return;
        }

        if (args.length < 2) {
            throw new RuntimeException("Не хватает параметров. Ссылка.");
        }

        EConsoleMode consoleMode = EConsoleMode.getByString(args[0]);

        if (consoleMode != EConsoleMode.TWO_GIS_BY_ID) {
            LOGGER.info("RESULT[" + EConsoleStatusCode.ERROR_NO_RESPONSE.getTitle() + "]. Запуск в неверной конфигурации " + args[0]);
        }

        String url = args[1];
        BrowserContext appConfig = new BrowserContext();

        boolean isDebug = appConfig.isDebug();

        boolean offscreenRenderer = true;

        if (true || isDebug) {
            offscreenRenderer = false;
        }

        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefApp.CefAppState.TERMINATED) {
                    System.exit(0);
                }
            }
        });
        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = false;
        CefApp cefApp = CefApp.getInstance(settings);

        CefClient client = cefApp.createClient();
        CefBrowser browser = client.createBrowser(url, offscreenRenderer, offscreenRenderer);

        if (true || isDebug) {
            Component cefBrowserPane = browser.getUIComponent();

            JFrame frame = new JFrame();
            frame.add(cefBrowserPane, BorderLayout.CENTER);
            frame.setSize(1680, 1050);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Main.browser = browser;

        /**
         * Подписка на события js Из компонента Браузер
         */
        CefMessageRouter msgRouter = CefMessageRouter.create();

        client.addMessageRouter(msgRouter);

        client.addLoadHandler(new TwoGisLoadHandler());
        msgRouter.addHandler(new TwoGisMessageRouterHandler(), true);

        browser.createImmediately();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static Properties loadProperties (String fileName) {
        Properties props = null;

        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is != null) {
                props = new Properties();
                props.load(is);
            }
        } catch (Exception e) {
            LOGGER.warn("Не удалось подтянуть main.properties", e);
        }
        return props;
    }

}
