package com.yan.spring.boot.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 *
 * @author : Y
 * @since 2023/5/23 21:24
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        println("当前线程");
        CompletableFuture.runAsync(()->{
                    System.out.println("异步");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).runAsync(()->{
                    System.out.println("异步");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).supplyAsync(() -> {
                    println("同步第一步返回\"Hello\"");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                }).thenApplyAsync(result -> {//异步?
                    println("同步第二 步在第一步结果+\",World\"");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return result + ",World";
                }).thenAccept(CompletableFutureTest::println) //控制输出
                .whenComplete((v, error) -> { //返回值void,异常->结束状态
                    long s2 = System.currentTimeMillis();
                    println("执行结束! cost:"+(s2-s1));
                }).join(); //等待执行结束
    }

    private static void println(String str) {
        System.out.println("输出:" + str + "--" + Thread.currentThread().getName());
    }
}