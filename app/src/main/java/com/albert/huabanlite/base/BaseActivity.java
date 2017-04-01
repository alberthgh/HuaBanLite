package com.albert.huabanlite.base;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by alberthuang on 2017/2/25.
 */

public abstract class BaseActivity extends Activity {

    protected CompositeSubscription subscription = new CompositeSubscription();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
