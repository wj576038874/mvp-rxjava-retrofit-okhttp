package com.winfo.wenjie.mvp.base;

import java.lang.ref.WeakReference;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.mvp.base
 * FileName: com.winfo.wenjie.mvp.base.BaseMvpPresenter.java
 * Author: wenjie
 * Date: 2016-12-12 14:47
 * Description: BaseMvpPresenter
 */
public class BaseMvpPresenter<V extends IBaseMvpView> {

    /**
     * v层泛型引用
     */
    protected V mView;

    private WeakReference<V> weakReferenceView;


    public void attachMvpView(V view) {
        weakReferenceView = new WeakReference<>(view);
        this.mView = weakReferenceView.get();
    }


    public void detachMvpView() {
        weakReferenceView.clear();
        weakReferenceView = null;
        mView = null;
    }


}
