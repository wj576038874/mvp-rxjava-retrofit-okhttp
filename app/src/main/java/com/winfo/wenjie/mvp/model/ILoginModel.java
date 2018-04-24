package com.winfo.wenjie.mvp.model;

import android.app.Dialog;
import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.domain.UserDetail;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName: com.winfo.wenjie.mvp.model
 * FileName: com.winfo.wenjie.mvp.model.ILoginModel.java
 * Author: wenjie
 * Date: 2017-01-03 13:54
 * Description: m层接口
 */
public interface ILoginModel {

    /**
     * 登陆方法
     *
     * @param client_id           client_id
     * @param client_secret       client_secret
     * @param grant_type          grant_type
     * @param username            用户名
     * @param password            用户密码
     * @param onLoadDatasListener 监听函数
     */
    void login(String client_id, String client_secret, String grant_type, String username, String password, OnLoadDatasListener<Token> onLoadDatasListener);

    /**
     * 获取用户信息，用户登录成之后获取到token 然后拿到token再用token去获取用户信息
     *
     * @param onLoadDatasListener 监听
     */
    void getMe(OnLoadDatasListener<UserDetail> onLoadDatasListener);

    /**
     * 合并请求
     * @param onLoadDatasListener 数据监听
     */
    void bebing(OnLoadDatasListener<TopicsAndNews> onLoadDatasListener);

}
