package com.winfo.wenjie.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.trello.rxlifecycle2.components.RxActivity;


/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.mvp.base
 * FileName: com.winfo.wenjie.mvp.base.BaseMvpActivity.java
 * Author: wenjie
 * Date: 2016-12-12 14:47
 * Description: BaseMvpActivity
 */
public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxActivity implements IBaseMvpView {

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
