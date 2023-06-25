package com.yan.demo.gof23.singleton;

/**
 * 懒汉
 *
 * @author : Y
 * @since 2023/6/19 21:09
 */
public class SimpleSingleton {

    private final static SimpleSingleton singleton = new SimpleSingleton();

    public static SimpleSingleton getSingleton(){
        return singleton;
    }

    private SimpleSingleton() {
    }
}