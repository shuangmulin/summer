package org.summer.container;

/**
 * Bean定义类
 *
 * @author 钟宝林
 * @date 2018-11-06 15:15
 **/
public class BeanDefinition {

    private final Class<?> clazz;
    private Object bean;
    private String beanName;
    private String className;

    public BeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
        this.className = clazz.getName();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }
}
