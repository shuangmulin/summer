package org.summer.container;

/**
 * 处理bean的上下文
 *
 * @author 钟宝林
 * @date 2018-11-06 13:24
 **/
public class BeanProcessContext {

    private BeanDefinition beanDefinition;
    private BeanContainer container;

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public void setBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

    public BeanContainer getContainer() {
        return container;
    }

    public void setContainer(BeanContainer container) {
        this.container = container;
    }
}
