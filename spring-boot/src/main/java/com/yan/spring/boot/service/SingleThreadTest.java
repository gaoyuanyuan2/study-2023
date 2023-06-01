package com.yan.spring.boot.service;

import java.util.concurrent.Executor;

/**
 * 单线程
 *
 * @author : Y
 * @since 2023/6/1 20:38
 */
public class SingleThreadTest {
    private static Executor executor = r -> r.run();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());//main
        executor.execute(() -> System.out.println(Thread.currentThread().getName()));//main
    }

}