package dao.realty;

import com.fasterxml.jackson.databind.ObjectMapper;
import consts.ProjectConst;
import dao.AbstractDao;
import db.entity.parser.view.VNoticeEntity;
import db.entity.realty.CityEntity;
import db.entity.realty.DistrictEntity;
import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import db.entity.realty.NoticeAveragePriceEntity;
import db.entity.realty.NoticeCategoryEntity;
import db.entity.realty.ShortDistrictEntity;
import db.entity.realty.admin.AddrHouseEntity;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import enums.EDirectionName;
import enums.realty.EStreetType;
import enums.realty.EVillageType;
import enums.report.EBalconParam;
import enums.report.EFloor;
import enums.report.EHouseBuildYear;
import enums.report.EHouseFloor;
import enums.report.EHouseType;
import enums.report.ERoomsCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class RealtyDao extends AbstractDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealtyDao.class);
    private static final RealtyDao instance = new RealtyDao();

    public static RealtyDao getInstance() {
        return instance;
    }

    private RealtyDao() {
    }

    public List<VNoticeEntity> getNoticesWithoutAddressByDirection(Connection connection, EDirectionName directionName) {

        String sql = "select n.*, du.city_id, c.name as city_name from parser.notice n"
                + "    join parser.parse_task pt on pt.id = n.parse_task_id"
                + "    join parser.direction_url du on pt.direction_url_id = du.id"
                + "    join realty.city c on c.id = du.city_id "
                + "    join parser.direction d on du.direction_id = d.id "
                + "where d.name = ? and n.house_id is null";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, directionName.getName());
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VNoticeEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public CityEntity getCityById(Connection connection, long cityId) {
        String sql = "select * from realty.city "
                + " where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cityId);
            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, CityEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CityEntity getCityByName(Connection connection, String name) {
        String sql = "select * from realty.city "
                + " where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, CityEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long findFiasVillage(Connection connection, long fiasCityId, String village, EVillageType villageType) {
        String sql = "select id from realty.fias_addr fa"
                + " where fa.city_id = ? and (lower(fa.off_name) = ? or lower(fa.formal_name) = ?) and fa.shortname = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String villageLower = village.toLowerCase();
            statement.setLong(1, fiasCityId);
            statement.setString(2, villageLower);
            statement.setString(3, villageLower);
            statement.setString(4, villageType.getFiasShortName());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }

            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long findFiasStreet(Connection connection, long fiasCityId, String street, EStreetType streetType) {
        String sql = "select id from realty.fias_addr fa"
                + " where fa.city_id = ? and (lower(fa.off_name) = ? or lower(fa.formal_name) = ?) and fa.shortname = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String lowerStreet = street.toLowerCase();
            statement.setLong(1, fiasCityId);
            statement.setString(2, lowerStreet);
            statement.setString(3, lowerStreet);
            statement.setString(4, streetType.getFiasShortName());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }

            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long findFiasStreet(Connection connection, long fiasCityId, String street, EStreetType streetType, long parentId) {
        String sql = "with recursive street_info as (\n"
                + "    select id, fias_uuid, off_name, formal_name, shortname, city_id from realty.fias_addr where id = ? "
                + "    union\n"
                + "    select child.id, child.fias_uuid, child.off_name, child.formal_name, child.shortname, child.city_id from realty.fias_addr child\n"
                + "        join street_info si on si.fias_uuid = child.parent_uuid\n"
                + ")\n"
                + "select id from street_info where city_id = ? and shortname = ? and (lower(formal_name) = ? or lower(off_name) = ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String lowerStreet = street.toLowerCase();
            statement.setLong(1, parentId);
            statement.setLong(2, fiasCityId);
            statement.setString(3, streetType.getFiasShortName());
            statement.setString(4, lowerStreet);
            statement.setString(5, lowerStreet);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }

            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HouseEntity getHouseByAddress(Connection connection, String streetAddr, String houseNum, CityEntity cityEntity) {

        String sql = "select h.* from realty.house h "
                + "    join realty.fias_addr fa on h.fias_street = fa.id "
                + "where h.house_num = ? and h.city_id = ? and h.street = ?";

        String houseNumFinal = (houseNum != null) ? houseNum : "-";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, houseNumFinal);
            statement.setLong(2, cityEntity.getId());
            statement.setString(3, streetAddr);

            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, HouseEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<CityEntity> getCityEntityList(Connection connection) {
        String sql = "select id, name, lat, lng from realty.city";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, CityEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DistrictEntity getDistrictById(Connection connection, long districtId) {
        String sql = "select * from realty.district where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, districtId);
            ResultSet resultSet = statement.executeQuery();

            return mappingSingleResult(resultSet, DistrictEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<DistrictEntity> getAllDistricts(Connection connection) {
        String sql = "select * from realty.district";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, DistrictEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ShortDistrictEntity> getMicroDistricts(Connection connection, long cityId, int levelStart, int levelEnd) {
        String sql = "select id, name, parent_district_id from realty.district where city_id = ? and level between ? and ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cityId);
            statement.setLong(2, levelStart);
            statement.setLong(3, levelEnd);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, ShortDistrictEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveDistrictCoords(Connection connection, long id, String coords) {
        String sql = "update realty.district set coords = ? :: jsonb where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, coords);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<DistrictEntity> getDistrictListByCity(Connection connection, long cityId) {
        String sql = "select * from realty.district where city_id = ? order by level";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cityId);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, DistrictEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HouseEntity saveHouse(Connection connection, HouseEntity houseEntity) {
        ObjectMapper objectMapper = ProjectConst.MAPPER;
        String sql = "insert into realty.house (street, house_num, district_id, city_id, coords, fias_street) "
                + " values (?, ?, ?, ?, ? :: jsonb, ?) returning id";

        String houseNum = (houseEntity.getHouseNum() != null) ? houseEntity.getHouseNum() : "-";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, houseEntity.getStreet());
            statement.setString(2, houseNum);
            statement.setLong(3, houseEntity.getDistrictId());
            statement.setLong(4, houseEntity.getCityId());
            statement.setString(5, objectMapper.writeValueAsString(houseEntity.getCoords()));
            statement.setLong(6, houseEntity.getFiasStreet());

            ResultSet resultSet = statement.executeQuery();
            long id = -1;

            if (resultSet.next()) {
                id = resultSet.getLong("id");
            } else {
                throw new RuntimeException("Ошибка при сохранении дома");
            }

            houseEntity.setId(id);

            return houseEntity;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<HouseEntity> getHousesWithoutDistrict(Connection connection) {
        String sql = "select * from realty.house where district_id = 1 and id > 0";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, HouseEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveHouseDistrict(Connection connection, long houseId, long districtId) {
        String sql = "update realty.house set district_id = ? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, districtId);
            statement.setLong(2, houseId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveOrUpdateNoticeCategory(Connection connection, NoticeCategoryEntity noticeCategoryEntity) {
        String sql = "insert into realty.notice_category (notice_id, canon_type_number, rooms_count, floor, house_floor, house_type, house_build_year, balcon, classifier_category,"
                + "realty_segment, repair_type, simple_house_type, total_square, kitchen_square, metro_distance, square_value) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "    on conflict(notice_id) do update set canon_type_number = ?, rooms_count = ?, floor = ?, house_floor = ?, house_type = ?, "
                + "                                         house_build_year = ?, balcon = ?, classifier_category = ?, realty_segment = ?, "
                + "                                         repair_type = ?, simple_house_type = ?, total_square = ?, kitchen_square = ?, metro_distance = ?,"
                + "                                         square_value = ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, noticeCategoryEntity.getNoticeId());

            statement.setInt(2, noticeCategoryEntity.getCanonTypeNumber());
            statement.setInt(17, noticeCategoryEntity.getCanonTypeNumber());

            statement.setDouble(16, noticeCategoryEntity.getSquareValue());
            statement.setDouble(31, noticeCategoryEntity.getSquareValue());

            if (noticeCategoryEntity.getRoomsCount() == null) {
                statement.setNull(3, Types.VARCHAR);
                statement.setNull(18, Types.VARCHAR);
            } else {
                statement.setString(3, noticeCategoryEntity.getRoomsCount().getName());
                statement.setString(18, noticeCategoryEntity.getRoomsCount().getName());
            }

            if (noticeCategoryEntity.getFloor() == null) {
                statement.setNull(4, Types.VARCHAR);
                statement.setNull(19, Types.VARCHAR);
            } else {
                statement.setString(4, noticeCategoryEntity.getFloor().getName());
                statement.setString(19, noticeCategoryEntity.getFloor().getName());
            }

            if (noticeCategoryEntity.getHouseFloor() == null) {
                statement.setNull(5, Types.VARCHAR);
                statement.setNull(20, Types.VARCHAR);
            } else {
                statement.setString(5, noticeCategoryEntity.getHouseFloor().getName());
                statement.setString(20, noticeCategoryEntity.getHouseFloor().getName());
            }

            if (noticeCategoryEntity.getHouseType() == null) {
                statement.setNull(6, Types.VARCHAR);
                statement.setNull(21, Types.VARCHAR);
            } else {
                statement.setString(6, noticeCategoryEntity.getHouseType().getName());
                statement.setString(21, noticeCategoryEntity.getHouseType().getName());
            }

            if (noticeCategoryEntity.getHouseBuildYear() == null) {
                statement.setNull(7, Types.VARCHAR);
                statement.setNull(22, Types.VARCHAR);
            } else {
                statement.setString(7, noticeCategoryEntity.getHouseBuildYear().getName());
                statement.setString(22, noticeCategoryEntity.getHouseBuildYear().getName());
            }

            if (noticeCategoryEntity.getBalcon() == null) {
                statement.setNull(8, Types.VARCHAR);
                statement.setNull(23, Types.VARCHAR);
            } else {
                statement.setString(8, noticeCategoryEntity.getBalcon().getName());
                statement.setString(23, noticeCategoryEntity.getBalcon().getName());
            }

            // classifier_category
            if (noticeCategoryEntity.getRealtyConfigType() == null) {
                statement.setNull(9, Types.VARCHAR);
                statement.setNull(24, Types.VARCHAR);
            } else {
                statement.setString(9, noticeCategoryEntity.getRealtyConfigType().getConfigName());
                statement.setString(24, noticeCategoryEntity.getRealtyConfigType().getConfigName());
            }

            if (noticeCategoryEntity.getRealtySegment() == null) {
                statement.setNull(10, Types.VARCHAR);
                statement.setNull(25, Types.VARCHAR);
            } else {
                statement.setString(10, noticeCategoryEntity.getRealtySegment().getName());
                statement.setString(25, noticeCategoryEntity.getRealtySegment().getName());
            }

            if (noticeCategoryEntity.getRepairType() == null) {
                statement.setNull(11, Types.VARCHAR);
                statement.setNull(26, Types.VARCHAR);
            } else {
                statement.setString(11, noticeCategoryEntity.getRepairType().getName());
                statement.setString(26, noticeCategoryEntity.getRepairType().getName());
            }

            if (noticeCategoryEntity.getSimpleHouseType() == null) {
                statement.setNull(12, Types.VARCHAR);
                statement.setNull(27, Types.VARCHAR);
            } else {
                statement.setString(12, noticeCategoryEntity.getSimpleHouseType().getName());
                statement.setString(27, noticeCategoryEntity.getSimpleHouseType().getName());
            }

            if (noticeCategoryEntity.getTotalArea() == null) {
                statement.setNull(13, Types.VARCHAR);
                statement.setNull(28, Types.VARCHAR);
            } else {
                statement.setString(13, noticeCategoryEntity.getTotalArea().getName());
                statement.setString(28, noticeCategoryEntity.getTotalArea().getName());
            }

            if (noticeCategoryEntity.getKitchenArea() == null) {
                statement.setNull(14, Types.VARCHAR);
                statement.setNull(29, Types.VARCHAR);
            } else {
                statement.setString(14, noticeCategoryEntity.getKitchenArea().getName());
                statement.setString(29, noticeCategoryEntity.getKitchenArea().getName());
            }

            if (noticeCategoryEntity.getMetroDistance() == null) {
                statement.setNull(15, Types.VARCHAR);
                statement.setNull(30, Types.VARCHAR);
            } else {
                statement.setString(15, noticeCategoryEntity.getMetroDistance().getName());
                statement.setString(30, noticeCategoryEntity.getMetroDistance().getName());
            }

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveOrUpdateNoticeAveragePrice(Connection connection, List<NoticeAveragePriceEntity> noticeAveragePriceList) {
        String sql = "insert into realty.notice_average_price (canon_type_number, realty_config_type, sum) "
                + "values (?, ?, ?) "
                + "    on conflict (canon_type_number, realty_config_type) do update set sum = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (NoticeAveragePriceEntity noticeAveragePrice : noticeAveragePriceList) {
                statement.setInt(1, noticeAveragePrice.getCanonicalTypeNumber());
                statement.setString(2, noticeAveragePrice.getRealtyConfigType());
                statement.setDouble(3, noticeAveragePrice.getSum());
                statement.setDouble(4, noticeAveragePrice.getSum());

                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<VNoticeInfoWithAvgPriceEntity> getNoticesInfoWithAvgPrice(Connection connection,
                                                                          NoticeCategoryEntity noticeCategoryEntity,
                                                                          boolean strict) {

        String sql = "select * from realty.v_notice_info_with_avg_price";
        String where = "";

        if (noticeCategoryEntity.getRoomsCount() != null) {
            where = addWhereCondition(where, true, "rooms_count = ?");
        }
        if (noticeCategoryEntity.getFloor() != null && strict) {
            where = addWhereCondition(where, true, "floor = ?");
        }
        if (noticeCategoryEntity.getHouseFloor() != null) {
            where = addWhereCondition(where, true, "house_floor = ?");
        }
        if (noticeCategoryEntity.getHouseType() != null) {
            where = addWhereCondition(where, true, "house_type = ?");
        }
        if (noticeCategoryEntity.getHouseBuildYear() != null) {
            where = addWhereCondition(where, true, "house_build_year = ?");
        }
        if (noticeCategoryEntity.getBalcon() != null && strict) {
            where = addWhereCondition(where, true, "balcon = ?");
        }
        if (noticeCategoryEntity.getRealtyConfigType() != null) {
            where = addWhereCondition(where, true, "classifier_category = ?");
        }
        if (noticeCategoryEntity.getRealtySegment() != null) {
            where = addWhereCondition(where, true, "realty_segment = ?");
        }
        if (noticeCategoryEntity.getRepairType() != null && strict) {
            where = addWhereCondition(where, true, "repair_type = ?");
        }
        if (noticeCategoryEntity.getSimpleHouseType() != null) {
            where = addWhereCondition(where, true, "simple_house_type = ?");
        }
        if (noticeCategoryEntity.getTotalArea() != null && strict) {
            where = addWhereCondition(where, true, "total_square = ?");
        }
        if (noticeCategoryEntity.getKitchenArea() != null && strict) {
            where = addWhereCondition(where, true, "kitchen_square = ?");
        }
        if (noticeCategoryEntity.getMetroDistance() != null && strict) {
            where = addWhereCondition(where, true, "metro_distance = ?");
        }

        int idx = 1;

        sql += where;
        // sql += " order by (sum / square_value - average_sum)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            if (noticeCategoryEntity.getRoomsCount() != null) {
                statement.setString(idx++, noticeCategoryEntity.getRoomsCount().getName());
            }
            if (noticeCategoryEntity.getFloor() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getFloor().getName());
            }
            if (noticeCategoryEntity.getHouseFloor() != null) {
                statement.setString(idx++, noticeCategoryEntity.getHouseFloor().getName());
            }
            if (noticeCategoryEntity.getHouseType() != null) {
                statement.setString(idx++, noticeCategoryEntity.getHouseType().getName());
            }
            if (noticeCategoryEntity.getHouseBuildYear() != null) {
                statement.setString(idx++, noticeCategoryEntity.getHouseBuildYear().getName());
            }
            if (noticeCategoryEntity.getBalcon() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getBalcon().getName());
            }
            if (noticeCategoryEntity.getRealtyConfigType() != null) {
                statement.setString(idx++, noticeCategoryEntity.getRealtyConfigType().getConfigName());
            }
            if (noticeCategoryEntity.getRealtySegment() != null) {
                statement.setString(idx++, noticeCategoryEntity.getRealtySegment().getName());
            }
            if (noticeCategoryEntity.getRepairType() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getRepairType().getName());
            }
            if (noticeCategoryEntity.getSimpleHouseType() != null) {
                statement.setString(idx++, noticeCategoryEntity.getSimpleHouseType().getName());
            }
            if (noticeCategoryEntity.getTotalArea() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getTotalArea().getName());
            }
            if (noticeCategoryEntity.getKitchenArea() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getKitchenArea().getName());
            }
            if (noticeCategoryEntity.getMetroDistance() != null && strict) {
                statement.setString(idx++, noticeCategoryEntity.getMetroDistance().getName());
            }

            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VNoticeInfoWithAvgPriceEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<VNoticeInfoWithAvgPriceEntity> getNoticesInfoWithAvgPrice(Connection connection, long[] districtIdArr,
                                                                          ERoomsCount roomsCount, EFloor floor, EHouseFloor houseFloor,
                                                                          EHouseType houseType, EHouseBuildYear houseBuildYear,
                                                                          EBalconParam balconParam) {
        String sql = "select * from realty.v_notice_info_with_avg_price";
        String where = "";
        if (districtIdArr != null && districtIdArr.length > 0) {
            where = addWhereCondition(where, true, "district_id = any(?)");
        }
        if (roomsCount != null) {
            where = addWhereCondition(where, true, "rooms_count = ?");
        }
        if (floor != null) {
            where = addWhereCondition(where, true, "floor = ?");
        }
        if (houseFloor != null) {
            where = addWhereCondition(where, true, "house_floor = ?");
        }
        if (houseType != null) {
            where = addWhereCondition(where, true, "house_type = ?");
        }
        if (houseBuildYear != null) {
            where = addWhereCondition(where, true, "house_build_year = ?");
        }
        if (balconParam != null) {
            where = addWhereCondition(where, true, "balcon = ?");
        }

        int idx = 1;

        sql += where;
        sql += " order by (sum / square_value - average_sum)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            if (districtIdArr != null && districtIdArr.length > 0) {
                Long[] idsLongArr = new Long[districtIdArr.length];
                for (int i = 0; i < districtIdArr.length; i++) {
                    idsLongArr[i] = districtIdArr[i];
                }

                Array idsArr = connection.createArrayOf("bigint", idsLongArr);

                statement.setArray(idx++, idsArr);
            }
            if (roomsCount != null) {
                statement.setString(idx++, roomsCount.getName());
            }
            if (floor != null) {
                statement.setString(idx++, floor.getName());
            }
            if (houseFloor != null) {
                statement.setString(idx++, houseFloor.getName());
            }
            if (houseType != null) {
                statement.setString(idx++, houseType.getName());
            }
            if (houseBuildYear != null) {
                statement.setString(idx++, houseBuildYear.getName());
            }
            if (balconParam != null) {
                statement.setString(idx, balconParam.getName());
            }

            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, VNoticeInfoWithAvgPriceEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void bindDistrictParams(PreparedStatement statement, DistrictEntity districtEntity) throws SQLException, IOException {
        statement.setString(1, districtEntity.getName());
        statement.setString(2, ProjectConst.MAPPER.writeValueAsString(districtEntity.getCoords()));
        statement.setLong(3, districtEntity.getCityId());
        if (districtEntity.getParentDistrictId() != null) {
            statement.setLong(4, districtEntity.getParentDistrictId());
        } else {
            statement.setNull(4, Types.BIGINT);
        }

        statement.setString(5, districtEntity.getParents());
        statement.setInt(6, districtEntity.getLevel());
    }

    public void createNewDistrict(Connection connection, DistrictEntity districtEntity) {
        String sql = "insert into realty.district (name, coords, city_id, parent_district_id, parents, level) "
                + " values (?, ? :: jsonb, ?, ?, ?  :: int[], ?)";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            bindDistrictParams(statement, districtEntity);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateDistrict(Connection connection, DistrictEntity districtEntity) {
        String sql = "update realty.district set name = ?, coords = ? :: jsonb, city_id = ?, parent_district_id = ?,"
                + " parents = ? :: int[], level = ? where id = ?";

        String updateChildsSql = "WITH RECURSIVE d_info AS( "
                + "    select id, parents, level from realty.district where id = ? "
                + "    UNION "
                + "    select d2.id, array_append(d3.parents, d3.id), d2.level from realty.district d2 "
                + "        join d_info d3 on d3.id = d2.parent_district_id "
                + ") "
                + "update realty.district set parents = d_info_sub.parents, level = d_info_sub.level "
                + "from (select * from d_info) d_info_sub "
                + "where district.id = d_info_sub.id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement childrenStmt = connection.prepareStatement(updateChildsSql)) {
            bindDistrictParams(statement, districtEntity);
            statement.setLong(7, districtEntity.getId());

            statement.executeUpdate();

            childrenStmt.setLong(1, districtEntity.getId());
            childrenStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteDistrictById(Connection connection, long districtId, Long childParentId) {
        String sql = "update realty.district set parent_district_id = ? where parent_district_id = ?";

        String updateChildsSql = "update realty.district set level = level - 1, "
                + "                           parents = array_remove(parents, ?) "
                + "where ? = any(parents);";

        String deleteDistrictSql = "delete from realty.district where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement childrenStmt = connection.prepareStatement(updateChildsSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteDistrictSql)) {
            statement.setLong(1, childParentId);
            statement.setLong(2, districtId);

            statement.executeUpdate();

            childrenStmt.setLong(1, districtId);
            childrenStmt.setLong(2, districtId);
            childrenStmt.executeUpdate();

            deleteStatement.setLong(1, districtId);
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<HouseEntity> getHousesWithoutBuildYear(Connection connection, int count) {
        String sql = "select h.* from realty.house h "
                + " left join realty.house_add_info hai on h.id = hai.house_id "
                + " where hai.id is null limit ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, HouseEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveHouseAdditionalInfo(Connection connection, HouseAddInfoEntity entity) {
        String sql = "insert into realty.house_add_info (house_id, build_year, floors_count, deterioration, house_type) "
                + " values (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, entity.getHouseId());
            statement.setInt(2, entity.getBuildYear());
            statement.setInt(3, entity.getFloorsCount());
            if (entity.getDeterioration() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, entity.getDeterioration());
            }

            statement.setString(5, entity.getHouseType().getName());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<AddrHouseEntity> noticesWithHouse(Connection connection) {
        String sql = "select h.*, n.address as notice_addr from realty.house h join parser.notice n on n.house_id = h.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return mappingMultipleResult(resultSet, AddrHouseEntity.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateHouseAddress(Connection connection, List<? extends HouseEntity> houseList) {
        String sql = "update realty.house set street = ?, house_num = ? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (HouseEntity houseEntity : houseList) {

                String houseNumFinal = (houseEntity.getHouseNum() != null) ? houseEntity.getHouseNum() : "-";

                statement.setString(1, houseEntity.getStreet());
                statement.setString(2, houseEntity.getHouseNum());
                statement.setLong(3, houseEntity.getId());

                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



}
