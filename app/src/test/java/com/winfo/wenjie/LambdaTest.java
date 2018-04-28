package com.winfo.wenjie;


import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageNmae: com.winfo.wenjie
 * Author: wenjie
 * FileName: com.winfo.wenjie.LambdaTest.java
 * Date: 2018-04-28 13:40
 * Description:
 */
public class LambdaTest {


    @Test
    public void testLambda() {
        //无参数无返回值 如果表达式只有一行则可以去掉{} 如下
        MyLambdaInterfaceOne myLambdaInterfaceOne = () -> System.out.println("myLambdaInterfaceOne");

//        MyLambdaInterfaceOne myLambdaInterfaceOne2 = () -> {
//            System.out.println("myLambdaInterfaceOne");
//        };

        myLambdaInterfaceOne.doSomeThing();
    }

    @Test
    public void testLambda2() {
        //当只有一个参数的时候可以简写成以下这样
        MyLambdaInterfaceTwo myLambdaInterfaceTwo = System.out::println;
//        MyLambdaInterfaceTwo myLambdaInterfaceTwo2 = a -> {
//          System.out.println(a);
//        };
        myLambdaInterfaceTwo.doSomeThing(100);
    }

    @Test
    public void testLambda3() {
        MyLambdaInterfaceThree myLambdaInterfaceThree = (str, a, b) -> System.out.println(str + (a + b));
        myLambdaInterfaceThree.doSomeThing("abc", 10, 12);
    }

    @Test
    public void testLambda4() {
        MyLambdaInterfaceFour<Integer> myLambdaInterfaceFour = (a, b) -> System.out.println(a + b);
        myLambdaInterfaceFour.doSomeThing(10, 21);
    }

    @Test
    public void testLambda5() {
        MyLambdaInterfaceFive myLambdaInterfaceFive = (a, b) -> a * b;
        int a = myLambdaInterfaceFive.doSomeThing(10, 11);
        System.out.println(a);
    }

    @Test
    public void testLambda6() {
        MyLambdaInterfaceSix<String> myLambdaInterfaceSix = (str, a) -> str + a;
        String s = myLambdaInterfaceSix.doSOmeThing("str", 10);
        System.out.println(s);
    }

    @Test
    public void testLambda7() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(123456);
            }
        }).start();

        new Thread(() -> System.out.println(123)).start();

    }

    @Test
    public void testLambda8() {
        Function<String, Integer> function = (str) -> {
            if (str.equals("str")) return 1;
            else return 2;
        };
        int a = function.apply("str");
        System.out.println(a);


        BinaryOperator.minBy((Comparator<String>) (str1, str2) -> {
            if (str1.equals("123")) return 2;
            else return 1;
        });

        UnaryOperator<String> unaryOperator = str -> "str";
        System.out.println(unaryOperator.apply("123"));


        ToLongFunction<String> toLongFunction = str -> 1;
        System.out.println(toLongFunction.applyAsLong("123"));

        ToLongBiFunction<Integer, Integer> toLongBiFunction = (aa, bb) -> aa + bb;
        System.out.println(toLongBiFunction.applyAsLong(10, 11));


        ToIntFunction<Integer> toIntFunction = c -> 11;
        System.out.println(toIntFunction.applyAsInt(1));

        Supplier<String> supplier = () -> "123";
        System.out.println(supplier.get());

        Predicate<Integer> predicate = d -> d == 1;
        System.out.println(predicate.test(1));

    }

    @Test
    public void testLambda9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        System.out.println("输出所有数据:");
//        Predicate<Integer> predicate = n -> true;
        eval(list, n -> true);
//        Predicate<Integer> predicate = n -> n % 2 == 0;
        eval(list, n -> n % 2 == 0);

//        Predicate<Integer> predicate = n -> n > 5;
        eval(list, n -> n > 5);
    }

    private void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n);
            }
        }
    }

    @Test
    public void testLambda10() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        funtion(list, i -> "function" + i);



    }

    private void funtion(List<Integer> list, Function<Integer, String> function) {
        for (Integer n : list) {
            System.out.println(function.apply(n));
        }
    }

}
