package com.yan.demo.gof23.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器单例
 *
 * @author : Y
 * @since 2023/6/19 21:33
 */
public class ContainerSingleton {
    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String className) {
        Object instance = null;
        synchronized (className) {
            if (!ioc.containsKey(className)) {
                try {
                    instance = Class.forName(className).newInstance();
                    ioc.put(className, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return instance;
            } else {
                return instance;
            }
        }
    }
}