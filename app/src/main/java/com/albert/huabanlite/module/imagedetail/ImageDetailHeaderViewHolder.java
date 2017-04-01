package com.albert.huabanlite.module.imagedetail;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.BasePinsViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alberthuang on 2017/3/23.
 */

public class ImageDetailHeaderViewHolder extends BasePinsViewHolder {

    @BindView(R.id.tv_image_text)
    public TextView tvImageText;
    @BindView(R.id.relativelayout_image_describe)
    public RelativeLayout relativelayoutImageDescribe;
    @BindView(R.id.img_image_user)
    public SimpleDraweeView imgImageUser;
    @BindView(R.id.tv_image_user)
    public TextView tvImageUser;
    @BindView(R.id.tv_image_about)
    public TextView tvImageAbout;
    @BindView(R.id.ibtn_image_user_chevron_right)
    public ImageButton ibtnImageUserChevronRight;
    @BindView(R.id.relativelayout_image_user)
    public RelativeLayout relativelayoutImageUser;
    @BindView(R.id.img_image_board)
    public SimpleDraweeView imgImageBoard;
    @BindView(R.id.tv_image_board)
    public TextView tvImageBoard;
    @BindView(R.id.tv_image_board_title)
    public TextView tvImageBoardTitle;
    @BindView(R.id.ibtn_image_board_chevron_right)
    public ImageButton ibtnImageBoardChevronRight;
    @BindView(R.id.relativelayout_image_board)
    public RelativeLayout relativelayoutImageBoard;

    public ImageDetailHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
