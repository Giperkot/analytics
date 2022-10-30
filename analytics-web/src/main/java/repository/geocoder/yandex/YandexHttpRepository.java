package repository.geocoder.yandex;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonUtils;
import converter.realty.RealtyConverter;
import core.AnalyticsContext;
import dto.geocode.CommonCoordsDto;
import dto.geocode.yandex.CoordinatesDto;
import dto.geocode.yandex.FeatureMember;
import dto.realty.HouseCoords;
import enums.realty.EStreetType;
import exceptions.YandexBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.geocoder.IGeocoder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class YandexHttpRepository implements IGeocoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(YandexHttpRepository.class);
    private static final YandexHttpRepository instance = new YandexHttpRepository();

    public static YandexHttpRepository getInstance() {
        return instance;
    }

    private final String apiKey;

    private XmlMapper mapper = new XmlMapper();

    private final RealtyConverter realtyConverter = RealtyConverter.getInstance();

    private YandexHttpRepository() {
        apiKey = AnalyticsContext.getConfig().getExternalApiRequestProps().yandexRequestProps.geocoderApiKey;

        client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    private final HttpClient client;

    public List<CommonCoordsDto> getHouseByAddress(String cityName, String street, EStreetType streetType, String houseNum) {
        try {
            String url = "https://geocode-maps.yandex.ru/1.x/?geocode=" +
                    URLEncoder.encode(cityName + " " + street + " " + houseNum, "UTF-8") + "&apikey=" + apiKey;

            HttpRequest.Builder builder = HttpRequest
                    .newBuilder(new URI(url))
                    .GET();

            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new YandexBadRequestException();
            }

            CoordinatesDto coordinatesDto = mapper.readValue(response.body(), CoordinatesDto.class);

            List<FeatureMember> featureMemberList = coordinatesDto.getGeoObjectCollection().getFeatureMemberList();
            List<CommonCoordsDto> result = new ArrayList<>();

            for (FeatureMember featureMember : featureMemberList) {
                HouseCoords houseCoords = realtyConverter.toHouseCoords(featureMember.getGeoObject());

                String displayName = featureMember.getGeoObject().getDescription() +
                        " " + featureMember.getGeoObject().getName();

                if (!containsStreetType(displayName, streetType)) {
                    continue;
                }

                CommonCoordsDto commonCoordsDto = new CommonCoordsDto();
                commonCoordsDto.setHouseCoords(houseCoords);
                commonCoordsDto.setDisplayName(displayName);

                result.add(commonCoordsDto);
            }

            return result;
        } catch (YandexBadRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка при определении координат дома.", ex);
        }
    }
}
