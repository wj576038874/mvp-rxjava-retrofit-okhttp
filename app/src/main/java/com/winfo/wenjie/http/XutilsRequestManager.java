package com.winfo.wenjie.http;

import java.util.Map;

/**
 * ProjectName: gdmsaecApp
 * PackageName: com.winfo.gdmsaec.app.gz.request
 * FileName: com.winfo.gdmsaec.app.gz.request.XutilsRequestManager.java
 * Author: wenjie
 * Date: 2016-12-23 11:26
 * Description: xutils请求封装类
 */
public class XutilsRequestManager implements RequestManager {
//    private HttpUtils http = new HttpUtils(30000);

    @Override
    public void get(String url, final RequestCallback requestCallback) {
//        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//
//            @Override
//            public void onSuccess(ResponseInfo<String> response) {
//                requestCallback.onSuccess(response.result);
//            }
//
//            @Override
//            public void onFailure(HttpException error, String msg) {
//                requestCallback.onFailure(error);
//            }
//        });
    }

    @Override
    public void post(String url, String requestBoduJson, final RequestCallback requestCallback) {
//        RequestParams params = new RequestParams();
//        params.setContentType("application/json");
//        try {
//            params.setBodyEntity(new StringEntity(requestBoduJson, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
//
//            @Override
//            public void onFailure(HttpException error, String msg) {
//                requestCallback.onFailure(error);
//            }
//
//            @Override
//            public void onSuccess(ResponseInfo<String> response) {
//                requestCallback.onSuccess(response.result);
//            }
//        });
    }

    @Override
    public void post(String url, Map<String, String> params, final RequestCallback requestCallback) {
//        RequestParams requestbody = new RequestParams();
//        for(String str : params.keySet()){
//            requestbody.addBodyParameter(str, params.get(str));
//        }
//        http.send(HttpRequest.HttpMethod.POST, url, requestbody, new RequestCallBack<String>() {
//
//            @Override
//            public void onFailure(HttpException error, String arg1) {
//                requestCallback.onFailure(error);
//            }
//
//            @Override
//            public void onSuccess(ResponseInfo<String> response) {
//                requestCallback.onSuccess(response.result);
//            }
//        });
    }

    /**
     * 获取xutils请求的管理实例
     *
     * @return XutilsRequestManage单例对象
     */
    static XutilsRequestManager getInstance() {
        return new XutilsRequestManager();
    }
}
