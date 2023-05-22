package com.yan.spring.cloud.consumer.controller;

import com.yan.spring.cloud.consumer.config.CustomizedLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务发现
 *
 * @author : Y
 * @since 2023/5/18 20:14
 */
@RestController
public class Client3Controller {
    /**
     * 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentServiceName;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @LoadBalanced利用注解来过滤,注入方和声明方同时使用
     */
    @Autowired
    @CustomizedLoadBalanced //依赖注入 Ribbon RestTemplate Bean
    private RestTemplate lbRestTemplate;

    @GetMapping("/invoke/{serviceName}/say")//-> /say
    public String invokeSay(@PathVariable String serviceName,
                            @RequestParam String message) {
        //输出响应
        return restTemplate.getForObject("/" + serviceName + "/say?message=" + message, String.class);

    }
    @GetMapping("lb/invoke/{serviceName}/say")//-> /say
    public String lbInvokeSay(@PathVariable String serviceName,
                            @RequestParam String message) {
        //输出响应
        return lbRestTemplate.getForObject("http://" + serviceName + "/say?message=" + message, String.class);

    }

}