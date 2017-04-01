package com.albert.huabanlite.base.coustomwidget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.R;
import com.albert.huabanlite.listener.OnNormalHeaderClickListener;
import com.albert.huabanlite.listener.OnPinClickListener;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.Utils;
import com.albert.huabanlite.unit.imageloading.ImageLoader;

/**
 * Created by alberthuang on 2017/2/27.
 *
 * 需要定制headView，则使用PinsAdapter的子类
 */

public class PinsAdapter extends ArrayRecyclerAdapter<PinsMainEntity, BasePinsViewHolder> {
    protected OnPinClickListener onPinClickListener;
    protected OnNormalHeaderClickListener onNormalHeaderClickListener;

    public PinsAdapter(OnPinClickListener onPinClickListener, RecyclerView recyclerView) {
        super(recyclerView);
        this.onPinClickListener = onPinClickListener;
    }

    public void setOnNormalHeaderClickListener(OnNormalHeaderClickListener onNormalHeaderClickListener) {
        this.onNormalHeaderClickListener = onNormalHeaderClickListener;
    }

    @Override
    public BasePinsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PinsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_snapshot, parent, false));
    }

    @Override
    public void onBindViewHolder(BasePinsViewHolder holder, final int position) {

        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        if (holder instanceof PinsViewHolder) {


            final PinsMainEntity bean = get(position);


            //设置图片空间的宽高比
            int width = bean.getFile().getWidth();
            int height = bean.getFile().getHeight();
            ((PinsViewHolder)holder).pictureSnapshotImage.setAspectRatio(Utils.getAspectRatio(width, height));


            ImageLoader.loadImage(((PinsViewHolder)holder).pictureSnapshotImage, String.format(Constant.Http.URL_GENERAL_FORMAT, bean.getFile().getKey()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPinClickListener.onPinClick(bean);
                }
            });
        }
    }


    //为RecyclerView添加Header
    //http://blog.csdn.net/qibin0506/article/details/49716795
    @Override
    public void onViewAttachedToWindow(BasePinsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (!(holder instanceof PinsViewHolder)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

}
