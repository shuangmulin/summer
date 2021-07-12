package org.summer.container.event;

import java.util.EventObject;

public abstract class Event extends EventObject {
    private static final long serialVersionUID = 7099057701183571937L;

    /**
     * System time when the event happened.
     */
    private final long timestamp;

    /**
     * 创建一个事件
     *
     * @param source 初始化这个事件的对象，或者和这个事件有关系的对象
     */
    public Event(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }


    /**
     * Return the system time in milliseconds when the event occurred.
     */
    public final long getTimestamp() {
        return this.timestamp;
    }

}
