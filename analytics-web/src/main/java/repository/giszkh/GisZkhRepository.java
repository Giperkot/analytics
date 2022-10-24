package repository.giszkh;

import consts.ProjectConst;
import dto.realty.giszkh.HouseInfoRequestDto;
import dto.realty.giszkh.HouseInfoResponseDto;
import dto.realty.giszkh.HouseResponseDtoDto;
import dto.realty.giszkh.StreetResponseDtoDto;
import dto.realty.giszkh.finalres.HouseFinalInfoDto;
import exceptions.YandexBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GisZkhRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(GisZkhRepository.class);
    private static final GisZkhRepository instance = new GisZkhRepository();

    public static GisZkhRepository getInstance() {
        return instance;
    }

    private final HttpClient client;

    private GisZkhRepository() {
        client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public StreetResponseDtoDto[] getStreetInfo(String street) throws InterruptedException, URISyntaxException, IOException {

        String url = "https://dom.gosuslugi.ru/nsi/api/rest/services/nsi/fias/v4/streets?actual=true&cityCode=a309e4ce-2f36-4106-b1ca-53e0f48a6d95&itemsPerPage=10&page=1&regionCode=4f8b1a21-e4bb-422f-9087-d3cbf4bebc14&searchString="
                + URLEncoder.encode(street, StandardCharsets.UTF_8);

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return ProjectConst.MAPPER.readValue(response.body(), StreetResponseDtoDto[].class);
    }

    public HouseResponseDtoDto[] getHouseUUID(String streetUUID, String houseNum) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://dom.gosuslugi.ru/nsi/api/rest/services/nsi/fias/v4/numbers?actual=true&cityCode=a309e4ce-2f36-4106-b1ca-53e0f48a6d95&itemsPerPage=10&page=1"
                + "&regionCode=4f8b1a21-e4bb-422f-9087-d3cbf4bebc14&searchAggregatedAddresses=true&streetCode="
                + streetUUID + "&searchString=" + houseNum;

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return ProjectConst.MAPPER.readValue(response.body(), HouseResponseDtoDto[].class);
    }

    public HouseInfoResponseDto getFinalHouseInfo(String houseUuid) throws URISyntaxException, IOException, InterruptedException {

        HouseInfoRequestDto request = new HouseInfoRequestDto();
        request.setRegionCode("4f8b1a21-e4bb-422f-9087-d3cbf4bebc14");
        request.setCityCode("a309e4ce-2f36-4106-b1ca-53e0f48a6d95");
        request.setStreetCode("3bf64478-ab2f-404f-9e34-54e05cca44d8");
        request.setFiasHouseCodeList(List.of(houseUuid));
        request.setStatuses(List.of("APPROVED"));

        String url = "https://dom.gosuslugi.ru/homemanagement/api/rest/services/houses/public/searchByAddress?pageIndex=1&elementsPerPage=10";

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .headers("Session-GUID", "7b7b15d1-ef45-4ed9-92a3-4b069c0e1ee4",
                         "Request-GUID", "0558add6-9af3-4993-9bb7-d018a97fa959",
                         "Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(ProjectConst.MAPPER.writeValueAsString(request)));

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return ProjectConst.MAPPER.readValue(response.body(), HouseInfoResponseDto.class);
    }

    public HouseFinalInfoDto houseDetailsByUUID(String houseUuid) throws InterruptedException, URISyntaxException, IOException {
        String url = "https://dom.gosuslugi.ru/homemanagement/api/rest/services/houses/public/1/" + houseUuid;

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return ProjectConst.MAPPER.readValue(response.body(), HouseFinalInfoDto.class);
    }

}
