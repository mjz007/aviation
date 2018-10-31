package com.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class illegalChar {
    public static boolean isSpecialChar(String str) {
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5\\s]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
