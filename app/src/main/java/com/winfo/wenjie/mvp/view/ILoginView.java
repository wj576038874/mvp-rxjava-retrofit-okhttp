package com.winfo.wenjie.mvp.view;

import android.app.Dialog;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.mvp.view.impl
 * @FileName: com.winfo.wenjie.mvp.view.impl.ILoginView.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:11
 * @Description: view层的接口 由view来实现也就是mainactivity来实现该接口
 * @Version:
 */
public interface ILoginView {

    /**
     * 获取view层的dialog
     * @return
     */
    Dialog getDialog();

    /**
     * 获取用户名 参数
     * @return username
     */
    String getUserName();

    /**
     * 获取密码
     * @return password
     */
    String getPassword();

    /**
     * 弹出消息
     * @param msg
     */
    void showMsg(String msg);

    /**
     * 将数据返回给view
     * @param result
     */
    void setText(String result);
}
