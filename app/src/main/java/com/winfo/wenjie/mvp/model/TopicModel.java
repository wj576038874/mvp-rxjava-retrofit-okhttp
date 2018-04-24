package com.winfo.wenjie.mvp.model;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.winfo.wenjie.domain.Topic;

import java.util.List;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.model
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.model.TopicModel.java
 * Date: 2018-04-24 14:49
 * Description:
 */
public interface TopicModel {
    void loadTopicList(LifecycleProvider<ActivityEvent> lifecycle ,OnLoadDatasListener<List<Topic>> onLoadDatasListener);
}
