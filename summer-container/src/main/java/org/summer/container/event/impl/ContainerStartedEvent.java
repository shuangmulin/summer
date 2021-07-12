package org.summer.container.event.impl;

import org.summer.container.event.Event;

/**
 * 容器启动完毕事件
 *
 * @author 钟宝林
 * @since 2021/7/12/012 19:42
 **/
public class ContainerStartedEvent extends Event {
    /**
     * 创建一个事件
     *
     * @param source 初始化这个事件的对象，或者和这个事件有关系的对象
     */
    public ContainerStartedEvent(Object source) {
        super(source);
    }
}
