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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
         doOnNext 它的作用是让订阅者在接收到数据之前干点有意思的事情。
         假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
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
//                .observeOn(Schedulers.io())//指定的就是（doOnNext）的线程
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

        Observable.create((ObservableOnSubscribe<User>) emitter -> {
            User user = new User();
            user.setPwd("123");
            user.setUsername("admin");
            emitter.onNext(user);
            emitter.onComplete();
            Log.e(TAG, "Current thread is " + Thread.currentThread().getName());
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(user -> {
                    cache.put("user", user.toString());
                    Log.e(TAG, "doOnNext Current thread is " + Thread.currentThread().getName());
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "rxjava=" + user);
                    Log.e(TAG, "cache=" + cache.getAsString("user"));
                    Log.e(TAG, "Consumer Current thread is " + Thread.currentThread().getName());
                });

        //-----------------------------------------------------------------------------------------------------

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

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Log.e(TAG, "onNext Current thread is " + Thread.currentThread().getName());
            emitter.onNext("123");
            emitter.onComplete();
        }).map(str -> {
            Log.e(TAG, "map Current thread is " + Thread.currentThread().getName());
            return new User("1243", "admin");
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "map操作符=" + user);
                    Log.e(TAG, "Current thread is " + Thread.currentThread().getName());
                });

        //-----------------------------------------------------------------------------------------------------

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

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("123");
            emitter.onComplete();
        }).flatMap(s -> {
            if (s.equals("123")) {
                return Observable.just(new User("1243", "admin"));
            } else {
                return null;
            }
        }).map(user -> user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> Log.e(TAG, "flatMap操作符=" + user));

        //-----------------------------------------------------------------------------------------------------

        /**
         * concat
         * 采用 concat 操作符先读取缓存再通过网络请求获取数据
         想必在实际应用中，很多时候（对数据操作不敏感时）都需要我们先读取缓存的数据，
         如果缓存没有数据，再通过网络请求获取，随后在主线程更新我们的UI。
         */
//        Observable<User> cache = Observable.create(new ObservableOnSubscribe<User>() {
//            @Override
//            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
//                User user =new User("缓存", null);
//                if (user.getPwd() != null){//缓存有数据 直接发射出去
//                    emitter.onNext(user);
//                }else{
//                    emitter.onComplete();//去请求网络
//                }
//            }
//        });
//
//        Observable<User> net = Observable.create(new ObservableOnSubscribe<User>() {
//            @Override
//            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
//                emitter.onNext(new User("网络", "admin"));
//                emitter.onComplete();
//            }
//        });
//
//        Observable.concat(cache , net)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        Log.e(TAG, "concat操作符=" + user);
//                    }
//                });

        Observable<User> cache = Observable.create(emitter -> {
            User user = new User("缓存", null);
            if (user.getPwd() != null) {//缓存有数据 直接发射出去
                emitter.onNext(user);
            } else {
                emitter.onComplete();//去请求网络
            }
        });

        Observable<User> net = Observable.create(emitter -> {
            emitter.onNext(new User("网络", "admin"));
            emitter.onComplete();
        });

        Observable.concat(cache, net)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> Log.e(TAG, "concat操作符=" + user));

        //-----------------------------------------------------------------------------------------------------

        /**
         *interval 想必即时通讯等需要轮训的任务在如今的 APP 中已是很常见，而 RxJava 2.x 的 interval 操作符可谓完美地解决了我们的疑惑
         * initialDelay 代表第一次发送时的一个延迟时间
         * period 后续每次的事件间隔时间
         */
//        Observable.interval(10, 3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e(TAG, "interval操作符=" + aLong);
//                            /*
//                            04-28 10:02:56.596 28179-28179/com.winfo.wenjie E/winfo: interval操作符=0
//                            04-28 10:02:59.595 28179-28179/com.winfo.wenjie E/winfo: interval操作符=1
//                            04-28 10:03:02.595 28179-28179/com.winfo.wenjie E/winfo: interval操作符=2
//                            04-28 10:03:05.595 28179-28179/com.winfo.wenjie E/winfo: interval操作符=3
//                             */
//                    }
//                });

        Observable.interval(10, 3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Log.e(TAG, "interval操作符=" + aLong);
                });

        //-----------------------------------------------------------------------------------------------------

        /**
         * timer延迟操作
         * 延迟15秒钟之后操作
         */
//        Observable.timer(15, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e(TAG, "timer操作符=" + aLong);
//                        /*
//                           04-28 10:01:25.136 27990-27990/com.winfo.wenjie E/winfo: timer操作符=0
//                         */
//                    }
//                });

        Observable.timer(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> Log.e(TAG, "timer操作符=" + aLong));

        //-----------------------------------------------------------------------------------------------------

        /**
         * Filter 你会很常用的，它的作用也很简单，过滤器嘛。可以接受一个参数，让其过滤掉不符合我们条件的值
         *
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                Log.e(TAG, "create的线程=" + Thread.currentThread().getName());
//                /*
//                 * 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: create的线程=RxCachedThreadScheduler-1
//                 */
//                emitter.onNext(1);
//                emitter.onNext(-78);
//                emitter.onNext(11);
//                emitter.onNext(40);
//                emitter.onNext(0);
//                emitter.onNext(-3);
//                emitter.onComplete();
//            }
//        }).filter(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) throws Exception {
//                Log.e(TAG, "filter接收的数据=" + integer);
//                Log.e(TAG, "filter接收的数据的线程=" + Thread.currentThread().getName());
//                /*
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=-78
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=11
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=40
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=0
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据=-3
//                 04-28 09:58:55.184 27752-27781/com.winfo.wenjie E/winfo: filter接收的数据的线程=RxCachedThreadScheduler-1
//                 */
//                //如果返回false则订阅者不会接收到数据,当过滤器接收到一个数据之后，我们拿到这个数据进行判断，符合条件就返回true发送给订阅者
//                //不符合条件就返回false把这条数据过滤掉
//                return integer > 0;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "filter过滤之后的数据=" + integer);
//                        Log.e(TAG, "filter过滤之后的线程=" + Thread.currentThread().getName());
//                        /*
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的数据=1
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的线程=main
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的数据=11
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的线程=main
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的数据=40
//                         04-28 09:58:55.232 27752-27752/com.winfo.wenjie E/winfo: filter过滤之后的线程=main
//                         */
//                    }
//                });

        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(-78);
            emitter.onNext(11);
            emitter.onNext(40);
            emitter.onNext(0);
            emitter.onNext(-3);
            emitter.onComplete();
        }).filter(integer -> integer > 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.e(TAG, "filter过滤之后的数据=" + integer));

        //-----------------------------------------------------------------------------------------------------

        /**
         * skip 很有意思，其实作用就和字面意思一样，接受一个 long 型参数 count ，代表跳过 count 个数目开始接收
         */
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("A");
//                emitter.onNext("D");
//                emitter.onNext("C");
//                emitter.onNext("D");
//                emitter.onNext("E");
//                emitter.onNext("F");
//                emitter.onNext("G");
//                emitter.onComplete();
//            }
//        }).skip(3).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.e(TAG, "skip之后的数据=" + s);
//                        /*
//                         04-28 09:58:06.326 27570-27570/? E/winfo: skip之后的数据=D
//                         04-28 09:58:06.326 27570-27570/? E/winfo: skip之后的数据=E
//                         04-28 09:58:06.326 27570-27570/? E/winfo: skip之后的数据=F
//                         04-28 09:58:06.326 27570-27570/? E/winfo: skip之后的数据=G
//                         */
//                    }
//                });

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("A");
            emitter.onNext("D");
            emitter.onNext("C");
            emitter.onNext("D");
            emitter.onNext("E");
            emitter.onNext("F");
            emitter.onNext("G");
            emitter.onComplete();
        }).skip(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e(TAG, "skip之后的数据=" + s);
                    Log.e(TAG, "skip之后的数据=" + s);
                });

        //-----------------------------------------------------------------------------------------------------

        /**
         * take 接受一个 long 型参数 count ，代表至多接收 count 个数据。
         */
//        Observable.fromArray(1, 2, 3, 4, 5, 6)
//                .take(3)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "take之后的数据=" + integer);
//                        /*
//                        04-28 10:05:37.727 28593-28593/? E/winfo: take之后的数据=1
//                        04-28 10:05:37.727 28593-28593/? E/winfo: take之后的数据=2
//                        04-28 10:05:37.727 28593-28593/? E/winfo: take之后的数据=3
//                         */
//                    }
//                });

        Observable.fromArray(1, 2, 3, 4, 5, 6)
                .take(3).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.e(TAG, "take之后的数据=" + integer));


        //-----------------------------------------------------------------------------------------------------

        /**
         * just 没什么好说的，其实在前面各种例子都说明了，就是一个简单的发射器依次调用 onNext() 方法。
         * 内部调用的就是fromArray 但是just最多传递10个对象
         */
//        Observable.just(1 , 2, 3, 4, 5, 6, 7, 8, 9, 23)
//        .subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e(TAG, "just发送的数据=" + integer);
//            }
//        });

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 23)
                .subscribe(integer -> Log.e(TAG, "just发送的数据=" + integer));


        //-----------------------------------------------------------------------------------------------------

        /**
         * Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
         */
//        Single.just(2).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e(TAG, "just发送的数据=" + integer);
//            }
//        });

        Single.just(2).subscribe(integer -> Log.e(TAG, "just发送的数据=" + integer));


        //-----------------------------------------------------------------------------------------------------

        /**
         * distinct 去重操作符，简单的作用就是去重。
         */
//        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
//                .distinct()
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "distinct之后的数据=" + integer);
//                        /*
//                        04-28 10:12:09.653 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=1
//                        04-28 10:12:09.653 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=2
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=3
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=4
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=5
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=6
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=7
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=8
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=9
//                        04-28 10:12:09.654 28937-28937/com.winfo.wenjie E/winfo: distinct之后的数据=0
//                         */
//                    }
//                });

        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
                .distinct()
                .subscribe(integer -> Log.e(TAG, "distinct之后的数据=" + integer));

        //-----------------------------------------------------------------------------------------------------

//        Button button = new Button(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        button.setOnClickListener(view -> Toast.makeText(this, "sad", Toast.LENGTH_SHORT).show());

    }

    public void doSomething(String s) {
        System.out.println(s);
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
