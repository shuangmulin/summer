package org.summer.container;

/**
 * 处理bean的上下文
 *
 * @author 钟宝林
 * @date 2018-11-06 13:24
 **/
public class BeanProcessContext {

    private Object bean;
    private String beanName;
    private BeanContainer container;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public BeanContainer getContainer() {
        return container;
    }

    public void setContainer(BeanContainer container) {
        this.container = container;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
