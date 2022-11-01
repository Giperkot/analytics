package resource.realty;

import core.rest.RequestHelper;
import dto.common.SimpleResultDto;
import dto.realty.FullDistrictDto;
import dto.realty.NoticeInfoFilter;
import dto.realty.VNoticeInfoWithAvgPriceDto;
import service.excelReader.ExcelReaderService;
import service.realty.RealtyService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/api/import")
public class ImportController {
    private ExcelReaderService excelReaderService = ExcelReaderService.getInstance();

    @POST
    @Path("readExcel")
    public void readExcel(File excelFile) throws IOException {
        excelReaderService.readFromExcel(excelFile);
    }
}
