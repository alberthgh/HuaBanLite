package com.albert.huabanlite.mvp.view;

import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.Entity.PinsMainEntity;

import java.util.List;

/**
 * Created by alberthuang on 2017/3/22.
 */

public interface ImageDetailView {
    void onGetRecommendPins(List<PinsMainEntity> pinsMainEntityList);
    void onGetPinsDetail(PinsDetailBean pinsDetailBean);
}
