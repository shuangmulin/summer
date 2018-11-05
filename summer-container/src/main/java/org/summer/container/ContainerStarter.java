package org.summer.container;

import org.summer.container.scan.ClassResource;
import org.summer.container.scan.ClassResourceScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

/**
 * 容器启动器
 *
 * @author 钟宝林
 * @date 2018-11-05 16:18
 **/
public class ContainerStarter {

    public BeanContainer start(String packageName) {
        ClassResourceScanner classResourceScanner = new ClassResourceScanner();
        Set<ClassResource> classResources = classResourceScanner.scan(packageName);

        // 先注册BeanProcessor

        for (ClassResource classResource : classResources) {
            Class<?> clazz = classResource.getClazz();
            // 类所有注解
            Annotation[] classAnnotations = clazz.getAnnotations();

            // 字段所有注解
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                Annotation[] fieldAnnotations = field.getAnnotations();
            }

            // 构造函数所有注解
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor<?> constructor : constructors) {
                Annotation[] constructorAnnotations = constructor.getAnnotations();

                // 构造函数所有参数的所有注解
                Parameter[] parameters = constructor.getParameters();
                for (Parameter parameter : parameters) {
                    Annotation[] parameterAnnotations = parameter.getAnnotations();
                }
            }

            // 所有方法的所有注解
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                Annotation[] methodAnnotations = method.getAnnotations();

                // 所有方法的所有参数的所有注解
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    Annotation[] parameterAnnotations = parameter.getAnnotations();

                }
            }
        }
        return null;
    }

}
