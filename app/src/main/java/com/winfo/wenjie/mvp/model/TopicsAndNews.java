package com.winfo.wenjie.mvp.model;

import com.winfo.wenjie.domain.New;
import com.winfo.wenjie.domain.Topic;

import java.util.List;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie.mvp.model
 * Author: wenjie
 * FileName: com.winfo.wenjie.mvp.model.TopicsAndNews.java
 * Date: 2018-03-14 11:28
 * Description:
 */
public class TopicsAndNews {

    private List<Topic> topics;

    private List<New> news;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<New> getNews() {
        return news;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }
}
