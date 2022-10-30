package repository.geocoder;

import dto.geocode.CommonCoordsDto;
import dto.geocode.yandex.CoordinatesDto;
import enums.realty.EStreetType;

import java.util.List;

public interface IGeocoder {

    List<CommonCoordsDto> getHouseByAddress(String cityName, String street, EStreetType streetType, String houseNum);

    default boolean containsStreetType(String address, EStreetType streetType) {
        for (String streetShortname : streetType.getTitleArr()) {
            if (address.contains(streetShortname)) {
                return true;
            }
        }

        return false;
    }
}
