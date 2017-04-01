package com.albert.huabanlite.module.user;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albert.huabanlite.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alberthuang on 2017/3/20.
 */

public class BoardsViewHolder extends RecyclerView.ViewHolder  {


    @BindView(R.id.img_card_image)
    SimpleDraweeView imgCardImage;
    @BindView(R.id.tv_board_title)
    TextView tvBoardTitle;
    @BindView(R.id.tv_board_gather)
    TextView tvBoardGather;
    @BindView(R.id.tv_board_attention)
    TextView tvBoardAttention;
    @BindView(R.id.relation_board_info)
    RelativeLayout relationBoardInfo;
    @BindView(R.id.framelayout_image)
    FrameLayout framelayoutImage;
    @BindView(R.id.item_user_board_title)
    TextView itemUserBoardTitle;
    @BindView(R.id.item_user_board_linear)
    LinearLayout itemUserBoardLinear;
    @BindView(R.id.item_user_board_card)
    CardView itemUserBoardCard;


    public BoardsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
