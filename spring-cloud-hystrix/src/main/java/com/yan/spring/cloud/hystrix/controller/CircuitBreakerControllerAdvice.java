package com.yan.spring.cloud.hystrix.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeoutException;

/**
 * 中级熔断Advice
 *
 * @author : Y
 * @since 2023/5/25 20:30
 */
@RestControllerAdvice(assignableTypes = CustomCircuitBreakerController.class)
public class CircuitBreakerControllerAdvice {

    @ExceptionHandler
    public void onTimeoutException(TimeoutException timeoutException,
                                   Writer writer) throws IOException {
        writer.write(errorContent(""));
        writer.flush();
        writer.close();
    }

    public String errorContent(String message) {
        return "Fault";
    }

}