package com.yan.spring.my.aop;


import com.yan.spring.my.aop.aspect.MyAdvice;
import com.yan.spring.my.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by Tom.
 */
public class MyJdkDynamicAopProxy implements InvocationHandler {

    private MyAdvisedSupport config;

    public MyJdkDynamicAopProxy(MyAdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, MyAdvice> advices = config.getAdvices(method,null);

        Object returnValue;
        try {
            invokeAdvice(advices.get("before"));

            returnValue = method.invoke(this.config.getTarget(),args);

            invokeAdvice(advices.get("after"));
        }catch (Exception e){
            invokeAdvice(advices.get("afterThrow"));
            throw e;
        }

        return returnValue;
    }

    private void invokeAdvice(MyAdvice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),this.config.getTargetClass().getInterfaces(),this);
    }
}
