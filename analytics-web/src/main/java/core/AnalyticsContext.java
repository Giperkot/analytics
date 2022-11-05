package core;

import core.config.AnalyticsConfig;
import core.config.AppConfig;
import service.AnalyticsStaticService;
import service.StaticService;
import service.realty.RealtyService;

import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AnalyticsContext extends Context{

    @Override
    public void customInit() {
        try (Connection connection = getConnection();) {
            RealtyService.getInstance().initializeAdjustCoeffDictMap(connection);
        }catch (Exception ex) {
            LOGGER.error("Ошибка инициализации корректоровочных коэффициэнтов", ex);
            throw new RuntimeException("Ошибка инициализации корректоровочных коэффициэнтов", ex);
        }

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
