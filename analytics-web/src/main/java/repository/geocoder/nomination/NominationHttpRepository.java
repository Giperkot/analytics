package repository.geocoder.nomination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonUtils;
import consts.ProjectConst;
import dto.geocode.CommonCoordsDto;
import dto.geocode.nomination.NominationCoordinatesDto;
import dto.geocode.yandex.CoordinatesDto;
import dto.geocode.yandex.FeatureMember;
import dto.geocode.yandex.GeoObjectCollection;
import dto.realty.BoundsDto;
import dto.realty.CoordPoint;
import dto.realty.HouseCoords;
import dto.realty.HouseDto;
import dto.realty.StreetDto;
import dto.realty.VillageDto;
import enums.realty.EStreetType;
import repository.geocoder.IGeocoder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NominationHttpRepository implements IGeocoder {

    private static final NominationHttpRepository instance = new NominationHttpRepository();

    public static NominationHttpRepository getInstance() {
        return instance;
    }

    // private final String apiKey;

    private ObjectMapper mapper = ProjectConst.MAPPER;

    private NominationHttpRepository() {
        // apiKey = AnalyticsContext.getConfig().getExternalApiRequestProps().nominationRequestProps.geocoderApiKey;

        client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    private final HttpClient client;

    @Override
    public List<CommonCoordsDto> getHouseByAddress(String cityName, HouseDto houseDto, String originalAddr) {
        try {

            String query = createQuery(cityName, houseDto);

            String url = "https://nominatim.openstreetmap.org/search.php?q=" +
                    URLEncoder.encode(query, StandardCharsets.UTF_8) + "&format=jsonv2" +
                    "&city=" + cityName +"&limit=50";

            HttpRequest.Builder builder = HttpRequest
                    .newBuilder(new URI(url))
                    .GET();

            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

            NominationCoordinatesDto[] nominationCoordinatesArr = mapper.readValue(response.body(), NominationCoordinatesDto[].class);


            List<CommonCoordsDto> result = new ArrayList<>();

            StreetDto streetDto = houseDto.getStreet();
            VillageDto villageDto = houseDto.getVillage();

            for (NominationCoordinatesDto nominationCoordinatesDto : nominationCoordinatesArr) {
                if (!"building".equals(nominationCoordinatesDto.getCategory())
                    && !"construction".equals(nominationCoordinatesDto.getCategory())) {
                    continue;
                }

                if (streetDto.getStreetType() != null && !streetDto.getStreetType().containsStreetType(nominationCoordinatesDto.getDisplayName())
                        && !nominationCoordinatesDto.getDisplayName().contains(streetDto.getName())
                    || !nominationCoordinatesDto.getDisplayName().contains(cityName)) {
                    continue;
                }

                HouseCoords houseCoords = new HouseCoords();
                houseCoords.setCenter(new CoordPoint(Double.parseDouble(nominationCoordinatesDto.getLon()),
                                                     Double.parseDouble(nominationCoordinatesDto.getLat())));

                String[] bounds = nominationCoordinatesDto.getBoundingbox();
                BoundsDto boundsDto = new BoundsDto();
                // ?????????????? ???????????? ?? ???????????? ?????????? ????????.
                boundsDto.setUpper(new CoordPoint(Double.parseDouble(bounds[3]), Double.parseDouble(bounds[1])));
                boundsDto.setLower(new CoordPoint(Double.parseDouble(bounds[2]), Double.parseDouble(bounds[0])));

                CommonCoordsDto commonCoordsDto = new CommonCoordsDto();

                houseCoords.setBound(boundsDto);
                commonCoordsDto.setHouseCoords(houseCoords);
                commonCoordsDto.setDisplayName(nominationCoordinatesDto.getDisplayName());

                result.add(commonCoordsDto);
            }

            return result;
        } catch (Exception ex) {
            throw new RuntimeException("???????????? ?????? ?????????????????????? ?????????????????? ????????.", ex);
        }
    }
}
