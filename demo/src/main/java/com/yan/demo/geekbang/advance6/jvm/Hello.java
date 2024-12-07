package com.yan.demo.geekbang.advance6.jvm;

/**
 * Hello
 *
 * @author : Y
 * @since 2024/12/7 10:07
 */
public class Hello {
    public static void main(String[] args) {
        hello();
    }

    public static void hello() {
        for (int i = 0; i < 10; i++) {
            if (i == 2) {
                System.out.println(i+9);
            }
        }
    }
}