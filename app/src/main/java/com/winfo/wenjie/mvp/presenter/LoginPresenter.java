package com.winfo.wenjie.mvp.presenter;

import android.text.TextUtils;

import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.domain.UserDetail;
import com.winfo.wenjie.mvp.base.BaseMvpPresenter;
import com.winfo.wenjie.mvp.model.OnLoadDatasListener;
import com.winfo.wenjie.mvp.model.TopicsAndNews;
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
        if (TextUtils.isEmpty(mView.getUserName()) || TextUtils.isEmpty(mView.getPassword())) {
            mView.showMsg("用户名或密码不能为空");
            return;
        }
        loginModel.login(mView.getDialog(), "", "", "password", mView.getUserName(), mView.getPassword(), new OnLoadDatasListener<Token>() {
            @Override
            public void onSuccess(Token token) {
                //请求成功服务器返回的数据s
                mView.setText(token.getAccess_token());
            }

            @Override
            public void onFailure(String error) {
                //请求成功服务器返回的错误信息
                mView.showMsg(error);
            }
        });
    }


    /**
     * 获取用户信息
     */
    public void getMe() {
        if (mView == null) return;
        loginModel.getMe(mView.getDialog() , new OnLoadDatasListener<UserDetail>() {
            @Override
            public void onSuccess(UserDetail userDetail) {
                mView.setUserDetail(userDetail);
            }

            @Override
            public void onFailure(String error) {
                mView.showMsg(error);
            }
        });
    }

    public void bebing(){
        if (mView== null) return;
        loginModel.bebing(mView.getDialog(), new OnLoadDatasListener<TopicsAndNews>() {
            @Override
            public void onSuccess(TopicsAndNews topicsAndNews) {
                mView.setBebingData(topicsAndNews);
            }

            @Override
            public void onFailure(String error) {
                mView.showMsg(error);
            }
        });
    }
}
