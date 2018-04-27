package com.winfo.wenjie.mvp.view.impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.winfo.wenjie.R;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.domain.UserDetail;
import com.winfo.wenjie.mvp.base.BaseMvpActivity;
import com.winfo.wenjie.mvp.model.TopicsAndNews;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.utils.DialogUtils;
import com.winfo.wenjie.utils.RxBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.mvp.view.impl
 * FileName: com.winfo.wenjie.mvp.view.impl.MainActivity.java
 * Author: wenjie
 * Date: 2016-12-12 14:47
 * Description: v层
 */
public class MainActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.username)
    EditText etUserName;

    @BindView(R.id.password)
    EditText etPassword;

    @BindView(R.id.result)
    TextView textView;

    @BindView(R.id.login)
    Button btnLogin;

    private Dialog dialog;

    @BindView(R.id.getme)
    Button btnGetMe;

    @BindView(R.id.userdetail)
    TextView tvUsetdetail;

    @BindView(R.id.tv_hebing1)
    TextView tvhebing1;

    @BindView(R.id.tv_hebing2)
    TextView tvhebing2;

    @BindView(R.id.btn_hebing)
    Button btnHebing;

    @BindView(R.id.id_textview)
    TextView id_textview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");


        RxBus.getInstance().toObservable(Topic.class)
                .subscribe(new Observer<Topic>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Topic s) {
                        etUserName.setText(s.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setText(String result) {
        textView.setText("登录成功！Token：\n" + result);
    }

    @Override
    public void setUserDetail(UserDetail userDetail) {
        tvUsetdetail.setText(userDetail.toString());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setBebingData(TopicsAndNews topicsAndNews) {
        tvhebing1.setText("第一个请求" + topicsAndNews.getNews().get(0).toString());
        tvhebing2.setText("第二个请求" + topicsAndNews.getTopics().get(0).toString());
    }

    @OnClick({R.id.login, R.id.getme, R.id.btn_hebing})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                 /*
                  * 调用登录方法进行登陆
                  */
                mPresenter.login();
//                startActivity(new Intent(this , Main2Activity.class));
                break;
            case R.id.getme:
                /*
                嵌套请求
                 */
                mPresenter.getMe();
//                "123".substring(10);
                break;

            case R.id.btn_hebing:
                /*
                合并请求
                 */
                mPresenter.bebing();
                break;
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
