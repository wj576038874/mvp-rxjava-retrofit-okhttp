package com.winfo.wenjie.mvp.presenter;

import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.mvp.base.BaseMvpPresenter;
import com.winfo.wenjie.mvp.model.OnLoadDatasListener;
import com.winfo.wenjie.mvp.model.TopicModel;
import com.winfo.wenjie.mvp.model.impl.TopicModelImpl;
import com.winfo.wenjie.mvp.view.TopicView;
import com.winfo.wenjie.mvp.view.impl.Main2Activity;

import java.util.List;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.presenter
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.presenter.TopicPresenter.java
 * Date: 2018-04-24 14:48
 * Description:
 */
public class TopicPresenter extends BaseMvpPresenter<TopicView> {

    private TopicModel topicModel;

    public TopicPresenter(){
        topicModel = new TopicModelImpl();
    }

    public void getTopics(){
        if (mView == null)return;
        mView.getLoadingDialog().show();
        Main2Activity main2Activity = (Main2Activity) mView;
        topicModel.loadTopicList(main2Activity ,new OnLoadDatasListener<List<Topic>>() {
            @Override
            public void onSuccess(List<Topic> topics) {
                if (mView == null)return;
                mView.getLoadingDialog().dismiss();
                mView.setTopics(topics);
            }

            @Override
            public void onFailure(String error) {
                if (mView == null)return;
                mView.getLoadingDialog().dismiss();
                mView.showMsg(error);
            }
        });
    }
}
