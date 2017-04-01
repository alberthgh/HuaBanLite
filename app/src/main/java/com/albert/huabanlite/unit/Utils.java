package com.albert.huabanlite.unit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by LiCola on  2015/12/05  14:12
 */
public final class Utils {

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * 计算宽高比
     *
     * @param width
     * @param height
     * @return
     */
    public static float getAspectRatio(int width, int height) {
        float ratio = (float) width / (float) height;
        //宽高比<0.7 表示长图 需要截断处理
        if (ratio < 0.7) {
            return 0.7f;
        }
        //// TODO: 2016/5/11 0011 ratio>3会导致图片不能显示
        return ratio;
    }


    /**
     * 检查图片类型是否为 git
     *
     * @param type
     * @return
     */
    public static boolean checkIsGif(String type) {
        if (type == null||type.isEmpty()) {
            return false;
        }

        if (type.contains("gif") || type.contains("GIF")) {
            return true;
        }
        return false;
    }


    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context)
    {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity)
        {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected())
            {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
