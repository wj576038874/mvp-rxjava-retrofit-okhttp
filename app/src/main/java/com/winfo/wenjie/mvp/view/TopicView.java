package com.winfo.wenjie.mvp.view;

import android.app.Dialog;

import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.mvp.base.IBaseMvpView;

import java.util.List;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.view
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.view.TopicView.java
 * Date: 2018-04-24 14:47
 * Description:
 */
public interface TopicView extends IBaseMvpView{

    void setTopics(List<Topic> topics);

    Dialog getLoadingDialog();

    void showMsg(String msg);
}
