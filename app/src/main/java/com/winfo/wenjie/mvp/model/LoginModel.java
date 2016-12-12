package com.winfo.wenjie.mvp.model;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.mvp.model
 * @FileName: com.winfo.wenjie.mvp.model.LoginModel.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:47
 * @Description:    m层
 * @Version:
 */
public interface LoginModel {


    /**
     * 登陆方法
     * @param username
     * @param password
     * @return 被订阅者 也就是被观察者 这里是通过rxjava结合起来使用  所以返回这个对象
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("AppUser/loginin")
    Observable<String> login(@Field("username") String username , @Field("password") String password);
}
