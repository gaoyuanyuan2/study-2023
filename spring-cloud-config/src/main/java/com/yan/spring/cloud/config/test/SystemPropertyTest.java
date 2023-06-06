package com.yan.spring.cloud.config.test;

/**
 * 系统属性测试
 *
 * @author : Y
 * @since 2023/6/6 20:36
 */
public class SystemPropertyTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.age", "10"));
        //将System Properties 转换为Integer 类型
        System.out.println(Integer.getInteger("user.age", 20));
        System.out.println(Boolean.getBoolean("user.male"));

    }
}