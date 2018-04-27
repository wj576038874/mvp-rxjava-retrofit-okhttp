package com.winfo.wenjie.mvp.view.impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.winfo.wenjie.R;
import com.winfo.wenjie.domain.Topic;
import com.winfo.wenjie.domain.UserDetail;
import com.winfo.wenjie.mvp.base.BaseMvpActivity;
import com.winfo.wenjie.mvp.base.MyApplication;
import com.winfo.wenjie.mvp.model.TopicsAndNews;
import com.winfo.wenjie.mvp.presenter.LoginPresenter;
import com.winfo.wenjie.mvp.view.ILoginView;
import com.winfo.wenjie.utils.ACache;
import com.winfo.wenjie.utils.DialogUtils;
import com.winfo.wenjie.utils.RxBus;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


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

    private String TAG = "winfo";

    private ACache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRxjava();
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

    private void testRxjava() {
        cache = ACache.get(MyApplication.getContext());

        /**
        doOnNext  请求成功之后  先接受到数据把数据保存在本地 往后传递
         */
//        Observable.create(new ObservableOnSubscribe<User>() {
//            @Override
//            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
//                User user = new User();
//                user.setPwd("123");
//                user.setUsername("admin");
//                emitter.onNext(user);
//                emitter.onComplete();
//                Log.e(TAG, "Current thread is " + Thread.currentThread().getName());
//            }
//        }).subscribeOn(Schedulers.io())//指定发射事件的线程
//                .observeOn(Schedulers.io())//指定的就是（doOnNext）订阅者接收事件的线程
//                .doOnNext(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        cache.put("user", user.toString());
//                        Log.e(TAG, "doOnNext Current thread is " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//指定的就是订阅者接收事件的线程
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        Log.e(TAG, "rxjava=" + user);
//                        Log.e(TAG, "cache=" + cache.getAsString("user"));
//                        Log.e(TAG, "Consumer Current thread is " + Thread.currentThread().getName());
//                    }
//                });

        /**
        map  map 操作符可以将一个 Observable 对象通过某种关系转换为另一个Observable 对象
         */
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                Log.e(TAG, "onNext Current thread is " + Thread.currentThread().getName());
//                emitter.onNext("123");
//                emitter.onComplete();
//            }
//        }).map(new Function<String, User>() {
//            @Override
//            public User apply(String str) throws Exception {
//                Log.e(TAG, "map Current thread is " + Thread.currentThread().getName());
//                return new User("1243", "admin");
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        Log.e(TAG, "map操作符=" + user);
//                        Log.e(TAG, "Current thread is " + Thread.currentThread().getName());
//                    }
//                });

        /**
        flatMap依赖请求，map操作符可以省略。以为flatMap本身发射的就是user数据
         */
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("123");
//                emitter.onComplete();
//            }
//        }).flatMap(new Function<String, ObservableSource<User>>() {
//            @Override
//            public ObservableSource<User> apply(String s) throws Exception {
//                return Observable.just(new User("1243", "admin"));
//            }
//        }).map(new Function<User, User>() {
//            @Override
//            public User apply(User user) throws Exception {
//                return user;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        Log.e(TAG, "flatMap操作符=" + user);
//                    }
//                });


        /**
         * concat
         * 采用 concat 操作符先读取缓存再通过网络请求获取数据
         想必在实际应用中，很多时候（对数据操作不敏感时）都需要我们先读取缓存的数据，
         如果缓存没有数据，再通过网络请求获取，随后在主线程更新我们的UI。
         */
        Observable<User> cache = Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                User user =new User("缓存", null);
                if (user.getPwd() != null){//缓存有数据 直接发射出去
                    emitter.onNext(user);
                }else{
                    emitter.onComplete();//去请求网络
                }
            }
        });

        Observable<User> net = Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                emitter.onNext(new User("网络", "admin"));
                emitter.onComplete();
            }
        });

        Observable.concat(cache , net)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Log.e(TAG, "concat操作符=" + user);
                    }
                });




    }

    class User implements Serializable {
        private String username;
        private String pwd;

        public User(String username, String pwd) {
            this.username = username;
            this.pwd = pwd;
        }

        public User() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }
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
