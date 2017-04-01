package com.albert.huabanlite.mvp.presenter;

import android.content.Context;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.Entity.ListPinsBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.api.RequestManager;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.albert.huabanlite.module.board.BoardDetailFragment;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by alberthuang on 2017/3/16.
 */

public class BoardDetailPresenter extends BasePresenter<BoardDetailFragment> {

    @Inject
    @ApplicationContext
    Context context;


    @Inject
    RequestManager requestManager;


    public void getBoardDetailInfo(String boardId) {

        compositeSubscription.add(
                requestManager.getBoardDetailInfo(boardId)
                        .subscribe(new Subscriber<BoardDetailBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(BoardDetailBean boardDetailBean) {
                                view.hideProgress();
                                view.onGetBoardDetail(boardDetailBean);
                            }
                        })
        );
    }

    public void getBoardDetailPins(String boardId, int limit) {

        compositeSubscription.add(
                requestManager.getBoardDetailPins(boardId, limit)
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
                                view.onGetBoardPins(listPinsBean);
                            }
                        })
        );
    }
    public void getBoardDetailPins(String boardId, int max, int limit) {

        compositeSubscription.add(
                requestManager.getBoardDetailPins(boardId, max, limit)
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
                                view.onGetBoardPins(listPinsBean);
                            }
                        })
        );
    }
}