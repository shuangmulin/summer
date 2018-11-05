package org.summer.core.utils;

/**
 * 字符串相关工具
 *
 * @author 钟宝林
 * @date 2018-11-05 10:52
 **/
public final class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
