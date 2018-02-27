package com.winfo.wenjie.mvp.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.winfo.wenjie.R;
import com.winfo.wenjie.mvp.base.BaseMvpActivity;
import com.winfo.wenjie.mvp.base.BaseMvpPresenter;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.request.ApiService;
import com.winfo.wenjie.request.DialogSubscriber;
import com.winfo.wenjie.request.OkHttpUtils;
import com.winfo.wenjie.request.RequestParams;
import com.winfo.wenjie.request.ResultData;
import com.winfo.wenjie.utils.DialogUtils;
import com.winfo.wenjie.utils.JsonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.id_et_username)
    EditText etUserName;
    @BindView(R.id.id_et_password)
    EditText etPassword;
    @BindView(R.id.textview)
    TextView textView;
    @BindView(R.id.id_btn_login)
    Button btnLogin;
    private Dialog dialog;

    /**
     * p层对象 来调取p层的方法
     */
//    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dialog = DialogUtils.createLoadingDialog(this, "登陆中...");
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

    @Override
    public void setText(String result) {
        textView.setText(result);
    }

    @OnClick(R.id.id_btn_login)
    public void onClick() {
        /*
         * 调用登录方法进行登陆
         */
        mPresenter.login();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
