package com.winfo.wenjie.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.wenjie.R;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName com.winfo.wenjie.utils
 * @FileName: com.winfo.wenjie.utils.DialogUtils.java
 * @Author: wenjie
 * @Date: 2016-12-12 14:15
 * @Description: 对话框的工具类
 * @Version:
 */
public class DialogUtils {
    private static AnimationDrawable animationDrawable;

    /**
     * 获取一个类似加载的对话框
     *
     * @param context 上下文
     * @param msg     文字说明
     * @return 对话框的对象
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

		/*
         * 获得view填充器对象
		 */
        LayoutInflater inflater = LayoutInflater.from(context);
        /*
         * 得到加载view
         */
        View v = inflater.inflate(R.layout.loading_dialog, null);
        /*
         * 加载布局
         */
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);

        /*
         *  main.xml中的ImageView
         */
        final ImageView imgViewLoading = (ImageView) v.findViewById(R.id.img);


        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        /*
         *  加载动画
         */
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//                context, R.anim.loading_animation);
        /*
         *  使用ImageView显示动画
         */
//        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        if (msg != null && !msg.equals("")) {
            tipTextView.setText(msg);// 设置加载信息
        }

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);//
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        loadingDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                animationDrawable = (AnimationDrawable) imgViewLoading.getDrawable();
                animationDrawable.start();
            }
        });


        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                animationDrawable = (AnimationDrawable) imgViewLoading.getDrawable();
                animationDrawable.stop();
            }
        });

        return loadingDialog;
    }
}
