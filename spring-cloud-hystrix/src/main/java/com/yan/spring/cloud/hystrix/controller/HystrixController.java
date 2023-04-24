package com.yan.spring.cloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Y
 * @since 2023/4/24 19:43
 */
@RestController
public class HystrixController {

    public static final Random random = new Random();

    @HystrixCommand(fallbackMethod = "errorHelloWorld",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            })
    @GetMapping("hello-world")
    public String helloWorld() throws InterruptedException {
        //如果题机时间大于J00 ，郡么触发容错
        int value = random.nextInt(200);
        System.out.println("Hello,World!耗时" + value);
        Thread.sleep(value);
        return "Hello,World!";
    }

    public String errorHelloWorld() {
        return "Error,Hello,World!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> helloWorld = executorService.submit(() -> {
            int value = random.nextInt(200);
            System.out.println("Hello,World!耗时" + value);
            Thread.sleep(value);
            return "Hello,World!";
        });
        helloWorld.get(100, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    public void test() {
        Single.just("Hello,World!").subscribeOn(Schedulers.immediate())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("执行结束");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("熔断保护！");
                    }

                    @Override
                    public void onNext(String s) {
                        int value = random.nextInt(200);
                        if (value > 100) {
                            throw new RuntimeException("超时！");
                        }
                        System.out.println("Hello,World!耗时" + value);
                    }
                });
    }
}