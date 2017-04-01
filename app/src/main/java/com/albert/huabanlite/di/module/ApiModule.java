package com.albert.huabanlite.di.module;

import android.content.Context;

import com.albert.huabanlite.api.RestApi;
import com.albert.huabanlite.di.qualifier.ApplicationContext;
import com.albert.huabanlite.network.AvatarConverter;
import com.albert.huabanlite.network.GBRxJavaCallAdapterFactory;
import com.albert.huabanlite.unit.Constant;
import com.albert.huabanlite.unit.Utils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by alberthuang on 2017/3/2.
 */
@Module(includes = ApplicationModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public RestApi provideRetrofit(OkHttpClient client, Converter.Factory converter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Http.BASE_URL)
                .addConverterFactory(converter)
                .addCallAdapterFactory(GBRxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(RestApi.class);
    }


    @Provides
    @Singleton
    public OkHttpClient provideClient(List<Interceptor> interceptors, @ApplicationContext Context context) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(Constant.Http.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.writeTimeout(Constant.Http.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.readTimeout(Constant.Http.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        okHttpClientBuilder.interceptors().addAll(interceptors);//此方法有缓存本地数据File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 Min
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        okHttpClientBuilder.cache(cache);
        return okHttpClientBuilder.build();
    }


    @Provides
    @Singleton
    public List<Interceptor> provideInterceptors(@ApplicationContext final Context context) {
        List<Interceptor> list = new ArrayList<>();
        /**
         * 日志输出
         */
        Interceptor loggingInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        };
        /**
         * 提交数据
         */

        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Authorization")
//                        .addHeader("User-Agent", user)
                        .build();

                return chain.proceed(request.newBuilder()
                        .build());
            }
        };

        /**
         * 缓存数据
         */
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Utils.isConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (Utils.isConnected(context)) {
                    String cacheControl = originalResponse.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")//??
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                            .removeHeader("Pragma")//??
                            .build();
                }
            }
        };



        list.add(loggingInterceptor);
        list.add(authInterceptor);
//        list.add(encryptInterceptor);
        list.add(cacheInterceptor);
        return list;
    }


    @Provides
    @Singleton
    public Converter.Factory provideConverter(Gson gson) {

        // ！！！！！ 配合AvatarConverter使用 正则转 Object avatar 为String
        return AvatarConverter.create(gson);
    }


}
