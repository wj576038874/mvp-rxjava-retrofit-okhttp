package com.winfo.wenjie.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.request
 * @FileName: com.winfo.wenjie.request.OkHttpUtils.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:17
 * @Description: 网络请求的工具类
 * @Version:
 */
public class OkHttpUtils {
    /**
     * okhttp
     */
    private static OkHttpClient okHttpClient;

    /**
     * 登陆所用的Retrofit
     */
    public static Retrofit loginRetrofit;


    private static String BASEURL = "http://121.8.249.13:8081/gdmsaec-app/act/";

    /**
     * 获取登陆Retrofit的实例
     *
     * @return loginRetrofit
     */
    public static Retrofit getLoginRetrofit() {
        if (loginRetrofit == null) {
            loginRetrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return loginRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(15, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }
}
