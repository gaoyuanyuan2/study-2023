package com.yan.spring.cloud.consumer.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * web 配置
 *
 * @author : Y
 * @since 2023/5/10 22:14
 */
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler, ModelAndView modelAndView) throws Exception {
                super.postHandle(request, response, handler, modelAndView);
                logger.info("request URI : {}", request.getRequestURI());
            }
        });
    }
}
