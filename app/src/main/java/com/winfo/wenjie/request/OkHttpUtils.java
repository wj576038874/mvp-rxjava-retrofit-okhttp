package com.winfo.wenjie.request;

import android.support.annotation.NonNull;
import com.winfo.wenjie.mvp.base.MyApplication;
import com.winfo.wenjie.utils.CacheUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    private static CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
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
                    .baseUrl("https://diycode.cc/api/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
                    if (null == cacheUtil.getToken() || alreadyHasAuthorizationHeader(original)) {
                        return chain.proceed(original);
                    }
                    String token = "Bearer " + cacheUtil.getToken().getAccess_token();
                    Request request = original.newBuilder()
                            .header("Authorization", token)
                            .build();
                    return chain.proceed(request);
                }
            });
            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS);
            builder.readTimeout(15 * 1000, TimeUnit.MILLISECONDS);//超时时间
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private static boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        String token = originalRequest.header("Authorization");
        // 如果本身是请求 token 的 URL，直接返回 true
        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
        return !(null == token || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"));
    }
}
