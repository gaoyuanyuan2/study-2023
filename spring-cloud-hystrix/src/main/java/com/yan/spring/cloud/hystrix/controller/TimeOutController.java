package com.yan.spring.cloud.hystrix.controller;

import com.yan.spring.cloud.hystrix.enums.Limited;
import com.yan.spring.cloud.hystrix.enums.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 超时
 *
 * @author : Y
 * @since 2023/6/10 14:35
 */
@RestController
public class TimeOutController {

    @Autowired
    private  Environment environment;

    private String getPort() {
        return environment.getProperty("local.server.port");
    }
    @Limited(1)
    @Timeout(value = 50L, fallback = "fallback")
    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable String message) {
        await();
        return "[ECHO:" + getPort() + "] " + message;
    }

    private final Random random = new Random();

    private void await() {
        long wait = random.nextInt(100);
        System.out.printf("[当前线程 : %s] 当前方法执行(模型) 消耗 %d 毫秒\n", Thread.currentThread().getName(), wait);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}