package com.winfo.wenjie.request;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName: com.winfo.wenjie.request
 * @FileName: com.winfo.wenjie.request.Result.java
 * @Author: wenjie
 * @Date: 2017-10-19 11:01
 * @Description:
 * @Version:
 */
public class Result {
    private int httpCode;
    private String message;
    private String httpResult;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpResult() {
        return httpResult;
    }

    public void setHttpResult(String httpResult) {
        this.httpResult = httpResult;
    }
}
