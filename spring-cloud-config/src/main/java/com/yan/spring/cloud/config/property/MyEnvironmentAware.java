package com.yan.spring.cloud.config.property;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义
 * 实现 {@Link ApplicationContextInitializer}
 * 或者 {@Link EnvironmentAware}
 *
 * @author : Y
 * @since 2023/4/17 20:46
 */
@Configuration
public class MyEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment env = ConfigurableEnvironment.class.cast(environment);

            MutablePropertySources mutablePropertySources = env.getPropertySources();

            Map<String, Object> source = new HashMap<>();

            source.put("server.port", 1234);

            PropertySource propertySource = new MapPropertySource("java-code", source);

            mutablePropertySources.addFirst(propertySource);

        }
    }
}