package com.albert.huabanlite.mvp.presenter;

import android.content.Context;

import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.api.RequestManager;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.albert.huabanlite.module.imagedetail.ImageDetailFragment;
import com.albert.huabanlite.unit.Constant;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by alberthuang on 2017/3/22.
 */

public class ImageDetailPresenter extends BasePresenter<ImageDetailFragment> {


    @Inject
    @ApplicationContext
    protected Context context;


    @Inject
    protected RequestManager requestManager;


    public void getPinsDetail(String pinsId) {

        compositeSubscription.add(
                requestManager.getPinsDetail(pinsId)
                        .subscribe(new Subscriber<PinsDetailBean>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(PinsDetailBean pinsDetailBean) {
                                view.hideProgress();
                                view.onGetPinsDetail(pinsDetailBean);
                            }
                        })
        );
    }



    public void getPinsRecommend(String pinsId, int page) {

        compositeSubscription.add(
                requestManager.getPinsRecommend(pinsId, page, Constant.Http.LIMIT)
                        .subscribe(new Subscriber<List<PinsMainEntity>>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.setRefreshing(false);
                                view.showMsg(context.getString(R.string.http_request_fail));
                            }

                            @Override
                            public void onNext(List<PinsMainEntity> pinsMainEntityList) {
                                view.setRefreshing(false);
                                view.hideProgress();
                                view.onGetRecommendPins(pinsMainEntityList);
                            }
                        })
        );
    }


}
