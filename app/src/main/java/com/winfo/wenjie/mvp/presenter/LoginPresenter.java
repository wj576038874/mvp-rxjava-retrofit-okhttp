package com.winfo.wenjie.mvp.presenter;

import com.winfo.wenjie.domain.UserInfo;
import com.winfo.wenjie.mvp.base.BaseMvpPresenter;
import com.winfo.wenjie.mvp.model.impl.LoginModel;
import com.winfo.wenjie.mvp.view.ILoginView;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.mvp.presenter
 * FileName: com.winfo.wenjie.mvp.presenter.LoginPresenter.java
 * Author: wenjie
 * Date: 2016-12-12 14:12
 * Description: p层
 */
public class LoginPresenter extends BaseMvpPresenter<ILoginView> {

    /**
     * m层
     */
    private LoginModel loginModel;

    /**
     * mvp模式  p层持有  v 和m 的接口引用 来进行数据的传递  起一个中间层的作用
     *
     */
    public LoginPresenter() {
        /*
         *示例化loginmodel对象  固定写法  Retrofit.create(Class);
         */
        this.loginModel = new LoginModel();
    }

    /**
     * 登陆
     */
    public void login() {
        if (mView == null) return;
        loginModel.login(mView.getDialog(), mView.getUserName(), mView.getPassword(), new LoginModel.OnLoginListener() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                //请求成功服务器返回的数据s
                mView.setText(userInfo.toString());
            }

            @Override
            public void onFailure(String msg) {
                //请求成功服务器返回的错误信息
                mView.showMsg(msg);
            }
        });
    }
}
