package org.summer.container;

import org.summer.container.event.EventMulticaster;

import java.util.HashSet;
import java.util.Set;

/**
 * 框架默认bean配置
 *
 * @author 钟宝林
 * @date 2018-11-06 20:28
 **/
public final class DefaultBeanConfig {

    private static Class<?>[] defaultBeanClasses = new Class[]{
            AutowiredBeanProcessor.class,
            ResourceBeanProcessor.class,
            EventMulticaster.class
    };

    public static Set<BeanDefinition> listDefaultBean() {
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        for (Class<?> defaultBeanClass : defaultBeanClasses) {
            beanDefinitions.add(new BeanDefinition(defaultBeanClass));
        }
        return beanDefinitions;
    }

}
