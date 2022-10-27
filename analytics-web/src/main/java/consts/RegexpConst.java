package consts;

import java.util.regex.Pattern;

public class RegexpConst {

    public static final Pattern resultPattern = Pattern.compile("^.*RESULT\\[(.*?)\\]\\. (.*)$");

}
