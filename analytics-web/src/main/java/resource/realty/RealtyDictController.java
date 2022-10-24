package resource.realty;

import core.rest.CheckRights;
import core.rest.RequestHelper;
import dto.realty.CityDistrictDto;
import dto.realty.CityDto;
import dto.realty.CityRequestDto;
import dto.realty.DistrictDto;
import dto.realty.FullDistrictDto;
import service.realty.RealtyService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/api/dict/realty")
public class RealtyDictController {

    private RealtyService realtyService = RealtyService.getInstance();

    @GET
    @Path("getCityList")
    @CheckRights(chechRights = false)
    public List<CityDto> getCityList(RequestHelper requestHelper) {
        return realtyService.getCityList(requestHelper);
    }

    @POST
    @Path("getPrimaryDistricts")
    @CheckRights(chechRights = false)
    public List<DistrictDto> getPrimaryDistricts(RequestHelper requestHelper, CityRequestDto cityRequestDto) {
        return realtyService.getPrimaryDistricts(requestHelper, cityRequestDto);
    }

    @POST
    @Path("getMicroDistricts")
    @CheckRights(chechRights = false)
    public List<DistrictDto> getMicroDistricts(RequestHelper requestHelper, CityDistrictDto cityDto) {
        return realtyService.getMicroDistricts(requestHelper, cityDto);
    }

    @GET
    @Path("getDistrictById")
    @CheckRights(chechRights = false)
    public FullDistrictDto getDistrictById(RequestHelper requestHelper, @QueryParam("id") String id) {
        return realtyService.getDistrictById(requestHelper, id);
    }



}
