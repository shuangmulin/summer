package org.summer.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean容器
 *
 * @author 钟宝林
 * @date 2018-11-05 15:58
 **/
public class BeanContainer {

    private static volatile BeanContainer beanContainer;

    private Map<String, Object> beans = new ConcurrentHashMap<>();

    public static BeanContainer getInstance() {
        if (beanContainer != null) {
            return beanContainer;
        }

        synchronized (BeanContainer.class) {
            if (beanContainer == null) {
                beanContainer = new BeanContainer();
            }
        }
        return beanContainer;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getByType(Class<T> clazz) {
        List<T> returnBeans = new ArrayList<>();
        for (Object bean : beans.values()) {
            if (clazz.isAssignableFrom(bean.getClass())) {
                returnBeans.add((T) bean);
            }
        }
        return returnBeans;
    }

    public void addBean(BeanDefinition beanDefinition) {
        beans.put(beanDefinition.getBeanName(), beanDefinition.getBean());
    }

}
