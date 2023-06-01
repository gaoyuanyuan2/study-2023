package com.yan.spring.cloud.bus.controller;

import com.yan.spring.cloud.bus.event.RemoteAppEvent2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 改进版自定义bus
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
        RemoteAppEvent2 remoteAppEvent = new RemoteAppEvent2(data, currentAppName, true);
        //发送事件当前上下文
        publisher.publishEvent(remoteAppEvent);
        return "Ok";
    }


}