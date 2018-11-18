package org.summer.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Field> listAllField(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                fields.add(declaredField);
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

}
