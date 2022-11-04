package dao.realty.excelimport;

import dao.AbstractDao;
import dao.realty.RealtyDao;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import db.entity.realty.excelimport.ImportRealtyRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImportRealtyDao extends AbstractDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyDao.class);
    private static final ImportRealtyDao instance = new ImportRealtyDao();

    public static ImportRealtyDao getInstance() {
        return instance;
    }

    public void saveImportRealtyRequest(Connection connection, ImportRealtyRequestEntity importRealtyRequestEntity,
                                        List<ImportRealtyObjectEntity> ImportRealtyObjectEntity) {

        String sql = "insert into realty.import_realty_request (user_id, objects_count) values (?, ?) returning id";
        String objSql = "insert into realty.import_realty_object (import_excel_request_id, address, rooms_count, realty_segment, house_floors_count, "
                + "wall_material, floor, total_area, kitchen_area, balcon, metro_distance, repair_type) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement objStatement = connection.prepareStatement(objSql)) {
            statement.setLong(1, importRealtyRequestEntity.getUserId());
            statement.setInt(2, importRealtyRequestEntity.getObjectsCount());

            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Ошибка при создании запроса на импорт");
            }

            long importRequestId = rs.getLong("id");

            importRealtyRequestEntity.setId(importRequestId);

            for (ImportRealtyObjectEntity entity : ImportRealtyObjectEntity) {
                objStatement.setLong(1, importRequestId);
                objStatement.setString(2, entity.getAddress());
                objStatement.setString(3, entity.getRoomsCount().getName());
                objStatement.setString(4, entity.getRealtySegment().getName());
                objStatement.setInt(5, entity.getHouseFloorsCount());
                objStatement.setString(6, entity.getWallMaterial().getName());
                objStatement.setInt(7, entity.getFloor());
                objStatement.setDouble(8, entity.getTotalArea());
                objStatement.setDouble(9, entity.getKitchenArea());
                objStatement.setString(10, entity.getBalcon().getName());
                objStatement.setInt(11, entity.getMetroDistance());
                objStatement.setString(12, entity.getRepairType().getName());

                objStatement.addBatch();
            }

            objStatement.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ImportRealtyObjectEntity>  getImportRealtyObjectsByRequest(Connection connection, long requestId) {
        String sql = "select * from realty.import_realty_object "
                + " where import_excel_request_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, requestId);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, ImportRealtyObjectEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ImportRealtyObjectEntity>  getImportRealtyObjectsByIdList(Connection connection, long[] ids) {
        String sql = "select * from realty.import_realty_object "
                + " where id = any(?)";

        Long[] idArr = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            idArr[i] = ids[i];
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setArray(1, connection.createArrayOf("bigint", idArr));
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, ImportRealtyObjectEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
