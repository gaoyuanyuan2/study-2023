package com.yan.spring.cloud.hystrix.config;

import com.yan.spring.cloud.hystrix.service.interceptor.TimeoutAnnotationHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 服务熔断拦截器实现
 *
 * @author : Y
 * @since 2023/6/10 14:29
 */
public class WebMvcConfiguration implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        // 注册一个拦截器
         registry.addInterceptor(new TimeoutAnnotationHandlerInterceptor());
    }
}