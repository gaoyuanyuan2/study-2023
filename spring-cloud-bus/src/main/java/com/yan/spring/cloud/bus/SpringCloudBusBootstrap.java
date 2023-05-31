package com.yan.spring.cloud.bus;

import com.yan.spring.cloud.bus.listener.HttpRemoteAppEventListener;
import com.yan.spring.cloud.bus.listener.HttpRemoteAppEventListener2;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient //激活服务发现客户端
public class SpringCloudBusBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudBusBootstrap.class)
                .web(WebApplicationType.SERVLET)
                .listeners(new HttpRemoteAppEventListener())
                .listeners(new HttpRemoteAppEventListener2())
                .run(args);
    }
}
