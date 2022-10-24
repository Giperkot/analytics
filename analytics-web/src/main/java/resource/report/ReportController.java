package resource.report;

import core.rest.CheckRights;
import core.rest.RequestHelper;
import dto.report.RequestReportDto;
import service.report.ReportService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api/report")
public class ReportController {

    private ReportService reportService = ReportService.getInstance();

    @POST
    @Path("get-realty-report")
    @CheckRights(chechRights = false)
    public String getRealtyReport(RequestHelper requestHelper, RequestReportDto requestReportDto) {
        return reportService.generateReport(requestHelper, requestReportDto);
    }

}
