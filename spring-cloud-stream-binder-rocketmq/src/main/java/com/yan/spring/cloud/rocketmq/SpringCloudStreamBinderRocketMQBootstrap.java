package com.yan.spring.cloud.rocketmq;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 启动类
 */
@SpringBootApplication
public class SpringCloudStreamBinderRocketMQBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudStreamBinderRocketMQBootstrap.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
