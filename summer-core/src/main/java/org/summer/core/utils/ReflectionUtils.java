package org.summer.core.utils;

import java.lang.annotation.Annotation;

/**
 * 反射有关工具类
 *
 * @author 钟宝林
 * @date 2018-11-06 20:11
 **/
public class ReflectionUtils {

    public static boolean hasAnnotation(Class<?> clazz, Class<?> testAnnotation) {
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(testAnnotation)) {
                return true;
            }
        }
        return false;
    }

}
