package com.albert.huabanlite.unit;

/**
 * Created by alberthuang on 2017/3/1.
 */

public class Constant {

    public static final class Http{

        //baseUrl
        public static final String BASE_URL="https://api.huaban.com/";
        //http header
        public static final String Authorization="Authorization";
        //http limit number
        public static final int LIMIT = 20;

        //OkHttpClient builder
        public static final int CONNECT_TIMEOUT = 15 * 1000;//15s
        public static final int WRITE_TIMEOUT = 15 * 1000;//15s
        public static final int READ_TIMEOUT = 30 * 1000;//15s


        //用于生成图片地址
        public static final String URL_GENERAL_FORMAT = "http://img.hb.aicdn.com/%s_fw320sf";
        //大图的后缀
        public static final String FORMAT_URL_IMAGE_BIG = "http://img.hb.aicdn.com/%s_fw658";
        //小图的后缀
        public static final String FORMAT_URL_IMAGE_SMALL = "http://img.hb.aicdn.com/%s_sq75sf";


    }

    public static final class Type{
        //所有
        public static final String ALL="all";

        //家居
        public static final String HOME="home";

        //美女
        public static final String BEAUTY="beauty";

        //动漫
        public static final String ANIME="anime";

        //旅行
        public static final String TRAVEL_PLACES="travel_places";

        //宠物
        public static final String PETS="pets";

    }


    //杂七杂八的东西，不知道怎么起名字，就叫沙律吧
    public static final class Salad{
        //辅助默认值-1
        public static final int DEFAULT_VALUE_MINUS_ONE = -1;

        public static final String HTTP_ROOT = "http://";
    }

}
