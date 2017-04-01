package com.albert.huabanlite.module.board;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.BasePinsViewHolder;
import com.albert.huabanlite.base.coustomwidget.PinsAdapter;
import com.albert.huabanlite.base.coustomwidget.PinsViewHolder;
import com.albert.huabanlite.listener.OnGetBoardDetailListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.imageloading.ImageLoader;

/**
 * Created by alberthuang on 2017/3/27.
 */

public class BoardDetailPinsAdapter extends PinsAdapter implements OnGetBoardDetailListener {

    private BoardDetailHeaderViewHolder boardDetailHeaderViewHolder;

    public BoardDetailPinsAdapter(OnPinClickListener onPinClickListener, RecyclerView recyclerView) {
        super(onPinClickListener, recyclerView);
    }


    @Override
    public BasePinsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            boardDetailHeaderViewHolder =  new BoardDetailHeaderViewHolder(mHeaderView);
            return boardDetailHeaderViewHolder;
        }
        return new PinsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_snapshot, parent, false));
    }



    @Override
    public void onGetBoardDetail(BoardDetailBean boardDetailBean) {
        final String userId = String.valueOf(boardDetailBean.getBoard().getUser().getUser_id());
        final String userName = boardDetailBean.getBoard().getUser().getUsername();

        String urlAvatar = String.format(Constant.Http.FORMAT_URL_IMAGE_SMALL, boardDetailBean.getBoard().getUser().getAvatar());
        ImageLoader.loadImage(boardDetailHeaderViewHolder.imgBoardUser, urlAvatar);
        boardDetailHeaderViewHolder.tvBoardUser.setText(boardDetailBean.getBoard().getUser().getUsername());

        if (boardDetailBean.getBoard().getDescription() != null && !boardDetailBean.getBoard().getDescription().isEmpty()){
            boardDetailHeaderViewHolder.tvBoardDescribe.setText(boardDetailBean.getBoard().getDescription());
        }

        boardDetailHeaderViewHolder.linearlayoutBoardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNormalHeaderClickListener.onUserClick(userId, userName);
            }
        });

    }
}
