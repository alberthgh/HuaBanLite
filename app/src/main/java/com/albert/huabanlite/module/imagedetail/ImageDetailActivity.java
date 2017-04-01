package com.albert.huabanlite.module.imagedetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.BaseActivity;
import com.albert.huabanlite.unit.Utils;
import com.albert.huabanlite.unit.imageloading.ImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

import static com.albert.huabanlite.unit.Constant.Http.FORMAT_URL_IMAGE_BIG;
import static com.albert.huabanlite.unit.Constant.Http.FORMAT_URL_IMAGE_SMALL;

/**
 * Created by alberthuang on 2017/3/16.
 */

public class ImageDetailActivity extends BaseActivity {


    protected static final String PIN_ENTITY = "PIN_ENTITY";

    @BindView(R.id.image_detail_image)
    SimpleDraweeView imageDetailImage;
    @BindView(R.id.image_detail_toolbar)
    Toolbar imageDetailToolbar;
    @BindView(R.id.image_detail_collapsing)
    CollapsingToolbarLayout imageDetailCollapsing;
    @BindView(R.id.image_detail_appbarlayout)
    AppBarLayout imageDetailAppbarlayout;
    @BindView(R.id.image_detail_framelayout)
    FrameLayout imageDetailFramelayout;
    @BindView(R.id.image_detail_coordinator)
    CoordinatorLayout imageDetailCoordinator;

    private PinsMainEntity pinsMainEntity;
    private String imageUrl;
    private String imageType;
    private String pinId;

    public static void launch(Activity activity, PinsMainEntity pinsMainEntity) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(PIN_ENTITY, pinsMainEntity);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        pinsMainEntity = getIntent().getParcelableExtra(PIN_ENTITY);

        imageUrl = pinsMainEntity.getFile().getKey();
        imageType = pinsMainEntity.getFile().getType();
        String url = String.format(FORMAT_URL_IMAGE_BIG, imageUrl);
        String url_low = String.format(FORMAT_URL_IMAGE_SMALL, imageUrl);
        pinId = String.valueOf(pinsMainEntity.getPin_id());

        //设置图片空间的宽高比
        int width = pinsMainEntity.getFile().getWidth();
        int height = pinsMainEntity.getFile().getHeight();
        imageDetailImage.setAspectRatio(Utils.getAspectRatio(width, height));
//
        setActionBar(imageDetailToolbar);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);


        ImageLoader.loadImage(imageDetailImage, url);

        getFragmentManager().beginTransaction().replace(R.id.image_detail_framelayout, ImageDetailFragment.newInstance()).commit();

    }

}
