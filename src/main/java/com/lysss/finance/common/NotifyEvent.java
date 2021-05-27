package com.lysss.finance.common;

import org.springframework.context.ApplicationEvent;

public class NotifyEvent<T> extends ApplicationEvent {
    private T data;

//    public NotifyEvent(T source) {
//        super(source);
//        this.data = source;
//    }
    public NotifyEvent(String source,T data) {
        super(source);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
