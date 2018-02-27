package com.winfo.wenjie.request;

import com.winfo.wenjie.domain.UserInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

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
     * 用户登陆
     *
     * @param username username
     * @param password password
     * @return return
     */
    @FormUrlEncoded
    @POST("AppUser/loginin")
    Observable<ResponseResult<UserInfo>> login(@Field("username") String username, @Field("password") String password);

}
