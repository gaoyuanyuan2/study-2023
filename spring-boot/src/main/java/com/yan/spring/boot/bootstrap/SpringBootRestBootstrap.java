package com.yan.spring.boot.bootstrap;

import com.yan.spring.boot.interceptor.GlobalBulkheadHandlerInterceptor;
import com.yan.spring.boot.interceptor.ResourceBulkheadHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * Spring Boot Rest 引导类
 *
 * @author 小马哥
 * @since 2018/5/27
 */
@SpringBootApplication(scanBasePackages = {
        "com.yan.spring.boot.controller",
        "com.yan.spring.boot.config"
})
@Import({ResourceBulkheadHandlerInterceptor.class, GlobalBulkheadHandlerInterceptor.class})
public class SpringBootRestBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestBootstrap.class, args);
    }
}
