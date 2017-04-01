package com.albert.huabanlite.base.coustomwidget;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.BaseFragment;
import com.albert.huabanlite.listener.OnLoadMoreListener;
import com.albert.huabanlite.listener.OnSwipeRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by alberthuang on 2017/3/14.
 * <p>
 * 提供下拉刷新，上拉自动加载功能的recycler
 */

public abstract class AutoLoadRecyclerFragment<T extends ArrayRecyclerAdapter> extends BaseFragment {



    @BindView(R.id.auto_load_recycler)
    protected RecyclerView autoLoadRecycler;
    @BindView(R.id.auto_load_swipe_refresh)
    protected SwipeRefreshLayout autoLoadSwipeRefresh;

    protected RecyclerView.LayoutManager layoutManager;


    private static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    protected final float percentageScroll = 0.8f;//滑动距离的百分比
    //是否还监听滑动的联网 标志位 默认为true 表示需要监听
    protected boolean isScrollListener = true;

    protected T adapter;

    //实际上本项目只有某些页面用到page
    protected int page = 1;

    private OnSwipeRefreshListener onSwipeRefreshListener;
    private OnLoadMoreListener onLoadMoreListener;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_auto_load_recycler;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        layoutManager = setLayoutManager();
        autoLoadRecycler.setHasFixedSize(false);
        autoLoadRecycler.setLayoutManager(layoutManager);
        autoLoadRecycler.setItemAnimator(new DefaultItemAnimator());

        adapter = setAdapter();
        autoLoadRecycler.setAdapter(adapter);

        onSwipeRefreshListener = setOnSwipeRefreshListener();
        onLoadMoreListener = setOnLoadMoreListener();



        autoLoadSwipeRefresh.setEnabled(setSwipeRefreshEnable());
        autoLoadSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                onSwipeRefreshListener.onSwipeRefresh();
            }
        });

//        autoLoadRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView rv, int dx, int dy) {
//                //项目中暂时只用到StaggeredGridLayoutManager
//                if (layoutManager instanceof StaggeredGridLayoutManager) {
//                    boolean isBottom = ((StaggeredGridLayoutManager) layoutManager)
//                            .findLastCompletelyVisibleItemPositions(new int[2])[1] >= adapter.getItemCount() - PRELOAD_SIZE;
//                    if (!autoLoadSwipeRefresh.isRefreshing() && isBottom) {
//                        if (!mIsFirstTimeTouchBottom) {
//                            page += 1;
//                            onLoadMoreListener.onLoadMore(page);
//                        } else {
//                            mIsFirstTimeTouchBottom = false;
//                        }
//                    }
//                }
//            }
//        });

        autoLoadRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
//                    Logger.d("滑动停止 position=" + mAdapter.getAdapterPosition());
                    int size = (int) (adapter.getItemCount() * percentageScroll);
                    if (adapter.getAdapterPosition() >= --size && isScrollListener) {
                        onLoadMoreListener.onLoadMore(page);
                    }
                } else if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //用户正在滑动
//                    Logger.d("用户正在滑动 position=" + mAdapter.getAdapterPosition());
                } else {
                    //惯性滑动
//                    Logger.d("惯性滑动 position=" + mAdapter.getAdapterPosition());
                }
            }
        });
    }

    /**
     * 从返回联网结果中保存max值 用于下次联网的关键
     *
     * @param result
     * @return
     */
    protected int refreshMaxId(List<PinsMainEntity> result) {
        return result.get(result.size() - 1).getPin_id();
    }


    protected abstract T setAdapter(); //这里初始化 adapter


    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract OnSwipeRefreshListener setOnSwipeRefreshListener();

    protected abstract boolean setSwipeRefreshEnable();

    protected abstract OnLoadMoreListener setOnLoadMoreListener();

    public boolean isRefreshing() {
        return autoLoadSwipeRefresh.isRefreshing();
    }

    public void setRefreshing(boolean refreshing) {
        autoLoadSwipeRefresh.setRefreshing(refreshing);
    }
}
