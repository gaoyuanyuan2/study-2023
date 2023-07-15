package com.yan.spring.my.beans;

/**
 * BeanWrapper
 *
 * @author : Y
 * @since 2023/7/11 21:10
 */
public class MyBeanWrapper {
    private Object wrapperInstance;
    private Class<?> wrappedClass;
    public MyBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}