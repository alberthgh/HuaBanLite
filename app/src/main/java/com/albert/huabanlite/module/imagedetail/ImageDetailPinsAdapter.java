package com.albert.huabanlite.module.imagedetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.coustomwidget.BasePinsViewHolder;
import com.albert.huabanlite.base.coustomwidget.PinsAdapter;
import com.albert.huabanlite.base.coustomwidget.PinsViewHolder;
import com.albert.huabanlite.listener.OnGetImageDetailListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.imageloading.ImageLoader;

/**
 * Created by alberthuang on 2017/3/27.
 */

public class ImageDetailPinsAdapter extends PinsAdapter implements OnGetImageDetailListener {


    protected ImageDetailHeaderViewHolder imageDetailHeaderViewHolder;

    public ImageDetailPinsAdapter(OnPinClickListener onPinClickListener, RecyclerView recyclerView) {
        super(onPinClickListener, recyclerView);
    }


    @Override
    public BasePinsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            imageDetailHeaderViewHolder =  new ImageDetailHeaderViewHolder(mHeaderView);
            return imageDetailHeaderViewHolder;
        }
        return new PinsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_snapshot, parent, false));
    }


    @Override
    public void onGetImageDetail(PinsDetailBean pinsDetailBean) {
        PinsDetailBean.PinBean bean = pinsDetailBean.getPin();
        final String mBoardId = String.valueOf(bean.getBoard_id());
        final String mUserId = String.valueOf(bean.getUser_id());
        final String mBoardName = bean.getBoard().getTitle();
        final String mUserName = bean.getUser().getUrlname();

        //配文
        if (bean.getRaw_text() != null && !bean.getRaw_text().isEmpty()){
            imageDetailHeaderViewHolder.tvImageText.setText(bean.getRaw_text());
        }

        //用户名
        imageDetailHeaderViewHolder.tvImageUser.setText(bean.getUser().getUsername());

        //用户头像
        String avatarUrl = bean.getUser().getAvatar();
        //因为图片来源不定 需要做处理
        if (avatarUrl != null) {
            if (!avatarUrl.contains(Constant.Salad.HTTP_ROOT)) {
                avatarUrl = String.format(Constant.Http.FORMAT_URL_IMAGE_SMALL, avatarUrl);
            }
            ImageLoader.loadImage(imageDetailHeaderViewHolder.imgImageUser, avatarUrl);
        }

        //画板信息
        String boardUrl = String.format(Constant.Http.FORMAT_URL_IMAGE_SMALL, bean.getBoard().getPins().get(0).getFile().getKey());
        ImageLoader.loadImage(imageDetailHeaderViewHolder.imgImageBoard, boardUrl);

        if (bean.getBoard().getTitle() != null && !bean.getBoard().getTitle().isEmpty()){
            imageDetailHeaderViewHolder.tvImageBoard.setText(bean.getBoard().getTitle());
        }


        imageDetailHeaderViewHolder.relativelayoutImageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNormalHeaderClickListener.onUserClick(mUserId, mUserName);
            }
        });

        imageDetailHeaderViewHolder.relativelayoutImageBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNormalHeaderClickListener.onBoardClick(mBoardId, mBoardName);
            }
        });
    }


}
