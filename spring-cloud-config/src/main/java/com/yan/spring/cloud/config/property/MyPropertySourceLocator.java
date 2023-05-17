package com.yan.spring.cloud.config.property;

/**
 * 小交通业务
 *
 * @author : Y
 * @since 2023/5/16 21:30
 */

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义配置源
 * 定义并且配置/META-INF/spring.factories
 * SPI 加载
 */
public class MyPropertySourceLocator implements PropertySourceLocator {

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