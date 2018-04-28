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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

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
        Observable<Response<Token>> observable = OkHttpUtils.getRetrofit().create(ApiService.class).getToken(client_id, client_secret, grant_type, username, password);
        /*
         * 订阅者
         */
        Observer<Response<Token>> subscriber = new RequestSubscriber<Response<Token>>() {
            @Override
            protected void onSuccess(Response<Token> response) {
                if (response.isSuccessful()) {
                    onLoadDatasListener.onSuccess(response.body());
                } else if (response.code() == 401) {
                    onLoadDatasListener.onFailure("用户名或密码错误");
                } else {
                    onLoadDatasListener.onFailure("登录失败");
                }
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
    public void getMe(String client_id, String client_secret, String grant_type, String username, String password, final OnLoadDatasListener<UserDetail> onLoadDatasListener) {
        /*
        先通过用户名和密码获取到token  再继续用token作为参数，去请求用户信息，这样就达到了嵌套请求
        第一个请求的返回数据，作为第二个请求的参数
         */
//        Observable<Response<UserDetail>> observable = OkHttpUtils.getRetrofit().create(ApiService.class).getToken(client_id, client_secret, grant_type, username, password)
//                .flatMap(new Function<Response<Token>, ObservableSource<Response<UserDetail>>>() {
//                    @Override
//                    public ObservableSource<Response<UserDetail>> apply(Response<Token> response) throws Exception {
//                        //第一个“登录请求”完成之后 会返回token信息，我们把token保存在本地，以便于第二个“获取用户信息”的请求可以从
//                        //本地获取到token作为请求所需要的参数
//                        if (response.isSuccessful()) {
//                            CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
//                            Token token = response.body();
//                            assert token != null;
//                            cacheUtil.saveToken(token);
//                            return OkHttpUtils.getRetrofit().create(ApiService.class).getMe();
//                        }
//                        //如果用户登录出错，没有获取到token的话，那么我们就把用户登录的请求返回的response
//                        //封装到 Response<UserDetail> 然后发射出去，进行code的值判断显示错误信息
//                        ResponseBody responseBody = response.errorBody();
//                        assert responseBody != null;
//                        Response<UserDetail> userDetailResponse = Response.error(response.code(), responseBody);
//                        return Observable.just(userDetailResponse);
//                    }
//                }).map(new Function<Response<UserDetail>, Response<UserDetail>>() {
//                    @Override
//                    public Response<UserDetail> apply(Response<UserDetail> userDetailResponse) throws Exception {
//                        return userDetailResponse;
//                    }
//                });

        Observable<Response<UserDetail>> observableLambda = OkHttpUtils.getRetrofit().create(ApiService.class).getToken(client_id, client_secret, grant_type, username, password)
                .flatMap(response -> {
                    //第一个“登录请求”完成之后 会返回token信息，我们把token保存在本地，以便于第二个“获取用户信息”的请求可以从
                    //本地获取到token作为请求所需要的参数
                    if (response.isSuccessful()) {
                        CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
                        Token token = response.body();
                        assert token != null;
                        cacheUtil.saveToken(token);
                        return OkHttpUtils.getRetrofit().create(ApiService.class).getMe();
                    }
                    //如果用户登录出错，没有获取到token的话，那么我们就把用户登录的请求返回的response
                    //封装到 Response<UserDetail> 然后发射出去，进行code的值判断显示错误信息
                    ResponseBody responseBody = response.errorBody();
                    assert responseBody != null;
                    Response<UserDetail> userDetailResponse = Response.error(response.code(), responseBody);
                    return Observable.just(userDetailResponse);
                });


        Observer<Response<UserDetail>> subscriber = new RequestSubscriber<Response<UserDetail>>() {
            @Override
            protected void onSuccess(Response<UserDetail> userDetailResponse) {
                if (userDetailResponse.isSuccessful()) {
                    onLoadDatasListener.onSuccess(userDetailResponse.body());
                } else if (userDetailResponse.code() == 401) {
                    onLoadDatasListener.onFailure("用户名或密码错误");
                } else {
                    onLoadDatasListener.onFailure("获取用户信息失败");
                }
            }

            @Override
            protected void onFailure(String msg) {
                onLoadDatasListener.onFailure(msg);
            }
        };
        observableLambda.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void bebing(final OnLoadDatasListener<TopicsAndNews> onLoadDatasListener) {
        Observable<Response<List<Topic>>> observable1 = OkHttpUtils.getRetrofit().create(ApiService.class).loadTopicList(1);
        Observable<Response<List<New>>> observable2 = OkHttpUtils.getRetrofit().create(ApiService.class).loadNewsList(1);

//        Observable<TopicsAndNews> observable = Observable.zip(observable1, observable2, new BiFunction<Response<List<Topic>>, Response<List<New>>, TopicsAndNews>() {
//
//            @Override
//            public TopicsAndNews apply(Response<List<Topic>> topicsResponse, Response<List<New>> newsResponse) throws Exception {
//                TopicsAndNews topicsAndNews = new TopicsAndNews();
//                if (newsResponse.isSuccessful()) {
//                    topicsAndNews.setNews(newsResponse.body());
//                }
//                if (topicsResponse.isSuccessful()) {
//                    topicsAndNews.setTopics(topicsResponse.body());
//                }
//                return topicsAndNews;
//            }
//        });

        Observable<TopicsAndNews> observableLambda = Observable.zip(observable1, observable2, (topicsResponse, newsResponse) -> {
            TopicsAndNews topicsAndNews = new TopicsAndNews();
            if (newsResponse.isSuccessful()) {
                topicsAndNews.setNews(newsResponse.body());
            }
            if (topicsResponse.isSuccessful()) {
                topicsAndNews.setTopics(topicsResponse.body());
            }
            return topicsAndNews;
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

        observableLambda.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
