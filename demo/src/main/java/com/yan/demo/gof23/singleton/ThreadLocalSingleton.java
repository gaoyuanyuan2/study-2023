package com.yan.demo.gof23.singleton;

/**
 * 线程单例
 *
 * @author : Y
 * @since 2023/6/19 21:20
 */
public class ThreadLocalSingleton {
    private final static ThreadLocal<ThreadLocalSingleton> singletonThreadLocal = new ThreadLocal<ThreadLocalSingleton>() {

        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getSingleton() {
        return singletonThreadLocal.get();
    }

    private ThreadLocalSingleton() {
    }
}