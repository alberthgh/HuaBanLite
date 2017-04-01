package com.albert.huabanlite.base.coustomwidget;

import android.view.View;
import android.widget.RelativeLayout;

import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.BasePinsViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alberthuang on 2017/2/27.
 */

public class PinsViewHolder extends BasePinsViewHolder {
    @BindView(R.id.picture_snapshot_image)
    SimpleDraweeView pictureSnapshotImage;
//    @BindView(R.id.picture_snapshot_description)
//    TextView pictureSnapshotDescription;
    @BindView(R.id.picture_snapshot_layout)
    RelativeLayout pictureSnapshotLayout;


    public PinsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
