package com.yan.spring.boot.service.reactive;

import java.util.stream.Stream;

/**
 * Stream
 *
 * @author : Y
 * @since 2023/5/24 20:06
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)//0-9
                .filter(v -> v % 2 == 1) //判断数值->获取奇数
                .map(v -> v - 1) //奇数变偶数
                .reduce(Integer::sum) //聚合操作
                .ifPresent(System.out::println);//输出0+2+4+6+ 8
        //.forEach(System.out:println); //消费属性Consumer
    }
}