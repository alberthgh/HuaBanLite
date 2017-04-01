package com.albert.huabanlite.module.board;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.Entity.ListPinsBean;
import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.PinsAdapter;
import com.albert.huabanlite.base.coustomwidget.WaterfallFragment;
import com.albert.huabanlite.di.component.ApiComponent;
import com.albert.huabanlite.listener.OnGetBoardDetailListener;
import com.albert.huabanlite.listener.OnLoadMoreListener;
import com.albert.huabanlite.listener.OnNormalHeaderClickListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.listener.OnSwipeRefreshListener;
import com.albert.huabanlite.module.imagedetail.ImageDetailActivity;
import com.albert.huabanlite.module.user.UserActivity;
import com.albert.huabanlite.mvp.presenter.BoardDetailPresenter;
import com.albert.huabanlite.mvp.view.BoardDetailView;
import com.albert.huabanlite.unit.Constant;

import java.util.List;

/**
 * Created by alberthuang on 2017/3/16.
 */

public class BoardDetailFragment extends WaterfallFragment implements BoardDetailView, OnPinClickListener, OnNormalHeaderClickListener {

    private int maxId = Constant.Salad.DEFAULT_VALUE_MINUS_ONE;

    private OnGetBoardDetailListener onGetBoardDetailListener;
    private BoardDetailPresenter boardDetailPresenter;
    private String boardId;

    public static BoardDetailFragment newInstance(String boardId) {
        BoardDetailFragment boardDetailFragment = new BoardDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BoardDetailActivity.BOARD_ID, boardId);
        boardDetailFragment.setArguments(bundle);
        return boardDetailFragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

        boardId = getArguments().getString(BoardDetailActivity.BOARD_ID);

        boardDetailPresenter = new BoardDetailPresenter();
        boardDetailPresenter.attachView(this);
        boardDetailPresenter.onCreate();
        ApiComponent.Instance.get().inject(boardDetailPresenter);

        setRefreshing(true);
        boardDetailPresenter.getBoardDetailInfo(boardId);

    }

    @Override
    protected PinsAdapter setAdapter() {
        BoardDetailPinsAdapter boardDetailPinsAdapter = new BoardDetailPinsAdapter(this, autoLoadRecycler);
        boardDetailPinsAdapter.setHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.header_board_detail, null, false));
        onGetBoardDetailListener = boardDetailPinsAdapter;
        boardDetailPinsAdapter.setOnNormalHeaderClickListener(this);
        return boardDetailPinsAdapter;
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
                boardDetailPresenter.getBoardDetailPins(boardId, maxId, Constant.Http.LIMIT);
            }
        };
    }

    @Override
    public void onGetBoardDetail(BoardDetailBean boardDetailBean) {
        onGetBoardDetailListener.onGetBoardDetail(boardDetailBean);

        boardDetailPresenter.getBoardDetailPins(boardId, Constant.Http.LIMIT);
    }

    @Override
    public void onGetBoardPins(ListPinsBean listPinsBean) {
        List<PinsMainEntity> pinsMainEntityList = listPinsBean.getPins();
        //没有更多数据，则不继续加载了
        if (pinsMainEntityList == null || pinsMainEntityList.isEmpty() || pinsMainEntityList.size() < Constant.Http.LIMIT){
            isScrollListener = false;
        }

        //保存maxId值 后续加载需要
        maxId = refreshMaxId(listPinsBean.getPins());

        adapter.addAll(listPinsBean.getPins());
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

    }
}
