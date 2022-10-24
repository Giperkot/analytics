package service;

import core.rest.RequestHelper;
import dto.static_dto.CabinetDto;
import dto.static_dto.EnterDto;
import dto.static_dto.MainCabinetDto;
import dto.static_dto.MainPageInfoDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;

public class AnalyticsStaticService extends StaticService {

    private static AnalyticsStaticService INSTANCE;
    private static final int BUFFER_SIZE = 2048;

    private AnalyticsStaticService() {}

    public static AnalyticsStaticService getInstance () {
        if (INSTANCE == null) {
            synchronized (StaticService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AnalyticsStaticService();
                }
            }
        }

        return INSTANCE;
    }

    public void getPageByPath (RequestHelper requestHelper, String path) throws IOException {
        String[] pathParts = path.split("/");

        String appRoot = getAppRoot(requestHelper);
        HttpServletResponse response = requestHelper.getHttpServletResponse();

        String innerPage = "";

        MainPageInfoDto mainPageInfoDto = new MainPageInfoDto();

        // todo Доделать titles, metaTags
        mainPageInfoDto.setTitle("Аналитический сервис - analytics.stvolov.site");
        mainPageInfoDto.setKeywords("Аналитический сервис - analytics.stvolov.site");
        mainPageInfoDto.setDescription("Аналитический сервис - analytics.stvolov.site");

//        String cabinetRoot = servletContext.getResource("template/backend/pages").getPath().replaceAll("%20", " ");

        String primalPath = pathParts[0];
        String pageName = pathParts[0];
        StringBuilder jsCmpsContent = new StringBuilder();

        switch (pathParts[0]) {
            case "cabinet":
                pageName = "cabinet";
                mainPageInfoDto.setUserInfo(requestHelper.getUserInfo());
                break;
            case "enter":
                EnterDto enterDto = new EnterDto();
                enterDto.setRegister("success".equals(requestHelper.getHttpServletRequest().getParameter("register")));
                break;
            case "reports":
            case "realtySearch":
            case "analytics":
            // case "realty":
                pageName = "analytics";
                mainPageInfoDto.setUserInfo(requestHelper.getUserInfo());
                innerPage = getFilledTemplate (cabinetRoot + pageName + ".ftl", null);
                break;

            default:
                mainPageInfoDto.setUserInfo(requestHelper.getUserInfo());
                innerPage = getFilledTemplate (cabinetRoot + pathParts[0] + ".ftl", null);
                // 404
//                throw new RuntimeException("404");
        }

        // Добавление frontend информации
        String templateContent = getFileContentByPath(appRoot + "template/frontend/" + pageName + ".jstmpl");
//        String jsCmpsContent = getFileContentByPath(appRoot + "style/js/pagesCmp/" + pathParts[0] + ".js");
        jsCmpsContent.append("<script type=\"text/javascript\" src=\"/style/js/pagesCmp/").append(pageName).append(".js\"></script>");
        jsCmpsContent.append("<script type=\"text/javascript\" src=\"/style/js/scenario/").append(pageName).append(".js\"></script>");

        mainPageInfoDto.setInnerPage(innerPage);
        mainPageInfoDto.setJsTemplateList(templateContent);
        mainPageInfoDto.setScriptList(jsCmpsContent.toString());

        String filePath;
        // Выбор общего шаблона.
        switch (pathParts[0]) {
            case "enter":
            case "new_user":
            case "restorePassword":
                filePath = appRoot + "template/backend/authTemplate.ftl";
                break;
            default:
                filePath = appRoot + "template/backend/commonTemplate.ftl";
                break;
        }

        writeTemplateToResponse(filePath, mainPageInfoDto, new OutputStreamWriter(response.getOutputStream()));
    }


    public String getCabinetPage (String cabinetRoot, String subpage) {

        if (subpage == null) {
            subpage = "proceed";
        }

        MainCabinetDto mainCabinetDto = new MainCabinetDto();

        String cabOpt = getCabinetOption(cabinetRoot, subpage);
        mainCabinetDto.setCabinetOption(cabOpt);

        return getFilledTemplate (cabinetRoot + "cabinet.ftl", mainCabinetDto);
    }

    public String getCabinetOption (String cabinetRoot, String cabOption) {

        int level = 2;

        CabinetDto cabinetDto = new CabinetDto();
        cabinetDto.setLevel(level);

        String filePath = cabinetRoot + "cabb/" + cabOption + ".ftl";

        return getFilledTemplate (filePath, cabinetDto);
    }

    public String getTheoryPage (String pagesRoot) {

        // Получить список уровней.



        return null;
    }

    public String getPracticePage (String pagesRoot) {
        return null;
    }



}
