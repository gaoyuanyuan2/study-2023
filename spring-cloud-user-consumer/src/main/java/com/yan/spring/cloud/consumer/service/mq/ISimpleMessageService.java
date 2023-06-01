package com.yan.spring.cloud.consumer.service.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 消息渠道
 *
 * @author : Y
 * @since 2023/6/1 20:09
 */
public interface ISimpleMessageService {
    @Output("my2023")
        // Channel name
    MessageChannel my2023(); // destination = my2023

    @Output("test007")
    MessageChannel testChannel(); // destination = test007

}