package com.yan.spring.cloud.hystrix.controller;

import com.yan.spring.cloud.hystrix.enums.SemaphoreCircuitBreaker;
import com.yan.spring.cloud.hystrix.enums.TimeoutCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 自定义熔断
 *
 * @author : Y
 * @since 2023/5/25 20:12
 */
@RestController("my")
public class CustomCircuitBreakerController {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static final Random random = new Random();

    /**
     * 初级版本
     *
     * @param message 消息
     * @return
     * @throws Exception
     */
    @GetMapping("/primary/say")
    public String primarySay(@RequestParam String message) throws Exception {
        Future<String> future = executorService.submit(() -> doSay2(message));
        // 100毫秒超时
        String returnValue = null;
        try {
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            //超级容错=执行错误或超时
            returnValue = errorContent(message);
        }
        return returnValue;
    }

    /**
     * 中级版本
     *
     * @param message 消息
     * @return
     * @throws Exception
     */
    @GetMapping("/middle/say")
    public String middleSay(@RequestParam String message) throws Exception {
        Future<String> future = executorService.submit(() -> doSay2(message));
        // 100毫秒超时
        String returnValue = null;
        try {
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw e;
        }
        return returnValue;
    }

    /**
     * 高级版本1
     *
     * @param message 消息
     * @return
     * @throws Exception
     */
    @GetMapping("/advanced/say")
    public String advancedSay(@RequestParam String message) throws Exception {
        return doSay2(message);
    }
    /**
     * 高级版本2
     *
     * @param message 消息
     * @return
     * @throws Exception
     */
    @GetMapping("/advanced/say")
    @TimeoutCircuitBreaker(timeout = 100)
    public String advancedSay2(@RequestParam String message) throws Exception {
        return doSay2(message);
    }
    /**
     * 高级版本3
     *
     * @param message 消息
     * @return
     * @throws Exception
     */
    @GetMapping("/advanced/say3")
    @SemaphoreCircuitBreaker(5)
    public String advancedSay3(@RequestParam String message) throws Exception {
        return doSay2(message);
    }

    private String errorContent(String message) {
        return "ERROR!";
    }

    private String doSay2(String message) throws InterruptedException {
        //如果随机时间大于100 ,那么触发容错
        int value = random.nextInt(200);
        System.out.println("say2( costs " + value + "ms.");
        TimeUnit.MILLISECONDS.sleep(value);
        return message;
    }
}