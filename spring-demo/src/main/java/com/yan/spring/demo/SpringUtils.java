package com.yan.spring.demo;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * SpringUtils
 *
 * @author : Y
 * @since 2023/4/15 16:54
 */
public class SpringUtils {

    /**
     * 获取Spring容器对象
     *
     * @param type    类型
     * @param context 上下文
     * @param <T>     泛型
     * @return 对象集合
     */
    public <T> Map<String, T> getInstances(Class<T> type, AnnotationConfigApplicationContext context) {
        if (BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
                type).length > 0) {
            return BeanFactoryUtils.beansOfTypeIncludingAncestors(context, type);
        }
        return null;
    }
}