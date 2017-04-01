package com.albert.huabanlite.mvp.presenter;

import android.content.Context;

import com.albert.huabanlite.Entity.ListPinsBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.api.RequestManager;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.albert.huabanlite.module.type.TypeFragment;
import com.albert.huabanlite.unit.Constant;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by alberthuang on 2017/3/13.
 */

public class TypePresenter extends BasePresenter<TypeFragment> {

    @Inject
    @ApplicationContext
    protected Context context;


    @Inject
    protected RequestManager requestManager;


    public void getTypePins(String type) {

        compositeSubscription.add(
                requestManager.getPinsByType(type, Constant.Http.LIMIT)
                        .subscribe(new Subscriber<ListPinsBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(ListPinsBean listPinsBean) {
                                view.setRefreshing(false);
                                view.hideProgress();
                                view.onGetTypePicture(listPinsBean.getPins());
                            }
                        })
        );
    }

    public void getTypePins(String type, int maxId) {

        compositeSubscription.add(
                requestManager.getPinsByType(type, maxId, Constant.Http.LIMIT)
                        .subscribe(new Subscriber<ListPinsBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(ListPinsBean listPinsBean) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.onGetTypePicture(listPinsBean.getPins());
                            }
                        })
        );
    }

}
