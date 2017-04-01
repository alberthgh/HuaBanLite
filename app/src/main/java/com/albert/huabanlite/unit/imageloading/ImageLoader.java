package com.albert.huabanlite.unit.imageloading;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by alberthuang on 2017/3/1.
 *
 * 通过这样一个中间类，日后要换成另一个图片加载库的话，只需要修改这一个中间类就行了
 *
 */

public class ImageLoader {
    //现在使用的是fresco，为了方便，形参直接限制为SimpleDraweeView
    public static void loadImage(SimpleDraweeView targetView, String uri){
        targetView.setImageURI(uri);
    }
}
