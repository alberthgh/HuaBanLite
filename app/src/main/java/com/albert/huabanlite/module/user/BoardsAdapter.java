package com.albert.huabanlite.module.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.huabanlite.Entity.UserBoardItemBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.ArrayRecyclerAdapter;
import com.albert.huabanlite.listener.OnBoardClickListener;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.imageloading.ImageLoader;

/**
 * Created by alberthuang on 2017/3/20.
 */

public class BoardsAdapter extends ArrayRecyclerAdapter<UserBoardItemBean, BoardsViewHolder> {

    private OnBoardClickListener onBoardClickListener;

    public BoardsAdapter(OnBoardClickListener onBoardClickListener, RecyclerView recyclerView) {
        super(recyclerView);
        this.onBoardClickListener = onBoardClickListener;
    }

    @Override
    public BoardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoardsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_boards, parent, false));
    }

    @Override
    public void onBindViewHolder(BoardsViewHolder holder, final int position) {
        final UserBoardItemBean bean = get(position);

        //设置图片空间的宽高比
        holder.imgCardImage.setAspectRatio(1f);

        ImageLoader.loadImage(holder.imgCardImage, String.format(Constant.Http.URL_GENERAL_FORMAT, getFirstPinsFileKey(bean)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBoardClickListener.onBoardClick(get(position));
            }
        });

        holder.itemUserBoardTitle.setText(bean.getTitle());
    }

    private String getFirstPinsFileKey(UserBoardItemBean bean) {
        int size = bean.getPins().size();
        if (size > 0) {
            return bean.getPins().get(0).getFile().getKey();
        } else {
            return "";
        }
    }
}
