package resource.realty;

import core.rest.RequestHelper;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import service.excelExport.ExcelExportService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@Path("/api/export")
public class ExportController {
    private final ExcelExportService excelExportService = ExcelExportService.getInstance();

    @POST
    @Path("exportExcel")
    public void exportExcel(RequestHelper requestHelper, List<ImportRealtyObjectEntity> entity) throws IOException {
        excelExportService.exportExcel(requestHelper, entity);
    }
}
