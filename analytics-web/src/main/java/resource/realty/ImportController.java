package resource.realty;

import service.excelReader.ExcelReaderService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;

@Path("/api/import")
public class ImportController {
    private ExcelReaderService excelReaderService = ExcelReaderService.getInstance();

    @POST
    @Path("readExcel")
    public void readExcel(File excelFile) throws IOException {
        excelReaderService.readFromExcel(excelFile);
    }
}
