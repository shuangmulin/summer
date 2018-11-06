package org.summer.container;

import org.summer.container.scan.ResourceBeanProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * 默认bean处理器定义
 *
 * @author 钟宝林
 * @date 2018-11-06 14:07
 **/
public final class DefaultBeanProcessorDefinition {

    public static Set<BeanProcessor> listDefaultBeanProcessor() {
        Set<BeanProcessor> beanProcessors = new HashSet<>();
        beanProcessors.add(new AutowiredBeanProcessor());
        beanProcessors.add(new ResourceBeanProcessor());
        return beanProcessors;
    }

}
