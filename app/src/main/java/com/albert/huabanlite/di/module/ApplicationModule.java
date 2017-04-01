package com.albert.huabanlite.di.module;

import android.app.Application;
import android.content.Context;

import com.albert.huabanlite.api.RequestManager;
import com.albert.huabanlite.api.RestApi;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alberthuang on 2017/3/2.
 */
@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    @Singleton
    public RequestManager provideRequestManager(RestApi restApi){
        return new RequestManager(restApi);
    }

}
