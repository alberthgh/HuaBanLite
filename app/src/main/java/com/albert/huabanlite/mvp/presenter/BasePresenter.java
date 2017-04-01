package com.albert.huabanlite.mvp.presenter;

import android.support.annotation.NonNull;

import com.albert.huabanlite.mvp.view.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by alberthuang on 2017/3/13.
 */

public class BasePresenter<T extends BaseView> {

    protected T view;
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void onCreate() {

    }

    public void attachView(@NonNull BaseView view) {
        this.view = (T) view;
    }


    public void onDestroy(){
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}
