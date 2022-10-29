package service.avito.service;

import common.CommonUtils;
import consts.ProjectConst;
import dao.parser.ParserDao;
import db.entity.parser.NoticeEntity;
import db.entity.parser.ParseTaskEntity;
import dto.parser.FeatureDto;
import dto.parser.KeyValue;
import dto.parser.NoticeEntityWrapper;
import enums.ENoticeStatus;
import enums.EParserStatus;
import enums.EParserType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AbstractParser;
import utils.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvitoParser extends AbstractParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvitoParser.class);
    private static final AvitoParser instance = new AvitoParser();

    private static final String PREFIX = "https://www.avito.ru";

    public static AvitoParser getInstance() {
        return instance;
    }

    private AvitoParser() {

    }

    private final ParserDao parserDao = ParserDao.getInstance();

    private Element getTitleLink(Element notice) {
        Elements links = notice.select("a");

        for (int i = 0; i < links.size(); i++) {
            Element link = links.get(i);
            if (link.className().contains("title")) {
                return link;
            }
        }

        throw new RuntimeException("there's no links");
    }

    public int findLastPage(Document noticesContent) {
        Elements paginElms = noticesContent.select("div[data-marker=pagination-button] span[data-marker]");

        if (paginElms.size() < 1) {
            throw new RuntimeException("Отсутствует количество сртаниц");
        }

        return Integer.parseInt(paginElms.get(paginElms.size() - 2).html());
    }

    public void addCookie(Map<String, String> headerMap, String cookies) {
        headerMap.put("cookie", cookies);
    }

    public Map<String, String> initHeaders() throws InterruptedException, IOException, URISyntaxException {
        Map<String, String> headerMap = getHeaders();

        // Открыть гл. страницу (получить сессию)
        String cookie = initRequest(headerMap);

        // Добавить куки.
        addCookie(headerMap, cookie);
        // addCookie(headerMap, "yandexuid=4382227851627802345; yuidss=4382227851627802345; is_gdpr=0; is_gdpr_b=CNuvQhCmPSgC; gdpr=0; _ym_uid=1627844404129509839; yandex_login=o.odohar; yandex_gid=50; i=Y7nYKU1rNSLmplpxwhHpeEcvMquVr9pv5hffpLisBPUsfKjB8WNdAuELxrQpqJE1HyOvViahYNxbg4cGTcMdKqV4q4o=; ymex=1974711006.yrts.1659351006; Session_id=3:1660778919.5.0.1627844465171:YRIPsA:1e.1.2:1|225957253.0.2|1130000048808559.6107952.2.2:6107952|1130000018123047.6108011.2.2:6108011|1130000058054262.26253730.2.2:26253730|3:10256913.353076.dA7eOaGQzZEsaCVWiaMm-UIqbHU; sessionid2=3:1660778919.5.0.1627844465171:YRIPsA:1e.1.2:1.499:1|225957253.0.2|1130000048808559.6107952.2.2:6107952|1130000018123047.6108011.2.2:6108011|1130000058054262.26253730.2.2:26253730|3:10256913.931704.Sv8cMh8GDsQ-2QOe-shymMyL-74; Nickel=1; bltsr=1; computer=1; yabs-sid=2654320681660838156; _ym_d=1660886483; _ym_isad=1; yabs-frequency=/5/0m000AOV5M400000/1LmOhY66wcCKI25lfLmS6hB2Qn189sRnK6c8IUD144WYaMHaEERPMb0BIFp___-m8OCwW-74HnH8W6b_gdMKKKv054WWlaSEXY_52M4KIE37UsuddFRYI1H8OAosKvSA6fL654XWrmPM5tbXVLKKI40oszepqxP8LHH8G1tEUwKHQlDz5KWW0TbmYRSKiv9D5KZW12j_8LShdnDw54XW4sifM5S0000KI62LutJOI_zvJ1H88DK-6et8z8Hj54WWW94KqVuuxs8KI206CGbPxqMqTnH8uD5ki72L000054Z0epLaDMHHst8KI82NJpAM9XekIXH8e000/");

        return headerMap;
    }

    public String initRequest(Map<String, String> headerMap) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(PREFIX))
                .GET();

        for (String key : headerMap.keySet()) {
            builder.header(key, headerMap.get(key));
        }

        HttpResponse<byte[]> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

        HttpHeaders headers = response.headers();

        List<String> cookieList = headers.allValues("set-cookie");

        StringBuilder cookieSb = new StringBuilder();

        for (int i = 0; i < cookieList.size(); i++) {
            cookieSb.append(cookieList.get(i));

            if (i < cookieList.size() - 1) {
                cookieSb.append("; ");
            }
        }

        return cookieSb.toString();
    }

    public Document getContentByUrl(String url, Map<String, String> headerMap) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest
                .newBuilder(new URI(url))
                .GET();

        for (String key : headerMap.keySet()) {
            builder.header(key, headerMap.get(key));
        }

        HttpResponse<byte[]> response =
                client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

        return Jsoup.parse(getBody(response.body()));
    }

    public List<ParseTaskEntity> parsePageList(Document doc) {

        List<ParseTaskEntity> result = new ArrayList<>();
        // Объявления
        Elements noticeElms = doc.select("div[data-marker=item]");

        for (int i = 0; i < noticeElms.size(); i++) {
            // Нужно достать ссылку.
            Element notice = noticeElms.get(i);

            Element link = getTitleLink(notice);

            String title = StringUtils.trimWhitespace(link.text());
            String href = link.attr("href");
            String externalId = notice.attr("data-item-id");

            ParseTaskEntity parseTaskEntity = new ParseTaskEntity();
            parseTaskEntity.setStatus(EParserStatus.NOT_PARSED);
            parseTaskEntity.setTitle(title);
            parseTaskEntity.setType(EParserType.AVITO);
            parseTaskEntity.setUrl(href);
            parseTaskEntity.setExternalId(externalId);

            result.add(parseTaskEntity);
        }

        return result;
    }

    private Elements getHouseParams(Document detailPage) {
        Elements liList2 = new Elements();
        Elements liList = detailPage.select("li");
        for (int i = 0; i < liList.size(); i++) {
            if (liList.get(i).className().startsWith("style-item-params-list-item")) {
                liList2.add(liList.get(i));
            }
        }

        return liList2;
    }

    private List<KeyValue> getMetroParams(Element metroCandidat) {
        // Elements elms = metroCandidat.getElementsByAttributeValueStarting("class", "style-item-address-georeferences-item");
        List<Element> elms = new ArrayList<>();
        Elements childrenRoot = metroCandidat.children();
        for (int i = 0; i < childrenRoot.size(); i++) {
            if (childrenRoot.get(i).className().startsWith("style-item-address-georeferences-item")) {
                elms.add(childrenRoot.get(i));
            }
        }

        List<KeyValue> keyValueList = new ArrayList<>();

        for (int i = 0; i < elms.size(); i++) {
            Element elm = elms.get(i);

            String station = "";
            String nearTime = "";

            Element metroElm = elm.select("span").get(0);
            for (int j = 0; j < metroElm.children().size(); j++) {
                if (!metroElm.children().get(j).hasAttr("class")) {
                    station = metroElm.children().get(j).text();
                    break;
                }
            }

            Elements children = elm.children();
            for (int j = 0; j < children.size(); j++) {
                if (children.get(j).className().startsWith("style-item-address-georeferences-item-interval")) {
                    nearTime = children.get(j).text();
                    break;
                }
            }

            KeyValue metroInfo = new KeyValue(station, nearTime);

            keyValueList.add(metroInfo);
        }

        return keyValueList;
    }

    private NoticeEntityWrapper createDeletedBy301Notice(ParseTaskEntity parseTaskEntity, String location) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setTitle(parseTaskEntity.getTitle());
        noticeEntity.setParseTaskId(parseTaskEntity.getId());
        noticeEntity.setStatus(ENoticeStatus.DELETED_301);
        noticeEntity.setSum(0D);

        return new NoticeEntityWrapper(noticeEntity, new ArrayList<>(), location);
    }

    private NoticeEntityWrapper createRemovedFromPublicationNotice(ParseTaskEntity parseTaskEntity,
                                                                   Elements sumElms,
                                                                   ENoticeStatus status) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setTitle(parseTaskEntity.getTitle());
        noticeEntity.setParseTaskId(parseTaskEntity.getId());
        noticeEntity.setStatus(status);

        if (CommonUtils.isNotEmpty(sumElms)) {
            noticeEntity.setSum(Double.valueOf(StringUtils.getOnlyNumbers(sumElms.get(0).text())));
        } else {
            noticeEntity.setSum(0D);
        }

        return new NoticeEntityWrapper(noticeEntity, new ArrayList<>());
    }

    private NoticeEntityWrapper createRemovedFromPublicationNotice(ParseTaskEntity parseTaskEntity,
                                                                   Elements sumElms) {
        return createRemovedFromPublicationNotice(parseTaskEntity, sumElms, ENoticeStatus.REMOVED_FROM_PUBLICATION);
    }

    public NoticeEntityWrapper parseDetailNotice(Map<String, String> headerMap, ParseTaskEntity parseTaskEntity) {
        return parseDetailNotice(headerMap, parseTaskEntity, 0);
    }

    public NoticeEntityWrapper parseDetailNotice(Map<String, String> headerMap, ParseTaskEntity parseTaskEntity,
                                                 int iterations) {
        try {
            String url = PREFIX + parseTaskEntity.getUrl();

            HttpRequest.Builder builder = HttpRequest
                    .newBuilder(new URI(url))
                    .GET();

            for (String key : headerMap.keySet()) {
                builder.header(key, headerMap.get(key));
            }

            HttpResponse<byte[]> response =
                    client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() == 301) {
                List<String> locationList = response.headers().allValues("location");
                String location = null;
                if (CommonUtils.isNotEmpty(locationList)) {
                    location = locationList.get(0);
                }

                // Похоже, объявление закрыто. Обновим статус.
                return createDeletedBy301Notice(parseTaskEntity, location);
            }

            if (response.statusCode() == 404) {
                // Похоже, объявление закрыто. Обновим статус.
                return createRemovedFromPublicationNotice(parseTaskEntity, null);
            }

            if (response.statusCode() != 200) {
                LOGGER.error(response.statusCode() + " Ошибка при запросе к parsedEntity id: " + parseTaskEntity.getId() + ". url: " + parseTaskEntity.getUrl());
                throw new RuntimeException(response.statusCode() + "Ошибка при запросе к parsedEntity id: " + parseTaskEntity.getId() + ". url: " + parseTaskEntity.getUrl());
            }

            Document detailPage = Jsoup.parse(getBody(response.body()));

            Elements removedFromPublication = detailPage.select("a[data-marker=item-view/closed-warning]");

            Elements sumElms = detailPage.select("div[data-marker=item-view/item-view-contacts] span[itemprop=price]");
            Elements noticeParams = detailPage.select("div[data-marker=item-view/item-params] ul li");
            Elements houseParams = getHouseParams(detailPage);

            // Проверим, может объявление снято с публикации...
            if (!removedFromPublication.isEmpty() && removedFromPublication.text().contains("Объявление снято с публикации")) {
                return createRemovedFromPublicationNotice(parseTaskEntity, sumElms);
            }

            if (!houseParams.isEmpty()) {
                noticeParams.addAll(houseParams);
            }

            List<FeatureDto> featureDtoList = new ArrayList<>();
            for (int i = 0; i < noticeParams.size(); i++) {
                Element noticeParamElm = noticeParams.get(i);

            /*String paramName = noticeParam.select("span").text();
            int j = 0;*/

                String noticeParam = noticeParamElm.text();
                String[] paramsParts = noticeParam.split(":");

                FeatureDto featureDto = new FeatureDto();
                featureDto.setName(StringUtils.trimWhitespace(paramsParts[0]));
                featureDto.setValue(StringUtils.trimWhitespace(paramsParts[1]));

                featureDtoList.add(featureDto);
            }

            Elements addressElms = detailPage.select("div[itemprop=address] span");
            Elements afterAddressElms = detailPage.select("div[itemprop=address] div span");
            Elements descroptionElms = detailPage.select("div[itemprop=description");

            if (addressElms.size() < 1 || sumElms.size() < 1) {
                Elements rubricPageTitle = detailPage.select("h1[data-marker=page-title/text]");

                if (!rubricPageTitle.isEmpty()) {
                    // Нас перебросило на страницу со списком объявлений
                    // Видимо, снято с публикации.
                    return createRemovedFromPublicationNotice(parseTaskEntity, null, ENoticeStatus.DELETED);
                }

                // Попробуем сделать ещё запрос через неск секунд
                if (iterations < 3) {
                    LOGGER.warn("Вернулась какая-то жесть. Попробуем отправить запрос снова. parseTaskId: "
                                        + parseTaskEntity.getId());
                    Thread.sleep(6000);

                    return parseDetailNotice(headerMap, parseTaskEntity, iterations + 1);
                } else {
                    throw new RuntimeException("Страница имеет некорректное содержание, parsedEntity id: " + parseTaskEntity.getId() + ". url: " + parseTaskEntity.getUrl());
                }

            }

            if (!afterAddressElms.isEmpty()) {
                List<KeyValue> metroInfoList = getMetroParams(afterAddressElms.get(0));
                featureDtoList.add(new FeatureDto("Метро", ProjectConst.MAPPER.writeValueAsString(metroInfoList)));
            }

            NoticeEntity noticeEntity = new NoticeEntity();
            noticeEntity.setTitle(parseTaskEntity.getTitle());
            noticeEntity.setParseTaskId(parseTaskEntity.getId());
            noticeEntity.setAddress(StringUtils.trimWhitespace(addressElms.get(0).text()));
            noticeEntity.setDescription(StringUtils.trimWhitespace(descroptionElms.get(0).text()));
            noticeEntity.setParseTaskId(parseTaskEntity.getId());
            noticeEntity.setSum(Double.valueOf(StringUtils.getOnlyNumbers(sumElms.get(0).text())));
            noticeEntity.setStatus(ENoticeStatus.ACTIVE);

            return new NoticeEntityWrapper(noticeEntity, featureDtoList);
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка при парсинге объявления: ", ex);
        }
    }


}
