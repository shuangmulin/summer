package org.summer.container;

import org.summer.core.utils.StringUtils;

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
        for (BeanDefinition beanDefinition : beans.values()) {
            if (clazz.isAssignableFrom(beanDefinition.getClazz())) {
                returnBeans.add((T) beanDefinition.getBean());
            }
        }
        return returnBeans;
    }

    public void addBean(BeanDefinition beanDefinition) {
        String beanName = beanDefinition.getBeanName();
        if (StringUtils.isBlank(beanName)) {
            beanName = StringUtils.toLowerCaseFirstOne(beanDefinition.getClazz().getSimpleName());
            beanDefinition.setBeanName(beanName);
        }
        Object bean = getBeanByName(beanName);
        if (bean != null) {
            throw new IllegalArgumentException(beanName + "已存在，容器启动失败");
        }
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

    public Object getBeanByName(String beanName) {
        if (StringUtils.isBlank(beanName)) {
            throw new IllegalStateException("未知beanName");
        }
        BeanDefinition beanDefinition = beans.get(beanName);
        if (beanDefinition != null) {
            return beanDefinition.getBean();
        }
        return null;
    }

    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        if (type == null) {
            return null;
        }

        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, BeanDefinition> entry : beans.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            Class<?> clazz = beanDefinition.getClazz();
            if (type.isAssignableFrom(clazz)) {
                result.put(beanName, (T) beanDefinition.getBean());
            }
        }
        return result;
    }

    public void registerSingleton(String beanName, Object bean) {
        BeanDefinition beanDefinition = new BeanDefinition(bean.getClass());
        beanDefinition.setBeanName(beanName);
        beanDefinition.setBean(bean);
        addBean(beanDefinition);
    }
}
