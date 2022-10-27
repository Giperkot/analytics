package core.config;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class AnalyticsConfig extends AppConfig {

    private final ExternalApiRequestProps externalApiRequestProps = new ExternalApiRequestProps();;

    private final ConsoleScript consoleScriptConf = new ConsoleScript();

    @Override
    public void init(Properties properties) {
        super.init(properties);

        externalApiRequestProps.yandexRequestProps = new YandexRequestProps();

        externalApiRequestProps.yandexRequestProps.geocoderApiKey = properties.getProperty("external.api.yandex.geocoder_key");

        try {
            consoleScriptConf.getterJarPath = new String(properties.getProperty("consoleScript.getter.jarPath").getBytes("ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        consoleScriptConf.getterLibraryPath = properties.getProperty("consoleScript.getter.libraryPath");;
        consoleScriptConf.javaPath = properties.getProperty("consoleScript.getter.javaPath");;
    }

    public ExternalApiRequestProps getExternalApiRequestProps() {
        return externalApiRequestProps;
    }

    public ConsoleScript getConsoleScriptConf() {
        return consoleScriptConf;
    }

    public class ExternalApiRequestProps {
        public YandexRequestProps yandexRequestProps;

        public ExternalApiRequestProps() {
        }
    }

    public class YandexRequestProps {
        public String geocoderApiKey;

        public YandexRequestProps() {
        }
    }

    public static class ConsoleScript {

        public String getterJarPath;

        public String getterLibraryPath;

        public String javaPath;

    }
}
