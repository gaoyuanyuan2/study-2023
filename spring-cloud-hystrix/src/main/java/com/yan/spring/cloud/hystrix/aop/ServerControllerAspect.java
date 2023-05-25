package com.yan.spring.cloud.hystrix.aop;

import com.yan.spring.cloud.hystrix.enums.SemaphoreCircuitBreaker;
import com.yan.spring.cloud.hystrix.enums.TimeoutCircuitBreaker;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 高级版本切面实现
 *
 * @author : Y
 * @since 2023/5/25 20:39
 */
@Aspect
public class ServerControllerAspect {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Around("execution(* com.yan.spring.cloud.hystrix.controller.CustomCircuitBreakerController.advancedSay(..)) && args(message) ")
    public Object advancedSayInTimeout(ProceedingJoinPoint point, String message) throws Throwable {
        return doInvoke(point, message, 100);
    }

    @Around("execution(* com.yan.spring.cloud.hystrix.controller.CustomCircuitBreakerController.advancedSay(..)) && args(message) && @annotation(circuitBreaker)")
    public Object advancedSay2InTimeout(ProceedingJoinPoint point,
                                        String message,
                                        TimeoutCircuitBreaker circuitBreaker) throws Throwable {
        long timeout = circuitBreaker.timeout();
        return doInvoke(point, message, timeout);
    }



    public String errorContent(String message) {
        return "Fault";
    }

    private Semaphore semaphore = null;

    @Around("execution(* com.yan.spring.cloud.hystrix.controller.CustomCircuitBreakerController.advancedSay(..)) && args(message) && @annotation(circuitBreaker)")
    public Object advancedSay3InSemaphore(ProceedingJoinPoint point,
                                          String message,
                                          SemaphoreCircuitBreaker circuitBreaker) throws Throwable {
        int value = circuitBreaker.value();
        if (semaphore == null) {
            semaphore = new Semaphore(value);
        }
        Object returnValue = null;
        try {
            if (semaphore.tryAcquire()) {
                returnValue = point.proceed(new Object[]{message});
                Thread.sleep(1000);
            } else {
                returnValue = errorContent("");
            }
        } finally {
            semaphore.release();
            return returnValue;
        }

    }

    private Object doInvoke(ProceedingJoinPoint point, String message, long timeout) {

        Future<Object> future = executorService.submit(() -> {
            Object returnValue = null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable ex) {
            }
            return returnValue;
        });
        Object returnValue = null;
        try {
            returnValue = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); //取消执行
            returnValue = errorContent("");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

}