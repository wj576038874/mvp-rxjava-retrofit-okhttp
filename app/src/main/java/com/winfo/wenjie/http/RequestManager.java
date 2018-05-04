package com.winfo.wenjie.http;

import java.util.Map;

/**
 * ProjectName: gdmsaecApp
 * PackageName: com.winfo.gdmsaec.app.gz.request
 * FileName: com.winfo.gdmsaec.app.gz.request.RequestManager.java
 * Author: wenjie
 * Date: 2016-12-23 11:27
 * Description:    请求管理接口 提供get post请求方法
 */
public interface RequestManager {
    /**
     * get请求
     *
     * @param url             请求路径
     * @param requestCallback 请求回调
     */
    void get(String url, RequestCallback requestCallback);

    /**
     * post请求提交json数据到服务器
     *
     * @param url             请求路径
     * @param requestBoduJson 提交的json数据
     * @param requestCallback 回调
     */
    void post(String url, String requestBoduJson, RequestCallback requestCallback);


    /**
     * post请求 提交表单数据到服务器
     *
     * @param url             请求路径
     * @param params          参数
     * @param requestCallback 回调
     */
    void post(String url, Map<String, String> params, RequestCallback requestCallback);
}
