package com.winfo.wenjie.request;

import android.app.Dialog;
import android.text.TextUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.request
 * @FileName: com.winfo.wenjie.request.DialogSubscriber.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:23
 * @Description: 订阅者
 * @Version:
 */
public abstract class DialogSubscriber<T> extends Subscriber<T> implements DialogCancelListener {

    private boolean isShowDialog;
    /**
     * 定义一个请求成功的抽象方法 子类必须实现并在实现中进行处理服务器返回的数据
     *
     * @param t 服务器返回的数据
     */
    protected abstract void onSuccess(T t);

    /**
     * 定义一个请求失败的抽象方法 子类必须实现并在实现中进行服务器返回数据的处理
     *
     * @param msg 服务器返回的错误信息
     */
    protected abstract void onFailure(String msg);

    private DialogHandler dialogHandler;

    /**
     *
     * @param dialog 对话框
     * @param isShowDialog 是否显示加载的对话框
     *                     这里的dialog要特别说明一下，把它当做DialogHandler构造参数传递进去，来进行具体的对话框的操作
     *                     其实是可以直接将context作为构造参数传递进去，在DialogHandler中通过context对象来创建dialog对象
     *                     因为感觉传递context对象不是很好，而且dialog加载框的提示语也会根据请求的不同而不同比如：
     *                     1、登陆请求 对话框需要是  登陆中...
     *                     2、加载数据  对话框需要时  数据加载中...
     *                     3、等等
     *                     所以我们直接将dialog的创建放在m层，作为参数传递进来，这样就可以适应很多请求
     *                     或许会有人觉得这事强迫症，可以直接  加载中...代替所有的啊，这样不是也可以吗？
     *                     的确可以但是，这样的话，需要传递context对象到这里了。为了避免传递context对象
     *                     所以选择这样的方式，也会有人说dialog携带了context引用啊。传conxtexthedialog都一样，
     *                     这里我就不处理了，你们自己想办法解决
     *                     isShowDialog这个就比较简单，请求时控制dialog是否显示，不是所有的请求都需要加载框比如下拉刷新
     *                     这些就不需要，所以这里灵活一点，自己根据具体的请求传递对应的值
     */
    public DialogSubscriber(Dialog dialog , boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
        dialogHandler = new DialogHandler(dialog , this);
    }

    /**
     * 显示对话框 发送一个显示对话框的消息给dialoghandler  由他自己处理（也就是dialog中hanldermesage处理该消息）
     */
    private void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(DialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 隐藏对话框 ....
     */
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(DialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialogHandler = null;
        }
    }

    /**
     * 请求开始
     * 先判断isShowDialog的值，如果为false就不显示对话框，为true才显示
     */
    @Override
    public void onStart() {
        if(isShowDialog){
            showProgressDialog();
        }
    }

    /**
     * 请求完成，隐藏对话框
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }


    /**
     * 请求出错
     * 这里异常处理的不是很完善，你们自己多写一些请求可能出现的异常
     * 进行捕获，这样可以直接将异常信息返回到view层可见页面，开发时一眼也可以看出具体的问题
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        String msg;
        if (e instanceof SocketTimeoutException) {
            msg = "请求超时。请稍后重试！";
        } else if (e instanceof ConnectException) {
            msg = "请求超时。请稍后重试！";
        } else {
            msg = "请求未能成功，请稍后重试！";
        }
        if (!TextUtils.isEmpty(msg)) {
            onFailure(msg);
        }
    }


    /**
     * 请求成功
     *
     * @param t
     */
    @Override
    public void onNext(T t) {
        /**
         * 请求成功将数据发出去
         */
        onSuccess(t);
    }


    /**
     * 请求被取消
     */
    @Override
    public void onCancel() {
        /**
         * 如果订阅者和被订阅者 没有取消订阅 则取消订阅 以取消网络请求
         */
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
