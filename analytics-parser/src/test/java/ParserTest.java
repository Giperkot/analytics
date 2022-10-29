import db.entity.parser.ParseTaskEntity;
import dto.parser.NoticeEntityWrapper;
import service.avito.service.AvitoParser;
import utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParserTest {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException, ClassNotFoundException {
        AvitoParser avitoParser = AvitoParser.getInstance();

        /*Set<String> strSet = Set.of("1234");

        System.out.println(strSet.contains(null));;*/

        Map<String, String> headerMap = avitoParser.initHeaders();
        ParseTaskEntity parseTaskEntity = new ParseTaskEntity();
        // parseTaskEntity.setUrl("https://www.avito.ru/perm/kvartiry/1-k._kvartira_35m_110et._2455378873");
        // parseTaskEntity.setUrl("https://www.avito.ru/perm/kvartiry/1-k._kvartira_316m_25et._2500563396");
        parseTaskEntity.setUrl("/moskva/kvartiry/2-k._kvartira_602m_48et._2564887698");

        NoticeEntityWrapper noticeEntityWrapper = avitoParser.parseDetailNotice(headerMap, parseTaskEntity);

        int i = 0;


        // initRequestTest();

        // trimWhitespace("3-к. квартира, 67,4 м², 4/9 эт.");

        /*ParserTest parserTest = new ParserTest();

        parserTest.scanClasses("resource");*/

    }

    private static void trimWhitespace(String str) {
        System.out.println(str);

        System.out.println(StringUtils.trimWhitespace(str));

    }

    public static void initRequestTest() throws InterruptedException, IOException, URISyntaxException {
        AvitoParser avitoParser = AvitoParser.getInstance();

        Map<String, String> headerMap = avitoParser.getHeaders();

        avitoParser.initRequest(headerMap);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }


    private List<Class> scanClasses (String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/').replaceAll("%20", " ");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getPath().replaceAll("%20", " ")));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

}
