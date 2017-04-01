package com.albert.huabanlite.base.coustomwidget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


/**
 * Created by alberthuang on 2017/2/27.
 */

public abstract class WaterfallFragment extends AutoLoadRecyclerFragment {


    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

}
