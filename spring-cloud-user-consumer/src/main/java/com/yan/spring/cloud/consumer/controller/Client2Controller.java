package com.yan.spring.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
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
public class Client2Controller {
    /**
     * 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentServiceName;

    @Autowired
    private RestTemplate restTemplate;
    //线程安全
    private volatile Set<String> targetUrls = new HashSet<>();
    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();


    @Scheduled(fixedRate = 10 * 1000) // 10秒钟更新-次缓存
    public void updateTargetUrlsCache() { //更新目标URLs
        //获取当前应用的机器列表
        // http://${ip}:${port}
        Map<String, Set<String>> newTargetUrlsCache = new HashMap<>();
        discoveryClient.getServices().forEach(serviceName -> {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            Set<String> newTargetUrls = serviceInstances
                    .stream()
                    .map(s -> s.isSecure() ? "https://" + s.getHost() + ":" + s.getPort() :
                            "http://" + s.getHost() + ":" + s.getPort()
                    ).collect(Collectors.toSet());
            newTargetUrlsCache.put(serviceName, newTargetUrls);
        });
        this.targetUrlsCache = newTargetUrlsCache;
    }

    @GetMapping("/invoke/{serviceName}/say")//-> /say
    public String invokeSay(@PathVariable String serviceName,
                            @RequestParam String message) {
        //服务器列表快照
        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));
        int size = targetUrls.size();
        //size =3, index =0-2
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        // RestTemplate发送请求到服务器
        //输出响应
        return restTemplate.getForObject(targetURL + "/say?message=" + message, String.class);

    }

}