package com.yan.spring.cloud.bus.controller;

import com.yan.spring.cloud.bus.event.RemoteAppEvent;
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
public class SpringEventController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentAppName;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @GetMapping("send/event")
    public String sendEvent(@RequestParam String message) {
        publisher.publishEvent(message);
        return "Sent";
    }

    @EventListener
    public void onMessage(PayloadApplicationEvent event) {
        System.out.println("接受事件: " + event.getPayload());
    }

    @PostMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable String appName, @RequestBody Map data) {
        //发送数据到自己
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data, currentAppName, appName, serviceInstances);
        //发送事件当前上下文
        publisher.publishEvent(remoteAppEvent);
        return "Ok";
    }

    @PostMapping("/send/remote/event/{appName}/{ip}/{port)")
    public String sendAppInstance(@PathVariable String appName,
                                  @PathVariable String ip,
                                  @PathVariable int port,
                                  @RequestBody Object data) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        ServiceInstance serviceInstance0 = serviceInstances.get(0);
        ServiceInstance serviceInstance = new DefaultServiceInstance(serviceInstance0.getInstanceId(), serviceInstance0.getServiceId(), ip, port, false);
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data, currentAppName, appName, Arrays.asList(serviceInstance));
        //发送事件当前上下文
        publisher.publishEvent(remoteAppEvent);

        return "Ok";
    }
}