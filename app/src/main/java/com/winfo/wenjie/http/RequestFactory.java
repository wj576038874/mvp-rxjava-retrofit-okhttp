package com.winfo.wenjie.http;

/**
 * ProjectName: gdmsaecApp
 * PackageName: com.winfo.gdmsaec.app.gz.request
 * FileName: com.winfo.gdmsaec.app.gz.request.RequestFactory.java
 * Author: wenjie
 * Date: 2016-12-23 11:30
 * Description: 请求工厂类
 */
public class RequestFactory {
    /**
     * 获取xutils请求实例
     */
    public static RequestManager getXuitlsRequestMnager() {
        return XutilsRequestManager.getInstance();
    }

    /**
     * 获取okhttp请求实例
     */
    public static RequestManager getOkHttpRequestManager() {
        return OkHttpRequestManager.getInstance();
    }

}
