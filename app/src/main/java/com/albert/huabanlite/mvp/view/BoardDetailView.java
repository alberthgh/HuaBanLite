package com.albert.huabanlite.mvp.view;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.Entity.ListPinsBean;

/**
 * Created by alberthuang on 2017/3/16.
 */

public interface BoardDetailView {
    void onGetBoardDetail(BoardDetailBean boardDetailBean);
    void onGetBoardPins(ListPinsBean listPinsBean);
}
