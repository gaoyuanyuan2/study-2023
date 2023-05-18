package com.yan.spring.cloud.consumer.config;

import com.yan.spring.cloud.consumer.service.interceptor.LoadBalancedRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 自定义服务费发现配置
 *
 * @author : Y
 * @since 2023/5/18 20:25
 */
@Configuration
public class DiscoveryConfig {
    @Bean
    public ClientHttpRequestInterceptor interceptor() {
        return new LoadBalancedRequestInterceptor();
    }

    //定义RestTemplate Bean
    @Bean
    @Autowired
    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor) {//依赖注入
        RestTemplate restTemplate = new RestTemplate();
        //增加拦截器
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;

    }
}