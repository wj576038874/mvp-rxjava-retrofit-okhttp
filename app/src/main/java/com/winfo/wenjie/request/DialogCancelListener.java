package com.winfo.wenjie.request;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.DialogCancelListener.java
 * Author: wenjie
 * Date: 2016-12-12 14:32
 * Description: 对话框隐藏或者消失之后取消请求
 */
public interface DialogCancelListener {
    /**
     * 取消网络请求
     */
    void onCancel();
}
