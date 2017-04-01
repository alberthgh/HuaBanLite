package com.albert.huabanlite.mvp.presenter;

import android.content.Context;

import com.albert.huabanlite.Entity.UserBoardListBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.api.RequestManager;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.albert.huabanlite.module.user.UserBoardsFragment;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by alberthuang on 2017/3/20.
 */

public class UserBoardsPresenter extends BasePresenter<UserBoardsFragment>  {


    @Inject
    @ApplicationContext
    public Context context;


    @Inject
    public RequestManager requestManager;


    public void getUserBoards(String userId, int limit) {

        compositeSubscription.add(
                requestManager.getUserBoards(userId, limit)
                        .subscribe(new Subscriber<UserBoardListBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(UserBoardListBean userBoardListBean) {
                                view.setRefreshing(false);
                                view.hideProgress();
                                view.onGetUserBoards(userBoardListBean);
                            }
                        })
        );
    }


    public void getUserBoards(String userId, int max, int limit) {

        compositeSubscription.add(
                requestManager.getUserBoards(userId, max, limit)
                        .subscribe(new Subscriber<UserBoardListBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(UserBoardListBean userBoardListBean) {
                                view.setRefreshing(false);
                                view.hideProgress();
                                view.onGetUserBoards(userBoardListBean);
                            }
                        })
        );
    }

}
