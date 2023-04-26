package com.yan.spring.cloud.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Y
 * @since 2023/4/20 20:46
 */
@Configuration
public class RestTemplateConfig {

    /**
     * new RestTemplate(new OkHttp3ClientHttpRequestFactory()
     * 不加@LoadBalanced 注解，RestTemplate不能通过服务名称找到服务
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}