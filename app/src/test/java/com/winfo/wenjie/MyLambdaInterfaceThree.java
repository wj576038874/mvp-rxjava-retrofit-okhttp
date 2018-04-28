package com.winfo.wenjie;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie
 * Author: wenjie
 * FileName: com.winfo.wenjie.MyLambdaInterfaceThree.java
 * Date: 2018-04-28 13:58
 * Description: 无返回值有两个或者多个参数的接口函数
 */
@FunctionalInterface
public interface MyLambdaInterfaceThree {
    void doSomeThing(String str, int a, Integer b);

    static String ss() {
        return "123";
    }
}
