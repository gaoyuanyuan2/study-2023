package com.yan.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class SpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApplication.class, args);
    }

    /**
     * 自定义配置源
     * 定义并且配置/META-INF/spring.factories
     * SPI 加载
     */
    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLocator implements PropertySourceLocator {
        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<>();
            source.put("server.port", 9090);
            PropertySource propertySource = new MapPropertySource("my-property-source", source);
            // 获取 PropertySources
            if (environment instanceof ConfigurableEnvironment) {

                ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);

                // 获取 PropertySources
                MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
                // 定义一个新的 PropertySource，并且放置在首位
                propertySources.addFirst(propertySource);

            }
            return null;
        }
    }
}
