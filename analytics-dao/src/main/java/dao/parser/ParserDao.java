package dao.parser;

import dao.AbstractDao;
import db.entity.BaseEntity;
import db.entity.parser.DirectionEntity;
import db.entity.parser.DirectionUrlEntity;
import db.entity.parser.FeatureEntity;
import db.entity.parser.NoticeEntity;
import db.entity.parser.ParseTaskEntity;
import db.entity.parser.action.NoticeActionEntity;
import db.entity.parser.action.NoticeFeatureActionEntity;
import db.entity.parser.view.VDistrictNoticeEntity;
import db.entity.parser.view.VFeatureValueEntity;
import db.entity.parser.view.VParserNoticeEntity;
import dto.parser.FeatureComplexValue;
import dto.parser.FeatureDto;
import enums.EDirectionName;
import enums.EFeatureExactName;
import enums.ENoticeStatus;
import enums.EParserStatus;
import enums.EParserType;
import gnu.trove.map.hash.TLongObjectHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserDao extends AbstractDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserDao.class);
    private static final ParserDao instance = new ParserDao();

    public static ParserDao getInstance() {
        return instance;
    }

    private ParserDao() {
    }

    // Здесь должно быть около 50 записей.
    public int saveAllParserEntity(Connection connection, List<ParseTaskEntity> parseTaskEntityList, long directionUrlId) {

        String sql = "insert into parser.parse_task (external_id, type, url, title, status, direction_url_id) "
                + " values (?, ?, ?, ?, ?, ?)"
                + " on conflict (type, external_id) do nothing";

        String featureToDirectionSql = "";

        String featureSql = "";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            for (ParseTaskEntity parseTaskEntity : parseTaskEntityList) {
                statement.setString(1, parseTaskEntity.getExternalId());
                statement.setString(2, parseTaskEntity.getType().getName());
                statement.setString(3, parseTaskEntity.getUrl());
                statement.setString(4, parseTaskEntity.getTitle());
                statement.setString(5, parseTaskEntity.getStatus().getName());
                statement.setLong(6, directionUrlId);

                statement.addBatch();
            }

            int[] rowsCountArr = statement.executeBatch();

            int sum = 0;

            for (int i = 0; i < rowsCountArr.length; i++) {
                sum += rowsCountArr[i];
            }

            return sum;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<DirectionUrlEntity> getUrlsByDirection(Connection connection, EDirectionName directionName) {

        String sql = "select du.* from parser.direction d"
                + "    join parser.direction_url du on d.id = du.direction_id"
                + " where d.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, directionName.getName());
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, DirectionUrlEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ParseTaskEntity> getNotParcedEntity(Connection connection, int count, EParserType parserType) {
        String sql = "select * from parser.parse_task pt" +
                "  where pt.type = ? and status = ? limit ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parserType.getName());
            statement.setString(2, EParserStatus.NOT_PARSED.getName());
            statement.setInt(3, count);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, ParseTaskEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DirectionEntity getDirectionByName(Connection connection, EDirectionName directionName) {
        String sql = "select * from parser.direction d" +
                "  where d.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, directionName.getName());
            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, DirectionEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Map<String, FeatureEntity> getFeaturesByNames(Connection connection, List<FeatureDto> rawFeatureList) {

        if (rawFeatureList == null || rawFeatureList.isEmpty()) {
            return new HashMap<>();
        }

        String sql = "select * from parser.feature where name = any(?)";

        String[] featureNameArr = new String[rawFeatureList.size()];
        for(int i = 0; i < featureNameArr.length; i++) {
            FeatureDto featureEntity = rawFeatureList.get(i);
            featureNameArr[i] = featureEntity.getName();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Array array = connection.createArrayOf("varchar", (Object[])featureNameArr);

            statement.setArray(1, array);
            ResultSet resultSet = statement.executeQuery();

            return mappingToMap(resultSet, FeatureEntity.class, FeatureEntity::getName);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public FeatureEntity getFeatureByExactName(Connection connection, EFeatureExactName exactName) {
        String sql = "select * from parser.feature where exact_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, exactName.getName());
            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, FeatureEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<VFeatureValueEntity> getFeaturesByNoticeId(Connection connection, long noticeId) {
        String sql = "select f.*, ftn.id as feature_to_notice_id, ftn.notice_id, ftn.value, ftn.units from parser.feature f "
                + " join parser.feature_to_notice ftn on ftn.feature_id = f.id "
                + " where ftn.notice_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, noticeId);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VFeatureValueEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Объявления без домов не идут в расчёт.
     *
     * @param connection
     * @return
     */
    public List<VDistrictNoticeEntity> getActiveNotices(Connection connection) {
        String sql = "select n.*, h.district_id from parser.notice n "
                + "    join realty.house h on n.house_id = h.id "
                + "where status = 'ACTIVE' and h.district_id > 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VDistrictNoticeEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<VDistrictNoticeEntity> getNoticesByHouse(Connection connection, long houseId) {

        String sql = "select n.*, h.district_id from parser.notice n "
                + "    join realty.house h on n.house_id = h.id "
                + "where n.house_id = ? and h.district_id > 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, houseId);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VDistrictNoticeEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<NoticeEntity> getAllNotices(Connection connection) {
        String sql = "select * from parser.notice";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, NoticeEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public TLongObjectHashMap<List<VFeatureValueEntity>> getFeaturesByNoticeIdArr(Connection connection, Long[] noticeIds) {
        String sql = "select f.*, ftn.id as feature_to_notice_id, ftn.notice_id, ftn.value, ftn.units "
                + " from parser.feature_to_notice ftn"
                + "      join parser.feature f on ftn.feature_id = f.id "
                + " where ftn.notice_id = any(?) and f.exact_name is not null";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setArray(1, connection.createArrayOf("bigint", noticeIds));
            ResultSet resultSet = statement.executeQuery();

            return groupByLongVal(resultSet, VFeatureValueEntity.class, VFeatureValueEntity::getNoticeId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<VParserNoticeEntity> getNoticesForUpdate(Connection connection, EDirectionName directionName) {
        String sql = "select n.*, pt.url from parser.notice n "
                + "    join parser.parse_task pt on pt.id = n.parse_task_id "
                + "    join parser.direction_url du on pt.direction_url_id = du.id "
                + "    join parser.direction d on du.direction_id = d.id "
                + "where ((now() - n.update_time) > interval '1' day or n.update_time is null) "
                // + "where (n.update_time is null) "
                + "  and d.name = ? and pt.type = ? and n.status in ('ACTIVE')"
                + " limit 100";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, directionName.getName());
            statement.setString(2, EParserType.AVITO.getName());
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VParserNoticeEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setNoticeParams(PreparedStatement statement, NoticeEntity noticeEntity) throws SQLException {
        statement.setLong(1, noticeEntity.getParseTaskId());
        statement.setString(2, noticeEntity.getTitle());
        statement.setDouble(3, noticeEntity.getSum());
        statement.setString(4, noticeEntity.getAddress());
        statement.setString(5, noticeEntity.getDescription());
        statement.setString(6, noticeEntity.getStatus().getName());
    }

    public void saveNotice(Connection connection, NoticeEntity noticeEntity, DirectionEntity directionEntity,
                           List<FeatureDto> featuresForCreate, Map<String, FeatureEntity> existingFeatureMap,
                           Map<String, FeatureComplexValue> featureValueMap) {
        String sql = "insert into parser.notice (parse_task_id, title, sum, address, description, status) "
                + "values (?, ?, ?, ?, ?, ?) returning id";

        String featureSql = "insert into parser.feature (name) values ";
        String featureValuePart = "(?)";
        String featureFinishPart = " returning id, name";

        String featureToDirectionSql = "insert into parser.feature_to_direction (feature_id, direction_id) values";
        String featToDirPart = "(?, " + directionEntity.getId() +")";

        String featureToNoticeSql = "insert into parser.feature_to_notice (feature_id, notice_id, value, units) values ";
        String featToNotPart = "(?, ?, ?, ?)";

        List<NoticeFeatureActionEntity> noticeFeatureActionEntities = new ArrayList<>();
        List<FeatureEntity> completeFeatureEntityList = new ArrayList<>();

        for (FeatureEntity featureEntity : existingFeatureMap.values()) {
            completeFeatureEntityList.add(featureEntity);
        }

        try {
            if (!featuresForCreate.isEmpty()) {
                for (int i = 0; i < featuresForCreate.size(); i++) {
                    featureSql += featureValuePart;
                    featureToDirectionSql += featToDirPart;

                    if (i < featuresForCreate.size() - 1) {
                        featureSql += ", ";
                        featureToDirectionSql += ", ";
                    }
                }

                featureSql += featureFinishPart;

                try (PreparedStatement featureStatement = connection.prepareStatement(featureSql);
                    PreparedStatement featToDirStatement = connection.prepareStatement(featureToDirectionSql)) {
                    for (int i = 0; i < featuresForCreate.size(); i++) {
                        featureStatement.setString(i + 1, featuresForCreate.get(i).getName());
                    }

                    // Добавление фичей объявления.
                    ResultSet featureResultSet = featureStatement.executeQuery();
                    List<FeatureEntity> savedFeatureEntityList = mappingMultipleResult(featureResultSet, FeatureEntity.class);

                    for (int i = 0; i < savedFeatureEntityList.size(); i++) {
                        featToDirStatement.setLong(i + 1, savedFeatureEntityList.get(i).getId());
                    }

                    // Добавление фичей к направлению...
                    featToDirStatement.executeUpdate();

                    completeFeatureEntityList.addAll(savedFeatureEntityList);
                }
            }

            if (!completeFeatureEntityList.isEmpty()) {
                // notice_to_feature
                for (int i = 0; i < completeFeatureEntityList.size(); i++) {

                    featureToNoticeSql += featToNotPart;

                    if (i < completeFeatureEntityList.size() - 1) {
                        featureToNoticeSql += ", ";
                    }

                }
            }

            try (PreparedStatement statement = connection.prepareStatement(sql);
                PreparedStatement fetToNotStatement = connection.prepareStatement(featureToNoticeSql)) {
                setNoticeParams(statement, noticeEntity);

                ResultSet noticeResultSet = statement.executeQuery();
                long noticeId = -1;

                if (noticeResultSet.next()) {
                    noticeId = noticeResultSet.getLong("id");
                } else {
                    throw new RuntimeException("Ошибка при сохранении объявления в БД.");
                }



                // List<FeatureEntity> featureEntityList = noticeEntityWrapper.getFeatureEntityList();

                if (!completeFeatureEntityList.isEmpty()) {
                    int j = 1;
                    for (int i = 0; i < completeFeatureEntityList.size(); i++) {
                        FeatureEntity featureEntity = completeFeatureEntityList.get(i);

                        FeatureComplexValue complexValue = featureValueMap.get(featureEntity.getName());
                        noticeFeatureActionEntities.add(new NoticeFeatureActionEntity("value",
                                                                                      "Создано свойство: " + featureEntity.getName(),
                                                                                      complexValue.getValue(), noticeId));

                        fetToNotStatement.setLong(j++, featureEntity.getId());
                        fetToNotStatement.setLong(j++, noticeId);
                        fetToNotStatement.setString(j++, complexValue.getValue());

                        if (complexValue.getUnit() == null) {
                            fetToNotStatement.setNull(j++, Types.VARCHAR);
                        } else {
                            fetToNotStatement.setString(j++, complexValue.getUnit().getName());
                        }
                    }

                    fetToNotStatement.executeUpdate();
                }
            }

            this.updateNoticeFeatureHistory(connection, noticeFeatureActionEntities);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateNotice(Connection connection, long noticeId, NoticeEntity updatedNoticeEntity) {

        String sql = "update parser.notice set update_time = now(), parse_task_id = ?, title = ?, sum = ?, "
                + "address = ?, description = ?, status = ?, close_date = ? "
                + " where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setNoticeParams(statement, updatedNoticeEntity);
            if (updatedNoticeEntity.getCloseDate() != null) {
                statement.setTimestamp(7, updatedNoticeEntity.getCloseDate());
            } else {
                statement.setNull(7, Types.TIMESTAMP);
            }

            statement.setLong(8, noticeId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


    public void setParserTaskStatus(Connection connection, long parserTaskId, EParserStatus parserStatus) {
        String sql = "update parser.parse_task set status = ? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parserStatus.getName());
            statement.setLong(2, parserTaskId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveHouseNubmerToNotice(Connection connection, long houseId, long noticeId) {
        String sql = "update parser.notice set house_id = ? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, houseId);
            statement.setLong(2, noticeId);

            statement.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateNoticeFeature(Connection connection, long noticeId, List<FeatureComplexValue> featureCreateList,
                                    List<VFeatureValueEntity> featureUpdateList,
                                    List<VFeatureValueEntity> featureDeleteList,
                                    Map<String, FeatureEntity> featureMapInDB) {

        String featureCreateSql = "insert into parser.feature_to_notice (feature_id, notice_id, value, units) "
                + " values (?, ?, ?, ?)";

        String featureUpdateSql = "update parser.feature_to_notice set feature_id = ?, notice_id = ?, value = ?, units = ? "
                + " where id = ?";

        String featureDeleteSql = "delete from parser.feature_to_notice "
                + " where id = any(?)";

        try {

            if (!featureCreateList.isEmpty()) {
                try (PreparedStatement statement = connection.prepareStatement(featureCreateSql)) {
                    for (int i = 0; i < featureCreateList.size(); i++) {
                        FeatureComplexValue featureComplexValue = featureCreateList.get(i);

                        FeatureEntity featureInDB = featureMapInDB.get(featureComplexValue.getName());

                        if (featureInDB == null) {
                            throw new RuntimeException(
                                    "Не найдено фичи в БД при обновлении объявления. noticeId: " + noticeId
                                            + " " + featureComplexValue.getName());
                        }

                        statement.setLong(1, featureInDB.getId());
                        statement.setLong(2, noticeId);
                        statement.setString(3, featureComplexValue.getValue());
                        if (featureComplexValue.getUnit() != null) {
                            statement.setString(4, featureComplexValue.getUnit().getName());
                        } else {
                            statement.setNull(4, Types.VARCHAR);
                        }

                        statement.addBatch();
                    }

                    statement.executeBatch();
                }
            }

            if (!featureUpdateList.isEmpty()) {
                try (PreparedStatement statement = connection.prepareStatement(featureUpdateSql)) {
                    for (int i = 0; i < featureUpdateList.size(); i++) {
                        VFeatureValueEntity featureComplexValue = featureUpdateList.get(i);

                        FeatureEntity featureInDB = featureMapInDB.get(featureComplexValue.getName());

                        if (featureInDB == null) {
                            throw new RuntimeException(
                                    "Не найдено фичи в БД при обновлении объявления. noticeId: " + noticeId
                                            + " " + featureComplexValue.getName());
                        }

                        statement.setLong(1, featureInDB.getId());
                        statement.setLong(2, noticeId);
                        statement.setString(3, featureComplexValue.getValue());
                        if (featureComplexValue.getUnits() != null) {
                            statement.setString(4, featureComplexValue.getUnits().getName());
                        } else {
                            statement.setNull(4, Types.VARCHAR);
                        }

                        statement.setLong(5, featureComplexValue.getFeatureToNoticeId());

                        statement.addBatch();
                    }

                    statement.executeBatch();
                }
            }

            if (!featureDeleteList.isEmpty()) {
                try (PreparedStatement statement = connection.prepareStatement(featureDeleteSql)) {

                    Long[] idsArr = new Long[featureDeleteList.size()];

                    for (int i = 0; i < featureDeleteList.size(); i++) {
                        idsArr[i] = featureDeleteList.get(i).getFeatureToNoticeId();
                    }
                    
                    Array array = connection.createArrayOf("bigint", idsArr);
                    statement.setArray(1, array);

                    statement.executeUpdate();
                }
            }


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public void updateNoticeHistory(Connection connection, List<NoticeActionEntity> noticeActionEntityList) {
        String sql = "insert into parser.notice_action (notice_id, field, title, value) values (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < noticeActionEntityList.size(); i++) {
                NoticeActionEntity action = noticeActionEntityList.get(i);
                statement.setLong(1, action.getNoticeId());
                statement.setString(2, action.getField());
                statement.setString(3, action.getTitle());
                statement.setString(4, action.getValue());

                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateNoticeFeatureHistory(Connection connection, List<NoticeFeatureActionEntity> noticeFeatureActionEntities) {
        String sql = "insert into parser.notice_feature_action (notice_id, field, title, value) values (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < noticeFeatureActionEntities.size(); i++) {
                NoticeFeatureActionEntity action = noticeFeatureActionEntities.get(i);
                statement.setLong(1, action.getNoticeId());
                statement.setString(2, action.getField());
                statement.setString(3, action.getTitle());
                statement.setString(4, action.getValue());

                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<BaseEntity> findClosedNoticesByNewTasks(Connection connection, String[] taskExternalArr) {
        String sql = "select n.id from parser.notice n "
                + "    join parser.parse_task pt on n.parse_task_id = pt.id"
                + "    where pt.external_id = any(?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setArray(1, connection.createArrayOf("varchar", taskExternalArr));
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, BaseEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void returnNoticesToActive(Connection connection, Long[] noticesIds) {
        String sql = "update parser.notice set update_time = null, status = ? "
                + "     where id = any(?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ENoticeStatus.ACTIVE.getName());
            statement.setArray(2, connection.createArrayOf("bigint", noticesIds));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
