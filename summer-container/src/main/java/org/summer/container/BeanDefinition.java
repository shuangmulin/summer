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

    public BeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
        this.beanName = clazz.getSimpleName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanDefinition that = (BeanDefinition) o;

        return beanName != null ? beanName.equals(that.beanName) : that.beanName == null;
    }

    @Override
    public int hashCode() {
        return beanName != null ? beanName.hashCode() : 0;
    }
}
