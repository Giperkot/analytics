package resource.parser;

import dao.parser.ParserDao;
import dao.realty.RealtyDao;
import enums.EDirectionName;
import service.avito.AvitoParserService;
import core.rest.CheckRights;
import core.rest.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.realty.RealtyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/avito")
public class AvitoParserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AvitoParserController.class);

    private final AvitoParserService avitoParserService = AvitoParserService.getInstance();

    private final RealtyService realtyService = RealtyService.getInstance();

    private final RealtyDao realtyDao = RealtyDao.getInstance();

    private final ParserDao parserDao = ParserDao.getInstance();

    @GET
    @Path("parse-notice-brief-list")
    @CheckRights(chechRights = false)
    public String parseNoticeBriefList(RequestHelper requestHelper) {
        try {
            // Нельзя отменять эту задачу, если запущена другая.
            while (true) {
                String res = avitoParserService.parseNoticeList(requestHelper);

                if (!"busy".equals(res)) {
                    break;
                }

                Thread.sleep(30_000);
            }

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

    @GET
    @Path("parse-details")
    @CheckRights(chechRights = false)
    public String parseDetails(RequestHelper requestHelper) {
        try {
            avitoParserService.parseDetails(requestHelper);

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

    @GET
    @Path("determineAddresses")
    @CheckRights(chechRights = false)
    public String determineAddresses(RequestHelper requestHelper) {
        try {
            realtyService.determineAddresses(requestHelper.getConnection(), EDirectionName.REALTY_SALE);

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

    @GET
    @Path("update-notice-details")
    @CheckRights(chechRights = false)
    public String updateNoticeDetails(RequestHelper requestHelper) {
        try {
            avitoParserService.updateNoticeDetails(requestHelper, EDirectionName.REALTY_SALE);

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

/*    @GET
    @Path("create-notice-actions")
    @CheckRights(chechRights = false)
    public String createNoticeActions(RequestHelper requestHelper) {
        try {
            Connection connection = requestHelper.getConnection();
            List<NoticeEntity> noticeEntityList = parserDao.getAllNotices(connection);

            for (int i = 0; i < noticeEntityList.size(); i++) {
                NoticeEntity noticeEntity = noticeEntityList.get(i);

                List<DiffAction> diffActionList = DiffUtils.getDiffList(null, noticeEntity);

                List<NoticeActionEntity> noticeActionEntityList = new ArrayList<>();
                for (DiffAction diffAction : diffActionList) {
                    NoticeActionEntity noticeActionEntity = new NoticeActionEntity(diffAction, noticeEntity.getId());
                    noticeActionEntityList.add(noticeActionEntity);
                }

                parserDao.updateNoticeHistory(connection, noticeActionEntityList);
            }

            connection.commit();

            *//*avitoParserService.updateNoticeDetails(requestHelper, EDirectionName.REALTY_SALE);*//*

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }*/

/*    @GET
    @Path("remapDistricts")
    @CheckRights(chechRights = false)
    public String remapDistricts(RequestHelper requestHelper) {
        try {
            List<DistrictEntity> districtEntityList = realtyDao.getAllDistricts(requestHelper.getConnection());

            ObjectMapper mapper = ProjectConst.MAPPER;

            for (DistrictEntity districtEntity : districtEntityList) {
                String coords = districtEntity.getCoords();

                TempPoint[] tempPointArr = mapper.readValue(coords, TempPoint[].class);

                CoordPoint[] coordPoints = new CoordPoint[tempPointArr.length];

                double south = tempPointArr[0].getLatitude(), north = tempPointArr[0].getLatitude(),
                        east = tempPointArr[0].getLongitude(), west = tempPointArr[0].getLongitude();

                double xSum = 0, ySum = 0;

                for (int i = 0; i < tempPointArr.length; i++) {
                    TempPoint tempPoint = tempPointArr[i];

                    coordPoints[i] = tempPoint.toCoordPoint();

                    xSum += tempPoint.getLongitude();
                    ySum += tempPoint.getLatitude();

                    if (east < coordPoints[i].getLongitude()) {
                        east = coordPoints[i].getLongitude();
                    }
                    if (west > coordPoints[i].getLongitude()) {
                        west = coordPoints[i].getLongitude();
                    }
                    if (south > coordPoints[i].getLatitude()) {
                        south = coordPoints[i].getLatitude();
                    }
                    if (north < coordPoints[i].getLatitude()) {
                        north = coordPoints[i].getLatitude();
                    }
                }

                CoordPoint lowerRight = new CoordPoint(east, south);
                CoordPoint upperLeft = new CoordPoint(west, north);

                DistrictCoords districtCoords = new DistrictCoords();
                districtCoords.setPoligonBounds(coordPoints);
                districtCoords.setCenter(new CoordPoint(xSum / coordPoints.length, ySum / coordPoints.length));
                districtCoords.setBound(new BoundsDto(lowerRight, upperLeft));

                String districctCoordsStr = mapper.writeValueAsString(districtCoords);

                realtyDao.saveDistrictCoords(requestHelper.getConnection(), districtEntity.getId(), districctCoordsStr);
            }

            requestHelper.getConnection().commit();

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }*/


}
