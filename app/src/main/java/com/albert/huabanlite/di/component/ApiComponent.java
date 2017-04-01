package com.albert.huabanlite.di.component;

import com.albert.huabanlite.di.module.ApiModule;
import com.albert.huabanlite.mvp.presenter.BoardDetailPresenter;
import com.albert.huabanlite.mvp.presenter.ImageDetailPresenter;
import com.albert.huabanlite.mvp.presenter.TypePresenter;
import com.albert.huabanlite.mvp.presenter.UserBoardsPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alberthuang on 2017/3/3.
 */

@Singleton
@Component(modules = { ApiModule.class})
public interface ApiComponent {

    void inject(TypePresenter typePresenter);
    void inject(ImageDetailPresenter imageDetailPresenter);
    void inject(UserBoardsPresenter userBoardsPresenter);
    void inject(BoardDetailPresenter boardDetailPresenter);

    class Instance {
        private static ApiComponent apiComponent;

        public static void init(ApiComponent component) {
            apiComponent = component;
        }

        public static ApiComponent get() {
            return apiComponent;
        }
    }
}
