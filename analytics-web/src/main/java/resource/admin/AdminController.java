package resource.admin;

import core.rest.CheckRights;
import core.rest.RequestHelper;
import dto.common.SimpleResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.realty.RealtyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/admin")
public class AdminController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private RealtyService realtyService = RealtyService.getInstance();

    @GET
    @Path("find-districs-houses")
    @CheckRights(chechRights = false)
    public String parseNoticeBriefList(RequestHelper requestHelper) {
        try {
            realtyService.findDistrictForHouses(requestHelper);

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

    @GET
    @Path("create-notice-index")
    @CheckRights(chechRights = false)
    public String createNoticeIndex(RequestHelper requestHelper) {
        try {
            realtyService.createOrUpdateAllNoticeIndex(requestHelper);

            return "success";
        } catch (Exception e) {
            LOGGER.error("There is error: ", e);

            return "fail";
        }
    }

    @GET
    @Path("fillHouseYearInfo")
    public SimpleResultDto fillHouseYearInfo(RequestHelper requestHelper) {
        return realtyService.fillHouseYearInfo(requestHelper);
    }

}
