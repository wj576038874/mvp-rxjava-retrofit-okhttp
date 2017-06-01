package com.winfo.wenjie.request;

import com.winfo.wenjie.domain.UserInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName: com.winfo.wenjie.request
 * @FileName: com.winfo.wenjie.request.ApiService.java
 * @Author: wenjie
 * @Date: 2017-01-17 16:54
 * @Description:
 * @Version:
 */
public interface ApiService {
    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("UserInfo/loginin")
    Observable<ResponseResult<UserInfo>> login(@Field("username") String username, @Field("password") String password);
}
