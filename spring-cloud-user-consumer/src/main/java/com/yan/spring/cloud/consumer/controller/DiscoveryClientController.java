package com.yan.spring.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DiscoveryClient 服务发现客户端
 *
 * @author : Y
 * @since 2023/5/17 20:36
 */
@RestController
public class DiscoveryClientController {
    /**
     * 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 返回所有的服务名称
     *
     * @return
     */
    @GetMapping("/services")
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }

    /**
     * 返回某个服务的所有实例
     *
     * @return
     */
    @GetMapping("/service/instances/{serviceName}")
    public List<String> getAllServiceInstances(@PathVariable String serviceName) {
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(s -> s.getServiceId() + "-" + s.getHost() + ":" + s.getPort()
                ).collect(Collectors.toList());
    }


}