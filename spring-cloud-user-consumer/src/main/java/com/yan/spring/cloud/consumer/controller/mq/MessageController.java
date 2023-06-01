package com.yan.spring.cloud.consumer.controller.mq;

import com.yan.spring.cloud.consumer.service.mq.ISimpleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 *
 * @author : Y
 * @since 2023/6/1 20:12
 */
public class MessageController {

    @Autowired
    private ISimpleMessageService simpleMessageService;

    @GetMapping("/stream/send")
    public boolean streamSend(@RequestParam String message) {
        //获取MessageChannel
        MessageChannel messageChannel = simpleMessageService.my2023();
        Map<String, Object> headers = new HashMap<>();
        headers.put("charset-encoding", "UTF-8");
        headers.put("content-type", MediaType.TEXT_PLAIN_VALUE);
        return messageChannel.send(new GenericMessage(message, headers));
    }

    @GetMapping("/stream/send/rocketmq")
    public boolean streamSendToRocketMQ(@RequestParam String message) {
        //获取MessageChannel
        MessageChannel messageChannel = simpleMessageService.testChannel();
        return messageChannel.send(new GenericMessage(message));
    }

    @StreamListener("test007") // Spring Cloud Stream注解驱动
    public void onMessage(byte[] data) {
        System.out.println("RocketMQ onMessage(byte[]): " + new String(data));
    }
}