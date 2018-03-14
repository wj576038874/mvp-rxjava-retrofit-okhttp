package com.winfo.wenjie.mvp.base;

import android.app.Application;
import android.content.Context;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.base
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.base.MyApplication.java
 * Date: 2018-03-14 10:37
 * Description:
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
