package com.winfo.wenjie.mvp.presenter;

import com.winfo.wenjie.domain.UserInfo;
import com.winfo.wenjie.mvp.model.impl.LoginModel;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.request.OkHttpUtils;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.mvp.presenter
 * @FileName: com.winfo.wenjie.mvp.presenter.LoginPresenter.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:12
 * @Description: p层
 * @Version:
 */
public class LoginPresenter {

    /**
     * v层
     */
    private ILoginView loginView;

    /**
     * m层
     */
    private LoginModel loginModel;

    /**
     * mvp模式  p层持有  v 和m 的接口引用 来进行数据的传递  起一个中间层的作用
     *
     * @param loginView
     */
    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        /**
         *示例化loginmodel对象  固定写法  Retrofit.create(Class);
         */
        this.loginModel = OkHttpUtils.getRetrofit().create(LoginModel.class);
    }

    /**
     * 登陆
     */
    public void login() {

        loginModel.login(loginView.getDialog(), loginView.getUserName(), loginView.getPassword(), new LoginModel.OnLoginListener() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                //请求成功服务器返回的数据s
                loginView.setText(userInfo.toString());
            }

            @Override
            public void onFailure(String msg) {
                //请求成功服务器返回的错误信息
                loginView.showMsg(msg);
            }
        });
    }
}
