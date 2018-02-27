package com.winfo.wenjie.mvp.model.impl;

import android.app.Dialog;

import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.mvp.model.ILoginModel;
import com.winfo.wenjie.mvp.model.OnLoadDatasListener;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.DialogSubscriber;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.request.ResponseResult;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.mvp.model
 * FileName: com.winfo.wenjie.mvp.model.impl.LoginModel.java
 * Author: wenjie
 * Date: 2016-12-12 14:47
 * Description: m层实现类
 */
public class LoginModel implements ILoginModel {

    @Override
    public void login(Dialog dialog, String client_id, String client_secret, String grant_type, String username, String password, final OnLoadDatasListener<Token> onLoadDatasListener) {
         /*
         * 被订阅者
         */
        Observable<Token> observable = OkHttpUtils.getRetrofit().create(ApiService.class).getToken(client_id, client_secret, grant_type, username, password);
        /*
         * 订阅者
         */
        Subscriber<Token> subscriber = new DialogSubscriber<Token>(dialog , true) {
            @Override
            protected void onSuccess(Token token) {
                onLoadDatasListener.onSuccess(token);
            }

            @Override
            protected void onFailure(String msg) {
                onLoadDatasListener.onFailure(msg);
            }
        };
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
