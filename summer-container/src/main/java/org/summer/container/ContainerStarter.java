package org.summer.container;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.proxy.Enhancer;
import org.summer.container.annotation.Component;
import org.summer.container.event.EventMulticaster;
import org.summer.container.event.impl.ContainerStartedEvent;
import org.summer.container.scan.ClassResource;
import org.summer.container.scan.ClassResourceScanner;

import java.util.*;

/**
 * 容器启动器
 *
 * @author 钟宝林
 * @date 2018-11-05 16:18
 **/
public class ContainerStarter {

    private final BeanContainer container = BeanContainer.getInstance();

    public BeanContainer start(String packageName) {
        Collection<BeanDefinition> existsBeans = container.getAllBean();
        Collection<BeanDefinition> newExistsBeans = new ArrayList<>(existsBeans);

        ClassResourceScanner classResourceScanner = new ClassResourceScanner();
        Set<ClassResource> classResources = classResourceScanner.scan(packageName);

        // 把容器自己放进去
        BeanDefinition beanContainerDefinition = new BeanDefinition(BeanContainer.class);
        beanContainerDefinition.setBeanName("beanContainer");
        container.addBean(beanContainerDefinition);

        // 把Bean放入容器
        for (ClassResource classResource : classResources) {
            Class<?> clazz = classResource.getClazz();
            Component component = clazz.getAnnotation(Component.class);
            if (component != null) {
                String beanName = component.value();
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                beanDefinition.setBeanName(beanName);
                container.addBean(beanDefinition);
            }
        }

        // 默认Bean
        Set<BeanDefinition> defaultBeanDefinitions = DefaultBeanConfig.listDefaultBean();
        for (BeanDefinition defaultBeanDefinition : defaultBeanDefinitions) {
            container.addBean(defaultBeanDefinition);
        }

        // 代理bean
        Collection<BeanDefinition> allBean = container.getAllBean();
        for (BeanDefinition beanDefinition : allBean) {
            if (newExistsBeans.contains(beanDefinition)) {
                // 已经存在的bean不重复处理
                continue;
            }
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanDefinition.getClazz());
            enhancer.setCallback(new BeanProxy());
            enhancer.setNamingPolicy(new DefaultNamingPolicy() {
                public String getTag() {
                    return "BySummer";
                }
                public String toString() {
                    return getTag();
                }
            });
            beanDefinition.setBean(enhancer.create());
        }

        // 处理bean
        for (BeanDefinition beanDefinition : allBean) {
            List<BeanProcessor> beanProcessors = BeanContainer.getInstance().getByType(BeanProcessor.class);
            for (BeanProcessor beanProcessor : beanProcessors) {
                BeanProcessContext context = new BeanProcessContext();
                context.setContainer(container);
                context.setBean(beanDefinition.getBean());
                beanProcessor.process(context);
            }
        }

        container.publishEvent(new ContainerStartedEvent(this)); // 发布容器启动完毕事件

        return container;
    }

}
