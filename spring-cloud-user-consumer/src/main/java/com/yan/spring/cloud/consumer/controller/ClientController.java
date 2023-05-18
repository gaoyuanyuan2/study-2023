package com.yan.spring.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务发现
 *
 * @author : Y
 * @since 2023/5/18 20:14
 */
@RestController
public class ClientController {
    /**
     * 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentServiceName;

    //线程安全
    private volatile Set<String> targetUrls = new HashSet<>();

    @Scheduled(fixedRate = 10 * 1000) // 10秒钟更新次缓存
    public void updateTargetUrls() { //更新目标URLs
        //获取当前应用的机器列表
        // http://${ip}:${port}
        Set<String> oldTargetUrls = this.targetUrls;
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(currentServiceName);
        Set<String> newTargetUrls = serviceInstances
                .stream()
                .map(s -> s.isSecure() ? "https://" + s.getHost() + ":" + s.getPort() :
                        "http://" + s.getHost() + ":" + s.getPort()
                ).collect(Collectors.toSet());
        // swap
        this.targetUrls = newTargetUrls;
        oldTargetUrls.clear();

    }
}