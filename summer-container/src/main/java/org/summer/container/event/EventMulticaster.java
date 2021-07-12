package org.summer.container.event;

import org.summer.container.BeanContainer;
import org.summer.container.BeanDefinition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 事件多播器
 *
 * @author 钟宝林
 * @since 2021/7/12/012 19:17
 **/
@SuppressWarnings("ALL")
public class EventMulticaster {

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    public void multicastEvent(Event event) {
        // 拿到监听这个事件的事件监听器，并执行onEvent方法
        for (BeanDefinition beanDefinition : listEventListener()) {
            Class aClass = beanDefinition.getClazz();
            Type[] genericInterfaces = aClass.getGenericInterfaces();
            if (genericInterfaces.length <= 0) {
                continue;
            }
            Type actualTypeArgument = null;
            for (Type genericInterface : genericInterfaces) {
                if (genericInterface instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments();
                    if (actualTypeArguments == null || actualTypeArguments.length != 1) {
                        continue;
                    }
                    if (Event.class.isAssignableFrom((Class<?>) actualTypeArguments[0])) {
                        actualTypeArgument = actualTypeArguments[0];
                    }
                }
            }
            if (actualTypeArgument != null && event.getClass().isAssignableFrom((Class<?>) actualTypeArgument)) {
                ((EventListener) beanDefinition.getBean()).onEvent(event);
            }
        }
    }

    public List<BeanDefinition> listEventListener() {
        return BeanContainer.getInstance().getBeanDefinitionByType(EventListener.class);
    }

}
