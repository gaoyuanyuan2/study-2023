package com.yan.spring.cloud.consumer.service.custom.feign.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * 小交通业务
 *
 * @author : Y
 * @since 2023/5/27 11:16
 */
public class RestClientClassFactoryBean implements FactoryBean {

    private final Object proxy;
    private final Class<?> restClientClass;

    private RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
        this.proxy = proxy;
        this.restClientClass = restClientClass;
    }

    @Nullable
    @Override
    public Object getObject() throws Exception {
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return restClientClass;
    }
}