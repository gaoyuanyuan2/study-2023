package com.yan.spring.boot.servlets.gateway;

import com.yan.spring.boot.servlets.gateway.service.ribbon.ZookeeperLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.yan.spring.boot.servlets.gateway.service")
@EnableScheduling
public class SpringBootServletGatewayBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletGatewayBootstrap.class, args);
    }

    @Bean
    public ZookeeperLoadBalancer zookeeperLoadBalancer(
            DiscoveryClient discoveryClient) {
        return new ZookeeperLoadBalancer(discoveryClient);
    }

}
