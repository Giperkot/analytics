package repository.geocoder;

import common.CommonUtils;
import dto.geocode.CommonCoordsDto;
import dto.geocode.yandex.CoordinatesDto;
import dto.realty.HouseDto;
import dto.realty.StreetDto;
import dto.realty.VillageDto;
import enums.realty.EStreetType;

import java.util.List;

public interface IGeocoder {

    List<CommonCoordsDto> getHouseByAddress(String cityName, HouseDto houseDto, String originalAddr);

    default String createQuery(String cityName, HouseDto houseDto) {
        String query = cityName;

        VillageDto village = houseDto.getVillage();
        if (houseDto.getVillage().exists()) {
            query += " " + village.getVillageType().toGeocodeStr(village.getName());
        }

        StreetDto street = houseDto.getStreet();
        if (street.exists()) {
            query += " " + street.getStreetType().toGeocodeStr(street.getName());
        }

        if (CommonUtils.isNotEmpty(houseDto.getHouseNum())) {
            query += " " + houseDto.getHouseNum();
        }

        return query;
    }
}
