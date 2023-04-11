package com.yan.boot.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Rest 引导类
 *
 * @author 小马哥
 * @since 2018/5/27
 */
@SpringBootApplication(scanBasePackages = {
        "com.yan.boot.controller",
        "com.yan.boot.config"
})
public class SpringBootRestBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestBootstrap.class, args);
    }
}
