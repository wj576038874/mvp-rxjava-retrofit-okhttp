package com.winfo.wenjie.request;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName: com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.OkHttpUtils.java
 * Author: wenjie
 * Date: 2016-12-12 14:17
 * Description: 网络请求的工具类
 */
public class OkHttpUtils {
    /**
     * okhttp
     */
    private static OkHttpClient okHttpClient;


    /**
     * Retrofit
     */
    private static Retrofit retrofit;

    /**
     * 获取Retrofit的实例
     *
     * @return retrofit
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.67/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(15, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }
}
