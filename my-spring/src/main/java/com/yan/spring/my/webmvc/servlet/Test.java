package com.yan.spring.my.webmvc.servlet;

public class Test {
    public static void main(String[] args) {
        System.out.println(containsChinese(null));
    }
    public static boolean containsChinese(String str) {
        String regex = "[\\u4e00-\\u9fa5]";
        return str.matches(".*" + regex + ".*");
    }
}
