package com.winfo.wenjie.mvp.model.impl;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.mvp.model.OnLoadDatasListener;
import com.winfo.wenjie.mvp.model.TopicModel;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.request.RequestSubscriber;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.model.impl
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.model.impl.TopicModelImpl.java
 * Date: 2018-04-24 14:50
 * Description:
 */
public class TopicModelImpl implements TopicModel {

    @Override
    public void loadTopicList(LifecycleProvider<ActivityEvent> lifecycle ,final OnLoadDatasListener<List<Topic>> onLoadDatasListener) {
        Observable<List<Topic>> observable = OkHttpUtils.getRetrofit().create(ApiService.class).loadTopicList(10);

        Observer<List<Topic>> observer = new RequestSubscriber<List<Topic>>() {
            @Override
            protected void onSuccess(List<Topic> topics) {
                onLoadDatasListener.onSuccess(topics);
            }

            @Override
            protected void onFailure(String msg) {
                onLoadDatasListener.onFailure(msg);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycle.<List<Topic>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
    }
}
