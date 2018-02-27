package com.winfo.wenjie.mvp.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.winfo.wenjie.R;
import com.winfo.wenjie.mvp.base.BaseMvpActivity;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.utils.DialogUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
        textView.setText("登录成功！Token：\n"+result);
    }

    @OnClick(R.id.login)
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
