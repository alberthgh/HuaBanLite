package com.albert.huabanlite.api;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.Entity.ListPinsBean;
import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.Entity.UserBoardListBean;
import com.albert.huabanlite.Entity.UserMeAndOtherBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by alberthuang on 2017/3/2.
 */

public interface RestApi {

    //获取分类图片
    @GET("favorite/{type}")
    Observable<ListPinsBean> getPinsByType(@Path("type") String type, @Query("max") int max, @Query("limit") int limit);

    @GET("favorite/{type}")
    Observable<ListPinsBean> getPinsByType(@Path("type") String type, @Query("limit") int limit);

    //https://api.huaban.com/pins/663478425
    //根据图片id获取详情
    @GET("pins/{pinsId}")
    Observable<PinsDetailBean> getPinsDetail(@Path("pinsId") String pinsId);

    //https//api.huaban.com/pins/654197326/recommend/?page=1&limit=40
    //获取某个图片的推荐图片列表
    @GET("pins/{pinsId}/recommend/")
    Observable<List<PinsMainEntity>> getPinsRecommend(@Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit);

    //获取画板的详情
    @GET("boards/{boardId}")
    Observable<BoardDetailBean> getBoardDetailInfo(@Path("boardId") String boardId);

    //获取画板的图片集合
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> getBoardDetailPins(@Path("boardId") String boardId, @Query("limit") int limit);

    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> getBoardDetailPins(@Path("boardId") String boardId, @Query("max") int max, @Query("limit") int limit);

    //获取个人信息
    @GET("users/{userId}")
    Observable<UserMeAndOtherBean> getUserInfo(@Path("userId") String userId);

    //用户画板信息
    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> getUserBoards(@Path("userId") String userId, @Query("limit") int limit);

    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> getUserBoards(@Path("userId") String userId, @Query("max") int max, @Query("limit") int limit);

}
