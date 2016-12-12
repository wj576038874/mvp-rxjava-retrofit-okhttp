package com.winfo.wenjie.mvp.presenter;

import com.winfo.wenjie.mvp.model.LoginModel;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.request.DialogSubscriber;
import com.winfo.wenjie.request.OkHttpUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
     * @param loginView
     */
    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        /**
         *示例化loginmodel对象  固定写法  Retrofit.create(Class);
         */
        this.loginModel = OkHttpUtils.getLoginRetrofit().create(LoginModel.class);
    }

    /**
     * 登陆
     */
    public void login(){
        loginModel.login(loginView.getUserName() , loginView.getPassword())
                .subscribeOn(Schedulers.io())//请求在那个线程执行 io线程 或者 newThresd()新建一个子线程  android4.0网络请求不能再主线程执行
                .observeOn(AndroidSchedulers.mainThread())//回调是在那个线程中执行 android主线程  更新UI必须在主线程 这里rxjava专门提供了android主线程
                //订阅  被订阅者 订阅  订阅者
                /**
                 * loginModel.login(loginView.getUserName() , loginView.getPassword())这个方法返回的就是被订阅者
                 * subscribe订阅的方法
                 * new DialogSubscriber<String>(loginView.getDialog().... 这个是订阅者  他需要一个对话框的对象从view获取并传进去
                 */
                .subscribe(new DialogSubscriber<String>(loginView.getDialog()){

                    @Override
                    protected void onSuccess(String s) {
                        //请求成功服务器返回的数据s
                        loginView.setText(s);
                    }

                    @Override
                    protected void onFailure(String msg) {
                        //请求成功服务器返回的错误信息
                        loginView.showMsg(msg);
                    }
                });
    }
}
