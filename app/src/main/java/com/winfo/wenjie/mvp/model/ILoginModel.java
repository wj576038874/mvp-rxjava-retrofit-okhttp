package com.winfo.wenjie.mvp.model;

import android.app.Dialog;

import com.winfo.wenjie.mvp.model.impl.LoginModel;

/**
 * @ProjectName: gdmsaecApp
 * @PackageName: com.winfo.gdmsaec.app.mvp.model.login
 * @FileName: com.winfo.gdmsaec.app.mvp.model.login.ILoginModel.java
 * @Author: wenjie
 * @Date: 2017-01-03 13:54
 * @Description:
 * @Version:
 */
public interface ILoginModel {

    /**
     *  登陆方法
     * @param dialog 对话框这里传递到model不是很好，但是也没办法，因为要做一个对话框消失，同时取消请求的操作
     * @param username  用户名
     * @param password  用户密码
     * @param loginListener 监听函数
     */
    void login(Dialog dialog, String username, String password, LoginModel.OnLoginListener loginListener);

}
