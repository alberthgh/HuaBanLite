package com.albert.huabanlite.module.board;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.BasePinsViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alberthuang on 2017/3/27.
 */

public class BoardDetailHeaderViewHolder extends BasePinsViewHolder {

    @BindView(R.id.img_board_user)
    SimpleDraweeView imgBoardUser;
    @BindView(R.id.tv_board_user)
    TextView tvBoardUser;
    @BindView(R.id.linearlayout_board_user)
    LinearLayout linearlayoutBoardUser;
    @BindView(R.id.tv_board_describe)
    TextView tvBoardDescribe;
    @BindView(R.id.tv_board_attention)
    TextView tvBoardAttention;
    @BindView(R.id.tv_board_gather)
    TextView tvBoardGather;
    @BindView(R.id.relativelayout_board_info)
    RelativeLayout relativelayoutBoardInfo;

    public BoardDetailHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
