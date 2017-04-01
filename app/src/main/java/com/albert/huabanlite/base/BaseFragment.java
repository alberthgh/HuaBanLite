package com.albert.huabanlite.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.huabanlite.mvp.presenter.BasePresenter;
import com.albert.huabanlite.mvp.view.BaseView;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by alberthuang on 2017/2/27.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected View rootView;
    protected T presenter;

    protected CompositeSubscription subscription = new CompositeSubscription();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }


    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.onDestroy();
        }

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {

    }
}
