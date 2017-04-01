package com.albert.huabanlite.module.user;

import android.os.Bundle;

import com.albert.huabanlite.Entity.UserBoardItemBean;
import com.albert.huabanlite.Entity.UserBoardListBean;
import com.albert.huabanlite.base.coustomwidget.WaterfallFragment;
import com.albert.huabanlite.di.component.ApiComponent;
import com.albert.huabanlite.listener.OnBoardClickListener;
import com.albert.huabanlite.listener.OnLoadMoreListener;
import com.albert.huabanlite.listener.OnSwipeRefreshListener;
import com.albert.huabanlite.module.board.BoardDetailActivity;
import com.albert.huabanlite.mvp.presenter.UserBoardsPresenter;
import com.albert.huabanlite.mvp.view.UserBoardsView;
import com.albert.huabanlite.unit.Constant;

import java.util.List;

/**
 * Created by alberthuang on 2017/3/20.
 */

public class UserBoardsFragment extends WaterfallFragment implements UserBoardsView, OnBoardClickListener {


    private int maxId = Constant.Salad.DEFAULT_VALUE_MINUS_ONE;

    private UserBoardsPresenter userBoardsPresenter;
    private String userId;

    public static UserBoardsFragment newInstance(String userId){
        UserBoardsFragment userBoardsFragment = new UserBoardsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(UserActivity.USER_ID, userId);
        userBoardsFragment.setArguments(bundle);
        return userBoardsFragment;
    }

    @Override
    protected BoardsAdapter setAdapter() {
        return new BoardsAdapter(this, autoLoadRecycler);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        userId = getArguments().getString(UserActivity.USER_ID);

        userBoardsPresenter = new UserBoardsPresenter();
        userBoardsPresenter.attachView(this);
        userBoardsPresenter.onCreate();
        ApiComponent.Instance.get().inject(userBoardsPresenter);

        userBoardsPresenter.getUserBoards(userId, Constant.Http.LIMIT);
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
                userBoardsPresenter.getUserBoards(userId, maxId, Constant.Http.LIMIT);
            }
        };
    }

    @Override
    public void onGetUserBoards(UserBoardListBean userBoardListBean) {
        List<UserBoardItemBean> userBoardItemBeanList = userBoardListBean.getBoards();

        //没有更多数据，则不继续加载了
        if (userBoardItemBeanList == null || userBoardItemBeanList.isEmpty() || userBoardItemBeanList.size() < Constant.Http.LIMIT){
            isScrollListener = false;
        }

        adapter.addAll(userBoardItemBeanList);

        //保存maxId值 后续加载需要
        maxId = getMax(userBoardItemBeanList);
    }

    private int getMax(List<UserBoardItemBean> been) {
        return been.get(been.size() - 1).getBoard_id();
    }


    @Override
    public void onBoardClick(UserBoardItemBean userBoardItemBean) {
        BoardDetailActivity.launch(getActivity(), String.valueOf(userBoardItemBean.getBoard_id()), userBoardItemBean.getTitle());
    }
}
