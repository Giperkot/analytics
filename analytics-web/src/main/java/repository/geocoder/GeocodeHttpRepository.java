package repository.geocoder;

import dto.geocode.CommonCoordsDto;
import exceptions.YandexBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.geocoder.nomination.NominationHttpRepository;
import repository.geocoder.yandex.YandexHttpRepository;

import java.util.List;

public class GeocodeHttpRepository implements IGeocoder{

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeHttpRepository.class);
    private static final GeocodeHttpRepository instance = new GeocodeHttpRepository();

    public static GeocodeHttpRepository getInstance() {
        return instance;
    }

    private GeocodeHttpRepository() {
    }

    private final YandexHttpRepository yandexHttpRepository = YandexHttpRepository.getInstance();

    private final NominationHttpRepository nominationHttpRepository = NominationHttpRepository.getInstance();

    private boolean yandexAlive = true;

    @Override
    public List<CommonCoordsDto> getHouseByAddress(String cityName, String street, String houseNum) {

        List<CommonCoordsDto> result = nominationHttpRepository.getHouseByAddress(cityName, street, houseNum);

        if (!result.isEmpty()) {
            return result;
        }

        if (yandexAlive) {
            try {
                return yandexHttpRepository.getHouseByAddress(cityName, street, houseNum);
            } catch (YandexBadRequestException ex) {
                LOGGER.error("Геокодеру яндекса стало плохо. Истёк ключ.");
                yandexAlive = false;
            }
        }

        return result;
    }
}
