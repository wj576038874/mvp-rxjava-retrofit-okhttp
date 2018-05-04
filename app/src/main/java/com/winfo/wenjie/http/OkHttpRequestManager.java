package com.winfo.wenjie.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.http
 * Author: wenjie
 * FileName: com.winfo.wenjie.http.OkHttpRequestManager.java
 * Date: 2018-05-04 13:03
 * Description:
 */
public class OkHttpRequestManager implements RequestManager {

    private OkHttpClient okHttpClient;
    private Handler mHander;
    private static OkHttpRequestManager instence;
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    static OkHttpRequestManager getInstance() {
        if (instence == null) {
            synchronized (OkHttpRequestManager.class) {
                if (instence == null) {
                    instence = new OkHttpRequestManager();
                }
            }
        }
        return instence;
    }

    private OkHttpRequestManager() {
        okHttpClient = new OkHttpClient.Builder().build();
        mHander = new Handler(Looper.getMainLooper());
    }

    @Override
    public void get(String url, RequestCallback requestCallback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHander.post(() -> requestCallback.onFailure(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                mHander.post(() -> {
                    try {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null) {
                            requestCallback.onSuccess(responseBody.string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        requestCallback.onFailure(e);
                    }
                });
            }
        });
    }

    @Override
    public void post(String url, String requestBodyJson, RequestCallback requestCallback) {
        RequestBody body = RequestBody.create(JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHander.post(() -> requestCallback.onFailure(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                mHander.post(() -> {
                    try {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null) {
                            requestCallback.onSuccess(responseBody.string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        requestCallback.onFailure(e);
                    }
                });
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> params, RequestCallback requestCallback) {
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("", "");
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHander.post(() -> requestCallback.onFailure(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                mHander.post(() -> {
                    try {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null) {
                            requestCallback.onSuccess(responseBody.string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        requestCallback.onFailure(e);
                    }
                });
            }
        });

    }

}
