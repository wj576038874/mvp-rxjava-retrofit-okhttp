package com.winfo.wenjie.http;

/**
 * ProjectName: gdmsaecApp
 * PackageName: com.winfo.gdmsaec.app.gz.request
 * FileName: com.winfo.gdmsaec.app.gz.request.RequestCallback.java
 * Author: wenjie
 * Date: 2016-12-23 11:28
 * Description:
 * Version:
 */
public interface RequestCallback {
    /**
     * 请求成功
     * @param response	服务器返回的结果数据
     */
    void onSuccess(String response);

    /**
     * 请求失败
     * @param throwable	错误信息
     */
    void onFailure(Throwable throwable);
}
