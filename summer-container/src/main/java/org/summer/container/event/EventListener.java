package org.summer.container.event;

@FunctionalInterface
public interface EventListener<E extends Event> {

    /**
     * 处理一个事件
     *
     * @param event 触发的事件
     */
    void onEvent(E event);

}
