package com.albert.huabanlite.unit;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by alberthuang on 2017/3/13.
 */

public class RxBus {
    private RxBus() {
    }

    private static volatile RxBus instance;

    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object event) {
        bus.onNext(event);
    }

    public <T> Observable<T> toObserverable(final Class<T> eventType) {
        //  ofType = filter + cast
        return bus.ofType(eventType);
    }
}
