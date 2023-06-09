package com.yan.spring.cloud.provider.controller;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 远程事件接收器控制器
 *
 * @author : Y
 * @since 2023/5/30 20:59
 */
@RestController
public class RemoteAppEventReceiverController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @PostMapping("/receive/remote/event/")
    public String receive(@RequestBody Map<String, Object> data) {
        //事件的发送者
        String sender = (String) data.get(" sender");
        //事件的数据内容
        Object value = data.get("value");
        //事件类型
        String type = (String) data.get("type");
        publisher.publishEvent(new SenderRemoteAppEvent(sender, value));
        //接受到对象内容，同样也要发送事件到本地,做处理
        return "received";
    }

    @EventListener
    public void onEvent(SenderRemoteAppEvent event) {
        System.out.println("接受到事件源: " + event + ",来自应用:" + event.getSender());
    }

    private static class SenderRemoteAppEvent extends ApplicationEvent {
        private final String sender;

        private SenderRemoteAppEvent(String sender, Object value) {
            super(value);
            this.sender = sender;
        }

        public String getSender() {
            return sender;
        }

    }
}