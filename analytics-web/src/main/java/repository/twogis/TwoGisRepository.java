package repository.twogis;

import consts.ProjectConst;
import dto.twogis.KeyValue;
import dto.twogis.SearchHouseDto;
import exceptions.YandexBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.giszkh.GisZkhRepository;
import service.AbstractParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TwoGisRepository extends AbstractParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(GisZkhRepository.class);
    private static final TwoGisRepository instance = new TwoGisRepository();

    public static TwoGisRepository getInstance() {
        return instance;
    }

    protected final HttpClient legacyClient;

    private TwoGisRepository() {
        super();

        legacyClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public SearchHouseDto searchAddr(String address, String view1, String view2, Map<String, String> headerMap) throws URISyntaxException, IOException, InterruptedException {
        // https://catalog.api.2gis.ru/3.0/suggests?key=rurbbn3446&q=Екатерининская 134&viewpoint1=55.687814580014916,58.19162883445249&viewpoint2=56.771963419985084,57.856551165547515
        String url = "https://catalog.api.2gis.ru/3.0/suggests?key=rurbbn3446&q=" + URLEncoder.encode(address, StandardCharsets.UTF_8)
                    + "&viewpoint1=" + view1 + "&viewpoint2=" + view2;

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        for (String key : headerMap.keySet()) {
            builder.header(key, headerMap.get(key));
        }

        HttpResponse<byte[]> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return ProjectConst.MAPPER.readValue(getBody(response.body(), "br"), SearchHouseDto.class);
    }

    private long calcHash(String e, int w, int c) {
        long t = e.length();
        int s = w;
        long temp = s;
        for (int r = 0; r < t; r += 1) {
            s = (int)(temp * c + e.charAt(r));

            if (s < 0) {
                temp = Long.parseLong(Integer.toBinaryString(s), 2);
            } else {
                temp = s;
            }
        }
        return temp;
    }

    private String getQueryString(List<KeyValue> paramsList, int w, int c, String twoGisHash) {
        paramsList.sort((a, b) -> {
            return a.key.compareTo(b.key);
        });

        StringBuilder sb = new StringBuilder();
        StringBuilder querySb = new StringBuilder("?");

        for (int i = 0; i < paramsList.size(); i++) {
            KeyValue elm = paramsList.get(i);
            sb.append(elm.value);

            if (i > 0) {
                querySb.append("&");
            }

            querySb.append(elm.key + "=" + elm.value);
        }

        String str = "/3.0/items/byid" + sb.toString() + twoGisHash;

        long hash = calcHash(str, w, c);

        querySb.append("&r=" + hash);

        return querySb.toString();
    }

    protected HttpResponse<byte[]> doGetRedirect(String url, String originUrl, Map<String, String> headerMap, int tryCount) throws URISyntaxException, IOException, InterruptedException {
        if (tryCount > 5) {
            throw new RuntimeException("Превышено число попыток редиректов");
        }

        HttpClient client;

        if (url.contains("ohio9.vchecks.io")) {
            client = legacyClient;
        } else {
            client = this.client;
        }

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        for (String key : headerMap.keySet()) {
            builder.header(key, headerMap.get(key));
        }

        HttpResponse<byte[]> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() == 307) {
            String location = response.headers().firstValue("location").get();
            String redirectUrl = location;

            if (location.contains("ohio9.vchecks.io")) {
                // builder.header("Host", "ohio9.vchecks.io");
                // builder.header("Connection", "keep-alive");
            }
            if (location.startsWith("catalog.api.2gis.ru/")) {
                Thread.sleep(300);
                headerMap = getHeaders();
            }

            /*if (location.startsWith("https://")) {

            }*/

            return doGetRedirect(redirectUrl, originUrl, headerMap, tryCount + 1);
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("Ошибка при отправке запроса " + url);
        }

        return response;
    }

    public String getHouseInfoByIdUrl(List<KeyValue> paramsList) {
        String url = "https://catalog.api.2gis.ru/3.0/items/byid";

        int[] arr = {22, 4147, 1234, 11};
        int c = arr[0] + arr[3], w = arr[1] + arr[2];
        String twoGisHash = "baf4c54e9dae";

        String queryPart = getQueryString(paramsList, w, c, twoGisHash);
        url = url + queryPart;

        return url;
    }

//    public HouseResultDto getHouseInfoById(List<KeyValue> paramsList, Map<String, String> headerMap) throws URISyntaxException, IOException, InterruptedException {
//        String url = "https://catalog.api.2gis.ru/3.0/items/byid";
//
//        int[] arr = {22, 4147, 1234, 11};
//        int c = arr[0] + arr[3], w = arr[1] + arr[2];
//        String twoGisHash = "baf4c54e9dae";
//
//        String queryPart = getQueryString(paramsList, w, c, twoGisHash);
//        url = url + queryPart;
//
//        HttpRequest.Builder builder = HttpRequest
//                .newBuilder(new URI(url))
//                .GET();
//
//        for (String key : headerMap.keySet()) {
//            builder.header(key, headerMap.get(key));
//        }
//
//        HttpResponse<byte[]> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());
//
//        if (response.statusCode() == 307) {
//            Map<String, String> headerRedirectedMap = new HashMap<>();
//            // headerRedirectedMap.put("Host", "ohio9.vchecks.io");
//            // headerRedirectedMap.put("Connection", "keep-alive");
//            headerRedirectedMap.put("Cache-Control", "max-age=0");
//            headerRedirectedMap.put("sec-ch-ua", "\"Google Chrome\";v=\"105\", \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"105\"");
//            headerRedirectedMap.put("sec-ch-ua-mobile", "?0");
//            headerRedirectedMap.put("sec-ch-ua-platform", "Windows");
//            headerRedirectedMap.put("Upgrade-Insecure-Requests", "1");
//            headerRedirectedMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
//            headerRedirectedMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            headerRedirectedMap.put("Sec-Fetch-Site", "none");
//            headerRedirectedMap.put("Sec-Fetch-Mode", "navigate");
//            headerRedirectedMap.put("Sec-Fetch-User", "?1");
//            headerRedirectedMap.put("Sec-Fetch-Dest", "document");
//            headerRedirectedMap.put("Accept-Encoding", "gzip, deflate, br");
//            headerRedirectedMap.put("Sccept-Language", "ru-RU,ru;q=0.9");
//
//            response = doGetRedirect(response.headers().firstValue("location").get(), url, headerRedirectedMap, 1);
//        }
//
//        if (response.statusCode() != 200) {
//            throw new YandexBadRequestException();
//        }
//
//        String contentEncoding = response.headers().firstValue("content-encoding").get();
//
//        return ProjectConst.MAPPER.readValue(getBody(response.body(), contentEncoding), HouseResultDto.class);
//    }

    public String getMainPage(Map<String, String> headerMap) throws IOException, InterruptedException, URISyntaxException {
        String url = "https://2gis.ru/";

        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        for (String key : headerMap.keySet()) {
            builder.header(key, headerMap.get(key));
        }

        // HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        HttpResponse<byte[]> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new YandexBadRequestException();
        }

        return getBody(response.body(), "br");
    }

}
