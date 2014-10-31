package com.rmj.nidframe.util;

/**
 * Created by G11 on 2014/10/31.
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0 || cs.equals("null")) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从url下载地址中提取文件名
     * @param url
     * @return
     */
    public static String getFileNameFromUrl(String url) {
        String _name = "";
        _name = url.substring(url.lastIndexOf("/")+1);
        return _name;
    }

}
