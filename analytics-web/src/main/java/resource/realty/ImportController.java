package resource.realty;

import core.rest.RequestHelper;
import dto.realty.excelimport.ImportResponseDto;
import service.excelReader.ExcelReaderService;

import javax.servlet.http.Part;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@Path("/api/import")
public class ImportController {
    private final ExcelReaderService excelReaderService = ExcelReaderService.getInstance();

    @POST
    @Path("readExcel")
    public ImportResponseDto readExcel(RequestHelper requestHelper, List<Part> attachmentFiles) throws IOException {
        return excelReaderService.readFromExcel(requestHelper, attachmentFiles);
    }
}
