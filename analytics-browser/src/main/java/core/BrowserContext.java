package core;

import java.util.Properties;

public class BrowserContext extends AbstractContext {

    private final boolean debug;

    public BrowserContext() {
        Properties properties = loadProperties ("main.properties", BrowserContext.class);

        debug = Boolean.valueOf(properties.getProperty("debug"));
    }

    public boolean isDebug() {
        return debug;
    }
}
