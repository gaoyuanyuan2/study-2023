package com.yan.spring.cloud.stream.kafka.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Listener
 * 注意:相同的编程模型重复执行不同的编程模型轮流执行
 *
 * @author : Y
 * @since 2023/5/30 19:59
 */
public class ListenerService {

    @StreamListener("input")
    public void onMessage(byte[] data) { //注解编程
        System.out.println(" onMessage(byte[]): " + new String(data));
    }

    @StreamListener("input")
    public void onMessage(String data) { //注解编程
        System.out.println(" onMessage: " + data);
    }

    @StreamListener("input")
    public void onMessage2(String data) { //注解编程
        System.out.println(" onMessage2: " + data);
    }

    @ServiceActivator(inputChannel = "input") // Spring Integration注解驱动
    public void onServiceActivator(String data) {
        System.out.println("onServiceActivator(String):" + data);
    }

}