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

    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String str){
        if(Character.isLowerCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

}
