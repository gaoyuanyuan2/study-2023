package com.yan.spring.cloud.bus.listener;


import com.yan.spring.cloud.bus.event.RemoteAppEvent;
import com.yan.spring.cloud.bus.event.RemoteAppEvent2;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RemoteAppEvent}监听器,将事件数据发送HTTP请求到目标机器
 *
 * @author : Y
 * @since 2023/5/30 20:56
 */
public class HttpRemoteAppEventListener2 implements SmartApplicationListener {

    private RestTemplate restTemplate = new RestTemplate();
    //得到DiscoveryClient Bean
    private DiscoveryClient discoveryClient;


    public void onApplicationEvent(RemoteAppEvent event) {
        Object source = event.getSource();
        String appName = event.getAppName();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        for (ServiceInstance s : serviceInstances) {
            String rootURL = s.isSecure() ?
                    "https://" + s.getHost() + ":" + s.getPort() :
                    "http://" + s.getHost() + ":" + s.getPort();
            String url = rootURL + "/receive/remote/event/";
            Map<String, Object> data = new HashMap<>();
            data.put("sender", event.getSender());
            data.put("value", source);
            data.put("type", RemoteAppEvent2.class.getName());
            restTemplate.postForObject(url, data, HashMap.class);
        }
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return RemoteAppEvent.class.isAssignableFrom(eventType)
                || ContextRefreshedEvent.class.isAssignableFrom(eventType);

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof RemoteAppEvent) {
            onApplicationEvent((RemoteAppEvent) event);
        } else if (event instanceof ContextRefreshedEvent) {
            onContextRefreshedEvent((ContextRefreshedEvent) event);
        }
    }


    private void onContextRefreshedEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        this.discoveryClient = applicationContext.getBean(DiscoveryClient.class);
    }
}