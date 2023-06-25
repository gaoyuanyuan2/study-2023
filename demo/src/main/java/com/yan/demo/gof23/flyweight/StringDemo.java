package com.yan.demo.gof23.flyweight;

import org.junit.jupiter.api.Test;

/**
 * String 编译时优化
 * int -128~127
 * long -128~127
 * @author : Y
 * @since 2023/6/25 20:13
 */
public class StringDemo {
    @Test
    public void testString() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";
        String s4 = "hel" + new String("lo");
        String s5 = new String("hello");
        String s6 = s5.intern();
        System.out.println(s1 == s2); //true
        System.out.println(s2 == s3); //true
        System.out.println(s1 == s4); //false
        System.out.println(s1 == s5); //false
        System.out.println(s1 == s5); //false
        System.out.println(s1 == s6);//true

    }
}