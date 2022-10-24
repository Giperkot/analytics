package utils;

import java.util.regex.Pattern;

public class StringUtils {

    private static Pattern notNumberPattern = Pattern.compile("^[0-9]+");

    public static String trimWhitespace(String source) {
        return source.replace('\u00A0',' ').trim();
    }

    private static boolean isItNumber(char ch) {
        return ch > 47 && ch < 58;
    }

    /**
     * Удаляет их строки всё кроме чисел.
     *
     * @param source
     * @return
     */
    public static String getOnlyNumbers(String source) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            if (isItNumber(ch)) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}
