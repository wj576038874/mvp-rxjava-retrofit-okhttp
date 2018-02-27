package com.winfo.wenjie.mvp.base;

import java.lang.ref.WeakReference;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.BaseMvpPresenter.java
 * Date: 2017/12/11 18:39
 * Description:
 */

public class BaseMvpPresenter<V extends IBaseMvpView> {

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
