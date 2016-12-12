package com.winfo.wenjie.mvp.view.impl;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.winfo.wenjie.R;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.utils.DialogUtils;

public class MainActivity extends AppCompatActivity implements ILoginView , View.OnClickListener{

    private Dialog dialog;

    private Button button;
    private TextView textView;
    private EditText editText1 , editText2;
    /**
     * p层对象 来调取p层的方法
     */
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 实例化p  构造方法需要一个实现了ILoginView的类 当前activity实现了ILoginView 所以直接传this即可
         */
        loginPresenter = new LoginPresenter(this);

        dialog = DialogUtils.createLoadingDialog(this,"登陆中...");
        button = (Button) findViewById(R.id.button);
        if(button != null){
            button.setOnClickListener(this);
        }
        textView = (TextView) findViewById(R.id.textview);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public String getUserName() {
        return editText1.getText().toString();
    }

    @Override
    public String getPassword() {
        return editText2.getText().toString();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setText(String result) {
        textView.setText(result);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            /**
             * 调用登录方法进行登陆
             */
            loginPresenter.login();
        }
    }
}
