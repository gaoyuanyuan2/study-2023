package com.yan.spring.boot.service.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flux
 *
 * @author : Y
 * @since 2023/5/24 20:10
 */
public class FluxTest {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)//直接执行
                .filter(v -> v % 2 == 1) //判断数值->获取奇数
                .map(v -> v - 1) //奇数变偶数
                .reduce(Integer::sum) //聚合操作
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        //.subscribe(ReactorDemo::println) //订阅才执行
        Thread.sleep(1000);

    }

    private static void printIn(Object message) {
        System.out.printf("[线程: %s] %s\n",
                Thread.currentThread().getName(), //当前线程名称
                message);
    }
}