package com.yan.spring.cloud.bus.controller;

import com.yan.spring.cloud.bus.event.RemoteAppEvent;
import com.yan.spring.cloud.bus.event.RemoteAppEvent2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 事件
 *
 * @author : Y
 * @since 2023/5/30 20:38
 */
@RestController
@RequestMapping("/v2")
public class SpringEventController2 implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @Value("${spring.application.name}")
    private String currentAppName;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }



    @PostMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable String appName, @RequestBody Map data) {


        RemoteAppEvent2 remoteAppEvent = new RemoteAppEvent2(data, currentAppName,  true);

        //发送事件当前上下文
        publisher.publishEvent(remoteAppEvent);
        return "Ok";
    }


}