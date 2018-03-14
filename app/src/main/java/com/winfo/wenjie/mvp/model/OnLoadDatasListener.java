package com.winfo.wenjie.mvp.model;

/**
 * ProjectName: DiycodeApp
 * PackageName: com.wenjie.diycode.mvp.model
 * FileName: com.wenjie.diycode.mvp.model.OnLoadDatasListener.java
 * Author: wenjie
 * Date: 2017-08-29 11:20
 * Description:
 */
public interface OnLoadDatasListener<T> {

    /**
     * 成功
     * @param t 数据
     */
    void onSuccess(T t);


    /**
     * 失败
     * @param error 错误信息
     */
    void onFailure(String error);

}
