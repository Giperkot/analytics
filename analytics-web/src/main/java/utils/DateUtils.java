package utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static DateTimeFormatter ISO_WITHOUT_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static DateTimeFormatter JS_WITH_H = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
}
