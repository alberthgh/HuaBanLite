package com.albert.huabanlite.module.imagedetail;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.ArrayRecyclerAdapter;
import com.albert.huabanlite.base.coustomwidget.WaterfallFragment;
import com.albert.huabanlite.di.component.ApiComponent;
import com.albert.huabanlite.listener.OnGetImageDetailListener;
import com.albert.huabanlite.listener.OnLoadMoreListener;
import com.albert.huabanlite.listener.OnNormalHeaderClickListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.listener.OnSwipeRefreshListener;
import com.albert.huabanlite.module.board.BoardDetailActivity;
import com.albert.huabanlite.module.user.UserActivity;
import com.albert.huabanlite.mvp.presenter.ImageDetailPresenter;
import com.albert.huabanlite.mvp.view.ImageDetailView;
import com.albert.huabanlite.unit.Constant;

import java.util.List;

import static com.albert.huabanlite.module.imagedetail.ImageDetailActivity.PIN_ENTITY;

/**
 * Created by alberthuang on 2017/3/16.
 */

public class ImageDetailFragment extends WaterfallFragment implements ImageDetailView, OnPinClickListener, OnNormalHeaderClickListener {

    private ImageDetailPresenter imageDetailPresenter;
    private PinsMainEntity pinsMainEntity;
    private String pinId;
    private OnGetImageDetailListener onGetImageDetailListener;


    public static ImageDetailFragment newInstance() {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        return imageDetailFragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        imageDetailPresenter = new ImageDetailPresenter();
        imageDetailPresenter.attachView(this);
        imageDetailPresenter.onCreate();

        pinsMainEntity = getActivity().getIntent().getParcelableExtra(PIN_ENTITY);
        pinId = String.valueOf(pinsMainEntity.getPin_id());

        ApiComponent.Instance.get().inject(imageDetailPresenter);

        setRefreshing(true);
        imageDetailPresenter.getPinsDetail(pinId);


    }

    @Override
    protected ArrayRecyclerAdapter setAdapter() {
//        return new PinsAdapter(this);
        ImageDetailPinsAdapter imageDetailPinsAdapter = new ImageDetailPinsAdapter(this, autoLoadRecycler);
        imageDetailPinsAdapter.setHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.header_image_detail, null, false));
        onGetImageDetailListener = imageDetailPinsAdapter;
        imageDetailPinsAdapter.setOnNormalHeaderClickListener(this);
        return imageDetailPinsAdapter;
    }

    @Override
    protected OnSwipeRefreshListener setOnSwipeRefreshListener() {
        return null;
    }

    @Override
    protected boolean setSwipeRefreshEnable() {
        return false;
    }

    @Override
    protected OnLoadMoreListener setOnLoadMoreListener() {
        return new OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page) {
                imageDetailPresenter.getPinsRecommend(pinId, page);
            }
        };
    }

    @Override
    public void onGetRecommendPins(List<PinsMainEntity> pinsMainEntityList) {
        //没有更多数据，则不继续加载了
        if (pinsMainEntityList == null || pinsMainEntityList.isEmpty() || pinsMainEntityList.size() < Constant.Http.LIMIT){
            isScrollListener = false;
        }

        adapter.addAll(pinsMainEntityList);
    }

    @Override
    public void onGetPinsDetail(PinsDetailBean pinsDetailBean) {
        onGetImageDetailListener.onGetImageDetail(pinsDetailBean);

        imageDetailPresenter.getPinsRecommend(pinId, page);
    }

    @Override
    public void onPinClick(PinsMainEntity pinsMainEntity) {
        ImageDetailActivity.launch(getActivity(), pinsMainEntity);
    }

    @Override
    public void onUserClick(String userId, String userName) {
        UserActivity.launch(getActivity(), userId, userName);
    }

    @Override
    public void onBoardClick(String boardId, String boardTitle) {
        BoardDetailActivity.launch(getActivity(), boardId, boardTitle);
    }
}
