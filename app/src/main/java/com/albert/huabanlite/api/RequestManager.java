package com.albert.huabanlite.api;

import com.albert.huabanlite.Entity.BoardDetailBean;
import com.albert.huabanlite.Entity.ListPinsBean;
import com.albert.huabanlite.Entity.PinsDetailBean;
import com.albert.huabanlite.Entity.PinsMainEntity;
import com.albert.huabanlite.Entity.UserBoardListBean;
import com.albert.huabanlite.Entity.UserMeAndOtherBean;

import java.util.List;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alberthuang on 2017/3/17.
 */

public class RequestManager {
     private RestApi restApi;


    public RequestManager(RestApi restApi){
        this.restApi = restApi;
    }

    public Observable<ListPinsBean> getPinsByType(@Path("type") String type, @Query("limit") int limit){
        return restApi.getPinsByType(type, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<ListPinsBean> getPinsByType(@Path("type") String type, @Query("max") int max, @Query("limit") int limit){
        return restApi.getPinsByType(type, max, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<PinsDetailBean> getPinsDetail(@Path("pinsId") String pinsId){
        return restApi.getPinsDetail(pinsId)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<List<PinsMainEntity>> getPinsRecommend(@Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit){
        return restApi.getPinsRecommend(pinsId, page, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<BoardDetailBean> getBoardDetailInfo(@Path("boardId") String boardId){
        return restApi.getBoardDetailInfo(boardId)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<ListPinsBean> getBoardDetailPins(@Path("boardId") String boardId, @Query("limit") int limit){
        return restApi.getBoardDetailPins(boardId, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<ListPinsBean> getBoardDetailPins(@Path("boardId") String boardId, @Query("max") int max, @Query("limit") int limit){
        return restApi.getBoardDetailPins(boardId, max, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }


    public Observable<UserMeAndOtherBean> getUserInfo (@Path("userId") String userId){
        return restApi.getUserInfo(userId)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<UserBoardListBean> getUserBoards(@Path("userId") String userId, @Query("limit") int limit){
        return restApi.getUserBoards(userId, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Observable<UserBoardListBean> getUserBoards(@Path("boardId") String boardId, @Query("max") int max, @Query("limit") int limit){
        return restApi.getUserBoards(boardId, max, limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

}
