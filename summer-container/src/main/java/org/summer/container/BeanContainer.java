package org.summer.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean容器
 *
 * @author 钟宝林
 * @date 2018-11-05 15:58
 **/
public class BeanContainer {

    private static volatile BeanContainer beanContainer;

    private Map<String, BeanDefinition> beans = new ConcurrentHashMap<>();

    private List<BeanMethodInterceptor> beanMethodInterceptors;

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
        for (BeanDefinition bean : beans.values()) {
            if (clazz.isAssignableFrom(bean.getClazz())) {
                returnBeans.add((T) bean);
            }
        }
        return returnBeans;
    }

    public void addBean(BeanDefinition beanDefinition) {
        beans.put(beanDefinition.getBeanName(), beanDefinition);
    }

    public Collection<BeanDefinition> getAllBean() {
        return beans.values();
    }

    public List<BeanMethodInterceptor> getBeanMethodInterceptors() {
        if (beanMethodInterceptors != null) {
            return beanMethodInterceptors;
        }

        beanMethodInterceptors = getByType(BeanMethodInterceptor.class);
        beanMethodInterceptors.sort(Comparator.comparingInt(Order::order));
        return beanMethodInterceptors;
    }
}
