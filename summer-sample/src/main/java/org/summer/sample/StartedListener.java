package org.summer.sample;

import org.summer.container.annotation.Component;
import org.summer.container.event.EventListener;
import org.summer.container.event.impl.ContainerStartedEvent;

import java.io.Serializable;

/**
 * @author 钟宝林
 * @since 2021/7/12/012 19:46
 **/
@Component
public class StartedListener implements EventListener<ContainerStartedEvent>, Serializable {
    @Override
    public void onEvent(ContainerStartedEvent event) {
        System.out.println("启动完毕: " + event.getTimestamp());
    }
}
