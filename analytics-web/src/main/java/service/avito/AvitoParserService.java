package service.avito;

import common.CommonUtils;
import common.DiffUtils;
import converter.realty.ParserMapper;
import converter.realty.ParserMapperImpl;
import core.rest.RequestHelper;
import dao.parser.ParserDao;
import db.entity.BaseEntity;
import db.entity.DiffAction;
import db.entity.parser.DirectionEntity;
import db.entity.parser.DirectionUrlEntity;
import db.entity.parser.FeatureEntity;
import db.entity.parser.NoticeEntity;
import db.entity.parser.ParseTaskEntity;
import db.entity.parser.action.NoticeActionEntity;
import db.entity.parser.action.NoticeFeatureActionEntity;
import db.entity.parser.view.VFeatureValueEntity;
import db.entity.parser.view.VParserNoticeEntity;
import dto.parser.FeatureComplexValue;
import dto.parser.FeatureDto;
import dto.parser.FeatureToSaveDto;
import dto.parser.NoticeEntityWrapper;
import enums.EDirectionName;
import enums.EFeatureExactName;
import enums.ENoticeStatus;
import enums.EParserStatus;
import enums.EParserType;
import enums.EUnit;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.avito.service.AvitoParser;
import utils.LockUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvitoParserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvitoParserService.class);
    private static final AvitoParserService instance = new AvitoParserService();

    public static AvitoParserService getInstance() {
        return instance;
    }

    private final ParserDao parserDao = ParserDao.getInstance();

    private final AvitoParser avitoParser = AvitoParser.getInstance();

    private final LockUtil parseInProcess = new LockUtil();

    private final ParserMapper parserMapper = new ParserMapperImpl();

    private String nextUrl(String baseUrl, int pageNumber) {
        if (pageNumber <= 1) {
            return baseUrl;
        }

        if (baseUrl.contains("?")) {
            return baseUrl + "&p=" + pageNumber;
        }

        return baseUrl + "?p=" + pageNumber;
    }

    // todo Распихать по направлениям...
    public String parseNoticeList(RequestHelper requestHelper) throws IOException, URISyntaxException, InterruptedException, SQLException {

        synchronized (parseInProcess) {
            if (parseInProcess.isLocked()) {
                // Парсинг уже запущен.
                return "busy";
            }
            parseInProcess.setLocked(true);
        }

        try {
            Connection connection = requestHelper.getConnection();

            List<DirectionUrlEntity> directionUrlEntityList = parserDao.getUrlsByDirection(connection, EDirectionName.REALTY_SALE, "Москва");

            DirectionUrlEntity directionUrlEntity = directionUrlEntityList.get(0);
            String url = directionUrlEntity.getUrl();

            Map<String, String> headerMap = avitoParser.initHeaders();

            Document noticesContent = avitoParser.getContentByUrl(url, headerMap);

            // Получить количество страниц.
            int lastPage = avitoParser.findLastPage(noticesContent);

            parseAndSave(connection, noticesContent, directionUrlEntity.getId());

            for (int i = 2; i < lastPage + 1; i++) {
                String nextUrl = this.nextUrl(url, i);

                Document noticesContentPage = avitoParser.getContentByUrl(nextUrl, headerMap);
                parseAndSave(connection, noticesContentPage, directionUrlEntity.getId());

                Thread.sleep(5000);

                connection.commit();
            }
        } finally {
            synchronized (parseInProcess) {
                parseInProcess.setLocked(false);
            }
        }

        return "success";
    }

    private void parseAndSave(Connection connection, Document noticesContent, long directionUrlId) {
        List<ParseTaskEntity> tasks = avitoParser.parsePageList(noticesContent);

        if (CommonUtils.isNullOrEmpty(tasks)) {
            return;
        }

        String[] taskExternalArr = tasks.stream().map(ParseTaskEntity::getExternalId).toArray(String[]::new);
        // Проверим, возможно какие-то объявления были переоткрыты
        List<BaseEntity> closedNoticeEntityList = parserDao.findClosedNoticesByNewTasks(connection, taskExternalArr);

        if (CommonUtils.isNotEmpty(closedNoticeEntityList)) {
            Long[] noticesIds = closedNoticeEntityList.stream().map(BaseEntity::getId).toArray(Long[]::new);

            parserDao.returnNoticesToActive(connection, noticesIds);
        }

        parserDao.saveAllParserEntity(connection, tasks, directionUrlId);
    }

    // 12.6 м²
    private FeatureComplexValue createAreaFeature(String name, String value) {
        int spaceIdx = value.indexOf(" ");

        if (spaceIdx == -1) {
            LOGGER.error("Нестандартный формат площади: " + value);
            return new FeatureComplexValue(name, value);
        }

        String square = value.substring(0, spaceIdx);
        String units = value.substring(spaceIdx + 1);

        if (Double.parseDouble(square) <= 0) {
            throw new RuntimeException("Площадь меньше 0: " + square);
        }

        EUnit unit = EUnit.getUnit(units);
        return new FeatureComplexValue(name, square, unit);
    }

    private FeatureComplexValue createBooleanBalconyFeature(String name, String value) {
        String res = "false";
        value = value.toLowerCase();

        if (value.contains("балкон") || value.contains("лоджия")) {
            res = "true";
        }

        return new FeatureComplexValue(name, res);
    }

    // 8 из 19
    private FeatureComplexValue createHouseFloorFeature(String name, String value) {
        value = value.trim();
        int lastSpace = value.lastIndexOf(" ");

        if (lastSpace == -1) {
            LOGGER.error("Нестандартный формат этажа: " + value);
            return new FeatureComplexValue(name, value);
        }

        String totalFloors = value.substring(lastSpace + 1);

        if (Integer.parseInt(totalFloors) < 0) {
            throw new RuntimeException("Количество этажей меньше 0: " + totalFloors);
        }

        return new FeatureComplexValue(name, totalFloors);
    }

    // 8 из 19
    private FeatureComplexValue createFloorFeature(String name, String value) {
        value = value.trim();
        int firstSpace = value.indexOf(" ");

        if (firstSpace == -1) {
            LOGGER.error("Нестандартный формат этажа: " + value);
            return new FeatureComplexValue(name, value);
        }

        String totalFloors = value.substring(0, firstSpace);

        if (Integer.parseInt(totalFloors) < 0) {
            throw new RuntimeException("Количество этажей меньше 0: " + totalFloors);
        }

        return new FeatureComplexValue(name, totalFloors);
    }

    private FeatureComplexValue createFeatureComplexValue(String value, FeatureEntity featureEntity) {

        EFeatureExactName exactName = featureEntity.getExactName();

        if (exactName == null) {
            return new FeatureComplexValue(featureEntity.getName(), value);
        }

        switch (exactName) {
            case KITCHEN_AREA:
            case LIVING_AREA:
            case TOTAL_AREA:
                return createAreaFeature(featureEntity.getName(), value);
            case BALCON_LOGGIA:
                return createBooleanBalconyFeature(featureEntity.getName(), value);
            case FLOOR:
                return createFloorFeature(featureEntity.getName(), value);
        }

        return new FeatureComplexValue(featureEntity.getName(), value);
    }

    private FeatureDto findFeatureByName(String lowerName, List<FeatureDto> featureDtoList) {
        for (int i = 0; i < featureDtoList.size(); i++) {
            FeatureDto featureDto = featureDtoList.get(i);

            if (lowerName.equals(featureDto.getName().toLowerCase())) {
                return featureDto;
            }
        }

        return null;
    }

    private FeatureToSaveDto getFeatureInfo(Connection connection, NoticeEntityWrapper noticeEntityWrapper) {
        // Получить features из БД. Возможно есть совпадающие.
        Map<String, FeatureEntity> featureMapInDB = parserDao.getFeaturesByNames(connection,
                                                                                 noticeEntityWrapper.getFeatureDtoList());
        List<FeatureDto> featureForCreatingList = new ArrayList<>();

        Map<String, FeatureComplexValue> featureValueMap = new HashMap<>();

        for (FeatureDto featureDto : noticeEntityWrapper.getFeatureDtoList()) {

            FeatureEntity featureEntity = featureMapInDB.get(featureDto.getName());
            FeatureComplexValue featureComplexValue;

            if (featureEntity == null) {
                featureForCreatingList.add(featureDto);
                featureComplexValue = new FeatureComplexValue(featureDto.getName(), featureDto.getValue());
            } else {
                // Здесь нужно выровнять единицы измерения...
                featureComplexValue = createFeatureComplexValue(featureDto.getValue(), featureEntity);

                if (featureEntity.getExactName() == EFeatureExactName.FLOOR) {
                    FeatureDto houseFloorFeature = findFeatureByName("этажей в доме", noticeEntityWrapper.getFeatureDtoList());

                    if (houseFloorFeature == null) {
                        // Если вдруг "Этажей в доме" нет в объявлении
                        FeatureEntity houseFloorFeatureEntity = parserDao.getFeatureByExactName(connection, EFeatureExactName.HOUSE_FLOORS);
                        featureMapInDB.put(houseFloorFeatureEntity.getName(), houseFloorFeatureEntity);
                        FeatureComplexValue secondFeatureComplexValue = createHouseFloorFeature(featureDto.getName(), featureDto.getValue());

                        featureValueMap.put(houseFloorFeatureEntity.getName(), secondFeatureComplexValue);
                    }
                }
            }

            // Нужна мапа feature.name -> value. чтобы можно было заполнить значение в БД.
            featureValueMap.put(featureDto.getName(), featureComplexValue);
        }

        FeatureToSaveDto result = new FeatureToSaveDto();

        result.setFeatureMapInDB(featureMapInDB);
        result.setFeatureValueMap(featureValueMap);
        result.setFeatureForCreatingList(featureForCreatingList);

        return result;
    }

    private void parseNotice(Connection connection, Map<String, String> headerMap,
                             ParseTaskEntity parseTaskEntity, DirectionEntity realtySaleDirection,
                             int level) throws SQLException, InterruptedException {
        NoticeEntityWrapper noticeEntityWrapper = avitoParser.parseDetailNotice(headerMap, parseTaskEntity);

        if (noticeEntityWrapper.getNoticeEntity().getStatus() == ENoticeStatus.DELETED_301) {
            // Нет смысла сохранять.
            // Сменить статус задачи на парсинг.
            parseTaskEntity.setUrl(noticeEntityWrapper.getRedirectUrl());
            if (level < 3) {
                Thread.sleep(5000);
                parseNotice(connection, headerMap, parseTaskEntity, realtySaleDirection, level + 1);
            } else {
                parserDao.setParserTaskStatus(connection, parseTaskEntity.getId(), EParserStatus.COMPLETED);
                connection.commit();
                return;
            }
        }

        if (noticeEntityWrapper.getNoticeEntity().getStatus() == ENoticeStatus.DELETED) {
            parserDao.setParserTaskStatus(connection, parseTaskEntity.getId(), EParserStatus.COMPLETED);
            connection.commit();
            return;
        }

        FeatureToSaveDto featureToSaveDto = getFeatureInfo(connection, noticeEntityWrapper);

        // Сохранить детальную информацию в БД.
        parserDao.saveNotice(connection, noticeEntityWrapper.getNoticeEntity(), realtySaleDirection,
                             featureToSaveDto.getFeatureForCreatingList(), featureToSaveDto.getFeatureMapInDB(),
                             featureToSaveDto.getFeatureValueMap());

        // Сменить статус задачи на парсинг.
        parserDao.setParserTaskStatus(connection, parseTaskEntity.getId(), EParserStatus.COMPLETED);

        List<DiffAction> diffActionList = DiffUtils.getDiffList(null, noticeEntityWrapper.getNoticeEntity());

        List<NoticeActionEntity> noticeActionEntityList = new ArrayList<>();
        for (DiffAction diffAction : diffActionList) {
            noticeActionEntityList.add(new NoticeActionEntity(diffAction, noticeEntityWrapper.getNoticeEntity().getId()));
        }

        parserDao.updateNoticeHistory(connection, noticeActionEntityList);
    }

    public void parseDetails(RequestHelper requestHelper) {
        synchronized (parseInProcess) {
            if (parseInProcess.isLocked()) {
                // Парсинг уже запущен.
                return;
            }
            parseInProcess.setLocked(true);
        }

        Connection connection = requestHelper.getConnection();
        // Запросить первые 50 объявлений и перевести их в PROCESSING
        List<ParseTaskEntity> notParsedList = parserDao.getNotParcedEntity(connection, 100, EParserType.AVITO);
        DirectionEntity realtySaleDirection = parserDao.getDirectionByName(connection, EDirectionName.REALTY_SALE);

        ParseTaskEntity currentEntity = null;
        try {
            Map<String, String> headerMap = avitoParser.initHeaders();

            // Парсить по одному
            //notParsedList.size()
            for (int i = 0; i < notParsedList.size(); i++) {
                ParseTaskEntity parseTaskEntity = notParsedList.get(i);
                currentEntity = parseTaskEntity;
                parseNotice(connection, headerMap, parseTaskEntity, realtySaleDirection, 0);

                connection.commit();

                Thread.sleep(5000);
            }
        } catch (Exception ex) {
            LOGGER.error("Ошибка при сохранении объявления. noticeId: " + currentEntity.getId() +
                                 " url: " + currentEntity.getUrl(), ex);
        } finally {
            synchronized (parseInProcess) {
                parseInProcess.setLocked(false);
            }
        }

    }

    public void updateNoticeDetails(RequestHelper requestHelper, EDirectionName directionName) {

        synchronized (parseInProcess) {
            if (parseInProcess.isLocked()) {
                // Парсинг уже запущен.
                return;
            }

            parseInProcess.setLocked(true);
        }

        try {
            Connection connection = requestHelper.getConnection();

            List<VParserNoticeEntity> vParserNoticeEntityList = parserDao.getNoticesForUpdate(connection, directionName);

            Map<String, String> headerMap = avitoParser.initHeaders();

            for (VParserNoticeEntity parserNotice : vParserNoticeEntityList) {

                ParseTaskEntity parseTaskEntity = new ParseTaskEntity();
                parseTaskEntity.setId(parserNotice.getParseTaskId());
                parseTaskEntity.setUrl(parserNotice.getUrl());
                parseTaskEntity.setTitle(parserNotice.getTitle());

                NoticeEntityWrapper noticeEntityWrapper = avitoParser.parseDetailNotice(headerMap, parseTaskEntity);

                NoticeEntity oldNoticeEntity = parserMapper.toNoticeEntity(parserNotice);
                NoticeEntity newNoticeEntity = noticeEntityWrapper.getNoticeEntity();

                switch (newNoticeEntity.getStatus()) {
                    case DELETED:
                    case DELETED_301:
                        newNoticeEntity.setTitle(oldNoticeEntity.getTitle());
                        newNoticeEntity.setSum(oldNoticeEntity.getSum());
                        newNoticeEntity.setParseTaskId(oldNoticeEntity.getParseTaskId());
                        newNoticeEntity.setHouseId(oldNoticeEntity.getHouseId());
                        newNoticeEntity.setDescription(oldNoticeEntity.getDescription());
                        newNoticeEntity.setAddress(oldNoticeEntity.getAddress());
                        newNoticeEntity.setCloseDate(Timestamp.from(Instant.now()));
                        break;
                    case REMOVED_FROM_PUBLICATION:
                        newNoticeEntity.setParseTaskId(oldNoticeEntity.getParseTaskId());
                        newNoticeEntity.setHouseId(oldNoticeEntity.getHouseId());
                        newNoticeEntity.setDescription(oldNoticeEntity.getDescription());
                        newNoticeEntity.setAddress(oldNoticeEntity.getAddress());

                        if (newNoticeEntity.getSum() == null) {
                            newNoticeEntity.setSum(oldNoticeEntity.getSum());
                        }

                        newNoticeEntity.setCloseDate(Timestamp.from(Instant.now()));
                        break;
                }

                List<DiffAction> noticeDiffActionList = DiffUtils.getDiffList(oldNoticeEntity, newNoticeEntity);

                List<VFeatureValueEntity> oldFeatureEntityList = parserDao.getFeaturesByNoticeId(connection, parserNotice.getId());

                Comparator<FeatureEntity> comparator = (elm1, elm2) -> {
                    return elm1.getName().compareTo(elm2.getName());
                };

                oldFeatureEntityList.sort(comparator);

                List<FeatureComplexValue> featureCreateList = new ArrayList<>();
                List<VFeatureValueEntity> featureUpdateList = new ArrayList<>();
                List<VFeatureValueEntity> featureDeleteList = new ArrayList<>();
                boolean[] existsArr = new boolean[oldFeatureEntityList.size()];
                List<NoticeFeatureActionEntity> noticeFeatureActionEntities = new ArrayList<>();
                FeatureToSaveDto featureToSaveDto = null;

                if (CommonUtils.isNotEmpty(noticeEntityWrapper.getFeatureDtoList())) {
                    featureToSaveDto = getFeatureInfo(connection, noticeEntityWrapper);

                    // Будем считать, что здесь нет неизвестных приложению фичей. Только новые в контексте объявления.
                    Map<String, FeatureComplexValue> featureComplexValueMap = featureToSaveDto.getFeatureValueMap();

                    FeatureEntity searchFeature = new FeatureEntity();

                    for (FeatureComplexValue featureComplexValue : featureComplexValueMap.values()) {
                        // FeatureComplexValue featureComplexValue = featureComplexValueMap.get(keyName);
                        searchFeature.setName(featureComplexValue.getName());
                        int index = Collections.binarySearch(oldFeatureEntityList, searchFeature, comparator);

                        if (index <= -1) {
                            featureCreateList.add(featureComplexValue);
                            noticeFeatureActionEntities.add(new NoticeFeatureActionEntity(
                                    "value", "Создано свойство: " + featureComplexValue.getName(),
                                    featureComplexValue.getValue(), parserNotice.getId()));
                        } else {
                            existsArr[index] = true;
                            VFeatureValueEntity oldFeature = oldFeatureEntityList.get(index);
                            if (!oldFeature.getValue().equals(featureComplexValue.getValue()) ||
                                    oldFeature.getUnits() != featureComplexValue.getUnit()) {
                                oldFeature.setValue(featureComplexValue.getValue());
                                featureUpdateList.add(oldFeature);

                                noticeFeatureActionEntities.add(new NoticeFeatureActionEntity(
                                        "value", featureComplexValue.getName(), featureComplexValue.getValue(), parserNotice.getId()));
                            }
                        }
                    }

                    for (int i = 0; i < existsArr.length; i++) {
                        if (!existsArr[i]) {
                            VFeatureValueEntity oldFeature = oldFeatureEntityList.get(i);
                            featureDeleteList.add(oldFeature);

                            noticeFeatureActionEntities.add(new NoticeFeatureActionEntity(
                                    "value", oldFeature.getName(), null, parserNotice.getId()));
                        }
                    }
                }



                List<NoticeActionEntity> noticeActionEntityList = new ArrayList<>();

                for (DiffAction noticeDiffAction : noticeDiffActionList) {
                    noticeActionEntityList.add(new NoticeActionEntity(noticeDiffAction, parserNotice.getId()));
                }

                parserDao.updateNotice(connection, oldNoticeEntity.getId(), newNoticeEntity);
                parserDao.updateNoticeHistory(connection, noticeActionEntityList);

                if (newNoticeEntity.getStatus() == ENoticeStatus.ACTIVE && featureToSaveDto != null) {
                    parserDao.updateNoticeFeature(connection, oldNoticeEntity.getId(), featureCreateList, featureUpdateList,
                                                  featureDeleteList, featureToSaveDto.getFeatureMapInDB());

                    parserDao.updateNoticeFeatureHistory(connection, noticeFeatureActionEntities);
                }

                connection.commit();

                Thread.sleep(5000);
            }
        } catch (Exception ex) {
            LOGGER.error("Ошибка при обновлении информации об объявлении.", ex);
            throw new RuntimeException("Ошибка при обновлении информации об объявлении.", ex);
        } finally {
            synchronized (parseInProcess) {
                parseInProcess.setLocked(false);
            }
        }



    }

}
