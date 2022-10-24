package core.config;

import core.AnalyticsContext;

import java.util.Properties;

public class AnalyticsConfig extends AppConfig {

    private ExternalApiRequestProps externalApiRequestProps;

    @Override
    public void init(Properties properties) {
        super.init(properties);

        externalApiRequestProps = new ExternalApiRequestProps();
        externalApiRequestProps.yandexRequestProps = new YandexRequestProps();

        externalApiRequestProps.yandexRequestProps.geocoderApiKey = properties.getProperty("external.api.yandex.geocoder_key");
    }

    public ExternalApiRequestProps getExternalApiRequestProps() {
        return externalApiRequestProps;
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
}
