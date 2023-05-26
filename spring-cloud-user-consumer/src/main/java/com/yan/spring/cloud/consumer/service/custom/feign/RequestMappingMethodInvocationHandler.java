package com.yan.spring.cloud.consumer.service.custom.feign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 小交通业务
 *
 * @author : Y
 * @since 2023/5/26 17:59
 */
public class RequestMappingMethodInvocationHandler extends InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}