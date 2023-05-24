package com.yan.spring.boot.service;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 *
 * @author : Y
 * @since 2023/5/23 21:24
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        println("当前线程");
        CompletableFuture.supplyAsync(() -> {
                    println("第一步返回\"Hello\"");
                    return "Hello";
                }).thenApplyAsync(result -> {//异步?
                    println("第二 步在第一步结果+\",World\"");
                    return result + ",World";
                }).thenAccept(CompletableFutureTest::println) //控制输出
                .whenComplete((v, error) -> { //返回值void,异常->结束状态
                    println("执行结束!");
                }).join(); //等待执行结束
    }

    private static void println(String str) {
        System.out.println("输出:" + str + "--" + Thread.currentThread().getName());
    }
}