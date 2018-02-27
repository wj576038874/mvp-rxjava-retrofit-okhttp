package com.winfo.wenjie.request;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName: com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.ResponseResult.java
 * Author: wenjie
 * Date: 2016-12-29 16:23
 * Description: 统一服务器返回的所有数据结构泛型 这里需要和服务器协商数据返回格式
 */
public class ResponseResult<T> {

    /**
     * 返回结果标识
     */
    private int result;

    /**
     * 结果标识所对应的信息
     */
    private String msg;

    /**
     * 结果数据
     */
    private T data;


    public int getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
