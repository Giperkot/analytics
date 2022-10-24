package core;

import core.config.AnalyticsConfig;
import core.config.AppConfig;
import service.AnalyticsStaticService;
import service.StaticService;

import javax.servlet.annotation.WebListener;

@WebListener
public class AnalyticsContext extends Context{

    @Override
    public void customInit() {

    }

    @Override
    protected AppConfig createConfig() {
        return new AnalyticsConfig();
    }

    @Override
    protected StaticService createStaticService() {
        return AnalyticsStaticService.getInstance();
    }

    @Override
    public void customDestroy() {

    }

    public static AnalyticsConfig getConfig() {
        return (AnalyticsConfig)config;
    }
}
