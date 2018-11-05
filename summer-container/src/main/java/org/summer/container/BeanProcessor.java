package org.summer.container;

/**
 * Bean加工者
 *
 * @author 钟宝林
 * @date 2018-11-05 17:49
 **/
public interface BeanProcessor {

    Object beforeInitialization(Object bean, BeanContainer container);

    Object afterInitialization(Object bean, BeanContainer container);

}
