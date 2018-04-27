package com.winfo.wenjie.request;

import com.winfo.wenjie.domain.New;
import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.domain.UserDetail;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName: com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.ApiService.java
 * Author: wenjie
 * Date: 2017-01-17 16:54
 * Description:
 */
public interface ApiService {
    /**
     * 获取 Token (一般在登录时调用)
     *
     * @param client_id     客户端 id
     * @param client_secret 客户端私钥
     * @param grant_type    授权方式 - 密码
     * @param username      用户名
     * @param password      密码
     * @return Token 实体类
     */
    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    Observable<Response<Token>> getToken(
            @Field("client_id") String client_id, @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type, @Field("username") String username,
            @Field("password") String password);



    /**
     * 获取当前登录者的详细资料
     *
     * @return 用户详情
     */
    @GET("users/me.json")
    Observable<Response<UserDetail>> getMe();


    @GET("news.json")
    Observable<Response<List<New>>> loadNewsList(@Query("limit") Integer limit);

    @GET("topics.json")
    Observable<Response<List<Topic>>> loadTopicList(@Query("limit") Integer limit);

}
