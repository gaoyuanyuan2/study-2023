package com.yan.spring.boot.servlets.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.yan.spring.boot.servlets.gateway.service")
public class SpringBootServletGatewayBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletGatewayBootstrap.class, args);
    }
}
