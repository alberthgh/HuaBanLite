package com.albert.huabanlite;

import android.app.Application;

import com.albert.huabanlite.di.component.ApiComponent;
import com.albert.huabanlite.di.component.ApplicationComponent;
import com.albert.huabanlite.di.component.DaggerApiComponent;
import com.albert.huabanlite.di.component.DaggerApplicationComponent;
import com.albert.huabanlite.di.module.ApplicationModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

/**
 * Created by alberthuang on 2017/2/28.
 */

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate(){
        super.onCreate();

        //！！！网络请求获取到数据后，使用普通的GsonConverterFactory处理会出错。此项目使用LiCola封装好的AvatarConverter进行处理，再次感谢LiCola

        Stetho.initializeWithDefaults(this);

        Fresco.initialize(this);//初始化Fresco图片加载框架

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        ApiComponent.Instance.init(DaggerApiComponent.builder().applicationModule(new ApplicationModule(this)).build());
    }
}
