package repository.geocoder;

import dto.geocode.CommonCoordsDto;
import dto.geocode.yandex.CoordinatesDto;

import java.util.List;

public interface IGeocoder {

    List<CommonCoordsDto> getHouseByAddress(String cityName, String street, String houseNum);

}
