package com.winfo.wenjie.mvp.model.impl;

import android.app.Dialog;

import com.winfo.wenjie.domain.UserInfo;
import com.winfo.wenjie.mvp.model.ILoginModel;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.DialogSubscriber;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.request.RequestParams;
import com.winfo.wenjie.request.ResponseResult;
import com.winfo.wenjie.request.ResultData;
import com.winfo.wenjie.utils.JsonUtil;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.mvp.model
 * @FileName: com.winfo.wenjie.mvp.model.impl.LoginModel.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:47
 * @Description: m层
 * @Version:
 */
public class LoginModel implements ILoginModel {


    @Override
    public void login(Dialog dialog, String username, String password, final OnLoginListener loginListener) {

        /*
         * 被订阅者
         */
        Observable<ResponseResult<UserInfo>> observable = OkHttpUtils.getRetrofit().create(ApiService.class).login(username, password);

        /*
         * 订阅者
         */
        Subscriber<ResponseResult<UserInfo>> subscriber = new DialogSubscriber<ResponseResult<UserInfo>>(dialog , true) {
            @Override
            public void onSuccess(ResponseResult<UserInfo> userInfoUserResponseResult) {
                switch (userInfoUserResponseResult.getResult()) {
                    case 1:
                        loginListener.onSuccess(userInfoUserResponseResult.getData());
                        break;
                    case 0:
                        loginListener.onFailure(userInfoUserResponseResult.getMsg());
                        break;
                    case -1:
                        loginListener.onFailure(userInfoUserResponseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                loginListener.onFailure(msg);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 回调接口用来把数据返回给p
     */
    public interface OnLoginListener {

        /**
         * 请求成功的回调方法
         * @param userInfo  用户信息
         */
        void onSuccess(UserInfo userInfo);

        /**
         * 请求失败的回调方法
         * @param msg   失败的信息
         */
        void onFailure(String msg);
    }
}
