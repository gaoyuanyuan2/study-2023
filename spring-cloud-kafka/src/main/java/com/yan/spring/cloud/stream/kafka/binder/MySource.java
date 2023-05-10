package com.yan.spring.cloud.stream.kafka.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义消息来源
 *
 * @author : Y
 * @since 2023/5/10 21:47
 */
public interface MySource {
    /**
     * Name of the output channel.
     */
    String MY_OUTPUT = "myoutput";

    /**
     * @return output channel
     */
    @Output(MySource.MY_OUTPUT)
    MessageChannel output();
}
