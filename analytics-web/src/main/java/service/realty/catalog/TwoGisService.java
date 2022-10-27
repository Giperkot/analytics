package service.realty.catalog;

import consts.ProjectConst;
import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import dto.realty.BoundsDto;
import dto.realty.CoordPoint;
import dto.task.ConsoleResultWrapperDto;
import dto.twogis.*;
import dto.twogis.auth.AuthResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.twogis.TwoGisRepository;
import service.task.CefConsoleExecObserver;
import utils.DateUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoGisService extends AbstractCatalogService implements IRealtyCatalogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwoGisService.class);
    private static final TwoGisService instance = new TwoGisService();

    public static TwoGisService getInstance() {
        return instance;
    }

    private final TwoGisRepository twoGisRepository = TwoGisRepository.getInstance();

    private Map<String, String> headers;

    private AuthResult authResult;

    private TwoGisService() {
    }

    private AuthResult getAuthResult(String mainPageHtmlString) {
        AuthResult result = new AuthResult();

        int cfgIdx = mainPageHtmlString.indexOf("__customcfg");

        int sessionIdIdx = mainPageHtmlString.indexOf("sessionId", cfgIdx);
        int userIdIdx = mainPageHtmlString.indexOf("userId", cfgIdx);
        int commitIsoDateIdx = mainPageHtmlString.indexOf("commitIsoDate", cfgIdx);

        if (cfgIdx < 0 || sessionIdIdx < 0 || userIdIdx < 0 || commitIsoDateIdx < 0) {
            throw new RuntimeException("Не удалось авторизоваться в 2gis");
        }

        int sessionIdValQuoteIdx = mainPageHtmlString.indexOf(":", sessionIdIdx + "sessionId".length() + 1);
        int userIdValQuoteIdx = mainPageHtmlString.indexOf(":", userIdIdx + "userId".length() + 1);
        int commitIsoDateValQuoteIdx = mainPageHtmlString.indexOf(":", commitIsoDateIdx + "commitIsoDate".length() + 1);

        String sessionId = mainPageHtmlString.substring(sessionIdValQuoteIdx + 2, sessionIdValQuoteIdx + 2 + 36);
        String userId = mainPageHtmlString.substring(userIdValQuoteIdx + 2, userIdValQuoteIdx + 2 + 36);
        String commitIsoDateStr = mainPageHtmlString.substring(commitIsoDateValQuoteIdx + 2, commitIsoDateValQuoteIdx + 2 + 19);
        LocalDateTime commitIsoDate = LocalDateTime.parse(commitIsoDateStr, DateUtils.ISO_WITHOUT_Z);

        result.setSessionId(sessionId);
        result.setUserId(userId);
        result.setApiVers(commitIsoDate.format(DateUtils.JS_WITH_H));

        return result;
    }

    private List<KeyValue> createParamsList(String id, String view1, String view2, AuthResult authResult) {
        List<KeyValue> keyValues = new ArrayList<>();

        keyValues.add(new KeyValue("fields", "items.locale,items.flags,search_attributes,items.adm_div,items.city_alias,items.region_id,items.segment_id,items.reviews,items.point,request_type,context_rubrics,query_context,items.links,items.name_ex,items.org,items.group,items.dates,items.external_content,items.contact_groups,items.comment,items.ads.options,items.email_for_sending.allowed,items.stat,items.stop_factors,items.description,items.geometry.centroid,items.geometry.selection,items.geometry.style,items.timezone_offset,items.context,items.level_count,items.address,items.is_paid,items.access,items.access_comment,items.for_trucks,items.is_incentive,items.paving_type,items.capacity,items.schedule,items.floors,ad,items.rubrics,items.routes,items.platforms,items.directions,items.barrier,items.reply_rate,items.purpose,items.attribute_groups,items.route_logo,items.has_goods,items.has_apartments_info,items.has_pinned_goods,items.has_realty,items.has_exchange,items.has_payments,items.has_dynamic_congestion,items.is_promoted,items.congestion,items.delivery,items.order_with_cart,search_type,items.has_discount,items.metarubrics,broadcast,items.detailed_subtype,items.temporary_unavailable_atm_services,items.poi_category,items.structure_info.material,items.structure_info.floor_type,items.structure_info.gas_type,items.structure_info.year_of_construction,items.structure_info.elevators_count,items.structure_info.is_in_emergency_state,items.structure_info.project_type"));
        keyValues.add(new KeyValue("id", id));
        keyValues.add(new KeyValue("key", "rurbbn3446"));
        keyValues.add(new KeyValue("locale", "ru_RU"));
        keyValues.add(new KeyValue("viewpoint1", view1));
        keyValues.add(new KeyValue("viewpoint2", view2));
        keyValues.add(new KeyValue("stat[sid]", authResult.getSessionId()));
        keyValues.add(new KeyValue("stat[user]", authResult.getUserId()));
        keyValues.add(new KeyValue("shv", authResult.getApiVers()));

        return keyValues;
    }

    private CoordPoint getCoordsByCentroid(String centroid) {
        int squareOp = centroid.indexOf("(");
        int squareCl = centroid.indexOf(")");
        int space = centroid.indexOf(" ", squareOp);

        return new CoordPoint(Double.parseDouble(centroid.substring(space + 1, squareCl)), Double.parseDouble(centroid.substring(squareOp + 1, space)));
    }

    private ItemDto getExactlyHouse(List<ItemDto> houseList, HouseEntity houseEntity) {

        CoordPoint upperCoords = houseEntity.getCoords().getBound().getUpper();
        CoordPoint lowerCoords = houseEntity.getCoords().getBound().getLower();

        double lowerX = Math.min(upperCoords.getLongitude(), lowerCoords.getLongitude());
        double upperX = Math.max(upperCoords.getLongitude(), lowerCoords.getLongitude());
        double lowerY = Math.min(upperCoords.getLatitude(), lowerCoords.getLatitude());
        double upperY = Math.max(upperCoords.getLatitude(), lowerCoords.getLatitude());;

        for (int i = 0; i < houseList.size(); i++) {
            ItemDto item = houseList.get(i);

            String centroid = item.getGeometry().getCentroid();

            CoordPoint houseCoords = getCoordsByCentroid(centroid);

            if (lowerX <= houseCoords.getLongitude() && upperX >= houseCoords.getLongitude()
                    && upperY >= houseCoords.getLatitude() && lowerY <= houseCoords.getLatitude()) {
                return item;
            }
        }

        throw new RuntimeException("Координаты найденных домов не совпадают с нужными");
    }

    public Map<String, String> getHeaders() {
        Map<String, String> result = new HashMap<>();//super.getHeaders();
        result.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        result.put("accept-encoding", "gzip, deflate, br");
        result.put("accept-language", "ru-RU,ru;q=0.9");
        result.put("sec-ch-ua", "\"Google Chrome\";v=\"105\", \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"105\"");
        result.put("sec-ch-ua-mobile", "?0");
        result.put("sec-ch-ua-platform", "Windows");
        result.put("sec-fetch-dest", "document");
        result.put("sec-fetch-mode", "navigate");
        result.put("sec-fetch-site", "none");
        result.put("sec-fetch-user", "?1");
        result.put("upgrade-insecure-requests", "1");
        result.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
        result.put("cache-control", "max-age=0");

        return result;
    }

    @Override
    public void init() throws InterruptedException, IOException, URISyntaxException {
        headers = getHeaders();
        // Сначала авторизоваться в 2gis
        String mainPageHtmlString = twoGisRepository.getMainPage(headers);

        authResult = getAuthResult(mainPageHtmlString);
    }

    public HouseAddInfoEntity fillHouseYearInfo(HouseEntity houseEntity, Connection connection) throws InterruptedException, IOException, URISyntaxException {
        BoundsDto bounds = houseEntity.getCoords().getBound();
        CoordPoint lowerPoint = bounds.getLower();
        CoordPoint upperPoint = bounds.getUpper();
        String view1 = "55.687814580014916,58.19162883445249";//upperPoint.getLongitude() + "," + upperPoint.getLatitude();
        String view2 = "56.771963419985084,57.856551165547515";//lowerPoint.getLongitude() + "," + lowerPoint.getLatitude();

        SearchHouseDto searchHouseDto = twoGisRepository.searchAddr(houseEntity.getStreet() + " " + houseEntity.getHouseNum(),
                view1, view2, headers);

        if (searchHouseDto.getResult() == null) {
            throw new RuntimeException("Отсутствует дом в БД 2гис " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
        }

        List<ItemDto__3> items = searchHouseDto.getResult().getItems();

        if (items.isEmpty()) {
            throw new RuntimeException("Отсутствует дом в БД 2гис " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
        }

        //
        ItemDto__3 item = items.get(0);

        String houseId = item.getId();

        List<KeyValue> paramsList = createParamsList(houseId, view1, view2, authResult);

        String houseUrl = twoGisRepository.getHouseInfoByIdUrl(paramsList);

        CefConsoleExecObserver cefConsoleExecObserver = new CefConsoleExecObserver();

        ConsoleResultWrapperDto consoleResultWrapperDto = cefConsoleExecObserver.getHouseInfo(houseUrl);

        if (!consoleResultWrapperDto.isSuccess()) {
            throw new RuntimeException("Ошибка при получении информации о доме");
        }

        String resStr = consoleResultWrapperDto.getResultObjectStr();

        HouseResultDto houseResultDto = ProjectConst.MAPPER.readValue(resStr, HouseResultDto.class);

//        return null;

        if (houseResultDto.getMeta().getCode() == 403) {
            throw new RuntimeException("Авторизация провалилась");
        }

        List<ItemDto> itemList = houseResultDto.getResult().getItems();

        ItemDto houseItem = getExactlyHouse(itemList, houseEntity);

        int floorsCount = houseItem.getFloors().getGroundCount();
        String wallMaterial = houseItem.getStructureInfo().getMaterial();
        int year = (houseItem.getStructureInfo().getYearOfConstruction() != null) ? houseItem.getStructureInfo().getYearOfConstruction() : -1;

        HouseAddInfoEntity houseAddInfoEntity = createHouseAddinfoEntity(houseEntity.getId(), year, -1,
                wallMaterial, floorsCount);

        return houseAddInfoEntity;
    }


}
