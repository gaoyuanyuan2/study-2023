package com.yan.spring.cloud.bus.listener;


import com.yan.spring.cloud.bus.event.RemoteAppEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationListener;
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
public class HttpRemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        Object source = event.getSource();
        String appName = event.getAppName();
        List<ServiceInstance> serviceInstances = event.getServiceInstances();
        for (ServiceInstance s : serviceInstances) {
            String rootURL = s.isSecure() ?
                    "https://" + s.getHost() + ":" + s.getPort() :
                    "http://" + s.getHost() + ":" + s.getPort();
            String url = rootURL + "/receive/remote/event/";
            Map<String, Object> data = new HashMap<>();
            data.put("sender", event.getSender());
            data.put("value", source);
            data.put("type", RemoteAppEvent.class.getName());
            restTemplate.postForObject(url, data, HashMap.class);
        }
    }
}