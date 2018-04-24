package com.winfo.wenjie.mvp.model.impl;

import com.winfo.wenjie.domain.New;
import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.domain.UserDetail;
import com.winfo.wenjie.mvp.base.MyApplication;
import com.winfo.wenjie.mvp.model.ILoginModel;
import com.winfo.wenjie.mvp.model.OnLoadDatasListener;
import com.winfo.wenjie.mvp.model.TopicsAndNews;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.request.RequestSubscriber;
import com.winfo.wenjie.utils.CacheUtil;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    public void login(String client_id, String client_secret, String grant_type, String username, String password, final OnLoadDatasListener<Token> onLoadDatasListener) {
         /*
         * 被订阅者
         */
        Observable<Token> observable = OkHttpUtils.getRetrofit().create(ApiService.class).getToken(client_id, client_secret, grant_type, username, password);
        /*
         * 订阅者
         */
        Observer<Token> subscriber = new RequestSubscriber<Token>() {
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

    @Override
    public void getMe(final OnLoadDatasListener<UserDetail> onLoadDatasListener) {
        /*
        先通过用户名和密码获取到token  再继续用token作为参数，去请求用户信息，这样就达到了嵌套请求
        第一个请求的返回数据，作为第二个请求的参数
         */
        Observable<UserDetail> observable = OkHttpUtils.getRetrofit().create(ApiService.class).getToken("", "", "password", "wj576038874", "1rujiwang")
                .flatMap(new Function<Token, ObservableSource<UserDetail>>() {
                    @Override
                    public Observable<UserDetail> apply(Token token) throws Exception{
                        //第一个“登录请求”完成之后 会返回token信息，我们把token保存在本地，以便于第二个“获取用户信息”的请求可以从
                        //本地获取到token作为请求所需要的参数
                        CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
                        cacheUtil.saveToken(token);
                        return OkHttpUtils.getRetrofit().create(ApiService.class).getMe();
                    }
                }).map(new Function<UserDetail, UserDetail>() {
                    @Override
                    public UserDetail apply(UserDetail userDetail) throws Exception {
                        return userDetail;
                    }
                });

        Observer<UserDetail> subscriber = new RequestSubscriber<UserDetail>() {
            @Override
            protected void onSuccess(UserDetail userDetail) {
                onLoadDatasListener.onSuccess(userDetail);
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

    @Override
    public void bebing(final OnLoadDatasListener<TopicsAndNews> onLoadDatasListener) {
        Observable<List<Topic>> observable1 = OkHttpUtils.getRetrofit().create(ApiService.class).loadTopicList(1);
        Observable<List<New>> observable2 = OkHttpUtils.getRetrofit().create(ApiService.class).loadNewsList(1);

        Observable<TopicsAndNews> observable = Observable.zip(observable1, observable2, new BiFunction<List<Topic>, List<New>, TopicsAndNews>() {

            @Override
            public TopicsAndNews apply(List<Topic> topics, List<New> news) throws Exception {
                TopicsAndNews topicsAndNews = new TopicsAndNews();
                topicsAndNews.setNews(news);
                topicsAndNews.setTopics(topics);
                return topicsAndNews;
            }
        });

        Observer<TopicsAndNews> subscriber = new RequestSubscriber<TopicsAndNews>() {
            @Override
            protected void onSuccess(TopicsAndNews topicsAndNews) {
                onLoadDatasListener.onSuccess(topicsAndNews);
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
