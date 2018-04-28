package com.winfo.wenjie;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie
 * Author: wenjie
 * FileName: com.winfo.wenjie.MyLambdaInterfaceFour.java
 * Date: 2018-04-28 14:03
 * Description: 无返回值 携带泛型参数
 */
public interface MyLambdaInterfaceFour<T> {
    void doSomeThing(T t, int a);
}
