package com.yan.spring.cloud.stream.kafka.binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 生产消息bean
 *
 * @author : Y
 * @since 2023/5/10 21:37
 */
@Component
@EnableBinding(MySource.class)
public class MyMessageProducerBean {

    @Autowired
    @Qualifier(MySource.MY_OUTPUT) // Bean名称
    private MessageChannel myMessageChannel;

    @Autowired
    MySource mySource;

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void send(String message) {
        // 通过消息管道发送消息
//        myMessageChannel.send(MessageBuilder.withPayload(message).build());
        mySource.output().send(MessageBuilder.withPayload(message).build());
    }


}