package com.yan.spring.boot.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class SpringBootRestBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestBootstrap.class, args);
    }
}
