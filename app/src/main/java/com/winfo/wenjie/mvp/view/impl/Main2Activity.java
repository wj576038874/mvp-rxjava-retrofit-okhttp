package com.winfo.wenjie.mvp.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.trello.navi2.component.NaviActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.navi.NaviLifecycle;
import com.winfo.wenjie.R;
import com.winfo.wenjie.domain.Token;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.mvp.base.BaseMvpActivity;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.presenter.TopicPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.mvp.view.TopicView;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.DialogSubscriber;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.utils.DialogUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends BaseMvpActivity<TopicView, TopicPresenter> implements View.OnClickListener,TopicView {


    private EditText editText;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);

        loadingDialog = DialogUtils.createLoadingDialog(this , "加载中...");

        button.setOnClickListener(this);


        Observable<Long> observable = Observable.interval(3,3 , TimeUnit.SECONDS);

        Observer<Long> observer = new DialogSubscriber<Long>(new Dialog(this)) {
            @Override
            protected void onSuccess(Long l) {
                Toast.makeText(Main2Activity.this , l+"" , Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onFailure(String msg) {
                Toast.makeText(Main2Activity.this , msg , Toast.LENGTH_SHORT).show();
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(this.<Long>bindToLifecycle())
//                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);

    }

    @Override
    protected TopicPresenter createPresenter() {
        return new TopicPresenter();
    }

    @Override
    public void onClick(View v) {
//        Topic topic = new Topic();
//        topic.setTitle("根据传递的 eventType 类型返回特定类型(eventType)的 被观察者");
//        RxBus.getInstance().post(topic);

        mPresenter.getTopics();

    }

    @Override
    public void setTopics(List<Topic> topics) {
        editText.setText(topics.get(0).getTitle());
    }

    @Override
    public Dialog getLoadingDialog() {
        return loadingDialog;
    }

    @Override
    public void showMsg(String msg) {

    }
}
