package com.albert.huabanlite.module.type;

import android.os.Bundle;

import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.Entity.event.SwitchTypeEvent;
import com.albert.huabanlite.base.coustomwidget.PinsAdapter;
import com.albert.huabanlite.base.coustomwidget.WaterfallFragment;
import com.albert.huabanlite.di.component.ApiComponent;
import com.albert.huabanlite.listener.OnLoadMoreListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.listener.OnSwipeRefreshListener;
import com.albert.huabanlite.module.imagedetail.ImageDetailActivity;
import com.albert.huabanlite.mvp.presenter.TypePresenter;
import com.albert.huabanlite.mvp.view.TypeView;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.RxBus;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by alberthuang on 2017/2/27.
 */

public class TypeFragment extends WaterfallFragment implements TypeView, OnPinClickListener {


    private TypePresenter typePresenter;

    private int maxId = Constant.Salad.DEFAULT_VALUE_MINUS_ONE;


    private String type = Constant.Type.ALL;

    public static TypeFragment newInstance(){
        TypeFragment typeFragment = new TypeFragment();
        return typeFragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        typePresenter = new TypePresenter();
        typePresenter.attachView(this);
        typePresenter.onCreate();
        ApiComponent.Instance.get().inject(typePresenter);

        addRxBusEvent();

        setRefreshing(true);
        typePresenter.getTypePins(type);
    }

    @Override
    protected PinsAdapter setAdapter() {
        PinsAdapter pinsAdapter = new PinsAdapter(this, autoLoadRecycler);
        return pinsAdapter;
    }


    private void addRxBusEvent(){

        subscription.add(RxBus.getDefault().toObserverable(SwitchTypeEvent.class)
                .subscribe(new Action1<SwitchTypeEvent>() {
                    @Override
                    public void call(SwitchTypeEvent switchTypeEvent) {
                        type = switchTypeEvent.getType();
                        refreshData();
                    }
                }));
    }

    @Override
    public void onGetTypePicture(List<PinsMainEntity> pinsMainEntityList) {

        //没有更多数据，则不继续加载了
        if (pinsMainEntityList == null || pinsMainEntityList.isEmpty() || pinsMainEntityList.size() < Constant.Http.LIMIT){
            isScrollListener = false;
        }

        if (maxId == Constant.Salad.DEFAULT_VALUE_MINUS_ONE){
            adapter.clear();
        }
        adapter.addAll(pinsMainEntityList);

        //保存maxId值 后续加载需要
        maxId = refreshMaxId(pinsMainEntityList);

    }


    private void refreshData(){
        maxId = Constant.Salad.DEFAULT_VALUE_MINUS_ONE;
        setRefreshing(true);
        typePresenter.getTypePins(type);
    }


    @Override
    protected boolean setSwipeRefreshEnable() {
        return true;
    }

    @Override
    protected OnSwipeRefreshListener setOnSwipeRefreshListener() {
        return new OnSwipeRefreshListener() {
            @Override
            public void onSwipeRefresh() {
                refreshData();
            }
        };
    }

    @Override
    protected OnLoadMoreListener setOnLoadMoreListener() {
        return new OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page) {
                setRefreshing(true);
                typePresenter.getTypePins(type, maxId);
            }
        };
    }


    @Override
    public void onPinClick(PinsMainEntity pinsMainEntity) {
        ImageDetailActivity.launch(getActivity(), pinsMainEntity);
    }
}
