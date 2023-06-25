package com.yan.demo.gof23.singleton;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 破坏单例
 *
 * @author : Y
 * @since 2023/6/19 21:13
 */
public class DestroySingletonDemo {
    @Test
    public void testDestroy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = SimpleSingleton.class;
        Constructor c = clazz.getDeclaredConstructor();
        c.setAccessible(true);
        System.out.println(c);
        SimpleSingleton singleton = (SimpleSingleton) c.newInstance();
        System.out.println(singleton);
    }
    @Test
    public void testEnumDestroy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = EnumSingleton.class;
        Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
        c.setAccessible(true);
        System.out.println(c);
        EnumSingleton singleton = (EnumSingleton) c.newInstance();
        System.out.println(singleton);
    }
}