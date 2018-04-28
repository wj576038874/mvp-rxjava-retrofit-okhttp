package com.winfo.wenjie;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        Observable.just(123).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return "asd";
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.print(s);
//            }
//        });

//        Observable.create(emitter -> {
//            emitter.onNext("asd");
//            emitter.onComplete();
//        }).subscribe(o -> System.out.println("保存本地缓存" + o));


        Observable.create((ObservableOnSubscribe<User>) emiter -> {
            emiter.onNext(new User("124", "asd"));
            emiter.onComplete();
        }).subscribe(user -> System.out.println(user.toString()));

//        Observable.create(new ObservableOnSubscribe<User>() {
//            @Override
//            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
//                User user = new User();
//                user.setPwd("123");
//                user.setUsername("admin");
//                emitter.onNext(user);
//            }
//        }).doOnNext(new Consumer<User>() {
//            @Override
//            public void accept(User user) throws Exception {
//                System.out.println("保存本地缓存" + user.toString());
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        System.out.println("rxjava=" + user);
//                    }
//                });

//        Observable.create((ObservableOnSubscribe<User>) emitter -> {
//            User user = new User();
//            user.setPwd("123");
//            user.setUsername("admin");
//            emitter.onNext(user);
//        }).doOnNext(user -> System.out.println("保存本地缓存" + user.toString())).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(user -> System.out.println("rxjava=" + user));

    }


    @FunctionalInterface
    interface MyLambdaInterface<T> {
        String doSomething(T t);
    }


    @Test
    public void testLambda() {
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;


        MyLambdaInterface<User> myLambdaInterface = user -> "124";
        System.out.println(myLambdaInterface.doSomething(new User("12", "123")));

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> a * b;

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


    class User {
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
}