package com.yan.spring.cloud.rocketmq.config;

import com.yan.spring.cloud.rocketmq.binder.RocketMQMessageChannelBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQMessageChannelBinder 配置
 *
 * @author : Y
 * @since 2023/5/31 21:27
 */
@Configuration
public class RocketMQMessageChannelBinderConfiguration {
    @Bean
    public RocketMQMessageChannelBinder rocketMQMessageChannelBinder() {
        return new RocketMQMessageChannelBinder();
    }

}