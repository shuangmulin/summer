package org.summer.container;

import org.summer.container.annotation.Component;
import org.summer.container.scan.ClassResource;
import org.summer.container.scan.ClassResourceScanner;
import org.summer.core.utils.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 容器启动器
 *
 * @author 钟宝林
 * @date 2018-11-05 16:18
 **/
public class ContainerStarter {

    private BeanContainer container = BeanContainer.getInstance();

    public BeanContainer start(String packageName) throws IllegalAccessException, InstantiationException {
        ClassResourceScanner classResourceScanner = new ClassResourceScanner();
        Set<ClassResource> classResources = classResourceScanner.scan(packageName);

        // 先把Bean放入容器
        for (ClassResource classResource : classResources) {
            Class<?> clazz = classResource.getClazz();
            Component component = clazz.getAnnotation(Component.class);
            if (component != null) {
                String beanName = component.value();
                if (StringUtils.isBlank(beanName)) {
                    beanName = clazz.getSimpleName();
                }
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                beanDefinition.setBeanName(beanName);
                beanDefinition.setBean(clazz.newInstance());
                container.addBean(beanDefinition);
            }
        }

        // 默认Bean
        Set<BeanDefinition> defaultBeanDefinitions = DefaultBeanConfig.listDefaultBean();
        for (BeanDefinition defaultBeanDefinition : defaultBeanDefinitions) {
            container.addBean(defaultBeanDefinition);
        }

        // 处理Bean
        Collection<BeanDefinition> allBean = container.getAllBean();
        for (Object bean : allBean) {
            List<BeanProcessor> beanProcessors = BeanContainer.getInstance().getByType(BeanProcessor.class);
            for (BeanProcessor beanProcessor : beanProcessors) {
                BeanProcessContext context = new BeanProcessContext();
                context.setContainer(container);
                context.setBean(bean);
                beanProcessor.process(context);
            }
        }
        return container;
    }

}
