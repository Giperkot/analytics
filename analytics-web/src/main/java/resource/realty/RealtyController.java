package resource.realty;

import core.rest.RequestHelper;
import dto.common.SimpleResultDto;
import dto.realty.FullDistrictDto;
import dto.realty.NoticeInfoFilter;
import dto.realty.VNoticeInfoWithAvgPriceDto;
import dto.realty.excelimport.ImportResponseDto;
import dto.realty.excelimport.standart.EvalutionStandartObjDto;
import dto.realty.excelimport.standart.StandartRequestWrapper;
import dto.realty.standart.SelectedStandartObjectDto;
import service.realty.RealtyService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/api/realty")
public class RealtyController {

    private RealtyService realtyService = RealtyService.getInstance();

    @POST
    @Path("getNoticesByFilter")
    public List<VNoticeInfoWithAvgPriceDto> getNoticesByFilter(RequestHelper requestHelper, NoticeInfoFilter filter) {
        return realtyService.getNoticesByFilter(requestHelper, filter);
    }

    @POST
    @Path("saveDistrict")
    public SimpleResultDto saveDistrict(RequestHelper requestHelper, FullDistrictDto fullDistrictDto) {
        return realtyService.saveDistrict(requestHelper, fullDistrictDto);
    }

    @POST
    @Path("deleteDistrictById")
    public SimpleResultDto deleteDistrictById(RequestHelper requestHelper, @QueryParam("id") String districtId) {
        return realtyService.deleteDistrictById(requestHelper, Long.parseLong(districtId));
    }

    @POST
    @Path("selectStandartObjects")
    public List<EvalutionStandartObjDto> selectStandartObjects(RequestHelper requestHelper, SelectedStandartObjectDto dto) {
        return realtyService.selectStandartObjects(requestHelper, dto);
    }

    @POST
    @Path("calcResult")
    public ImportResponseDto calcResult(RequestHelper requestHelper, StandartRequestWrapper standartRequestWrapper) {
        return realtyService.calcResult(requestHelper, standartRequestWrapper);
    }


}
