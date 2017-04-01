package com.albert.huabanlite.di.component;

import android.content.Context;

import com.albert.huabanlite.di.module.ApplicationModule;
import com.albert.huabanlite.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alberthuang on 2017/3/2.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

}
