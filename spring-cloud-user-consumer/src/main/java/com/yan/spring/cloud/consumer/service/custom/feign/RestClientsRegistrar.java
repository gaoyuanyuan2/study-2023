package com.yan.spring.cloud.consumer.service.custom.feign;

import com.yan.spring.cloud.consumer.enums.EnableRestClient;
import com.yan.spring.cloud.consumer.enums.RestClient;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * 自定义Feign 注册
 *
 * @author : Y
 * @since 2023/5/26 17:40
 */
public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar {
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(EnableRestClient.class.getName());
        // Attributes -> (clients : UserRestServiceClient)
        Class<?>[] clientClasses = (Class<?>[]) attributes.get("clients");
        //接口类对象数组
        //筛选所有接口
        Stream.of(clientClasses)
                .filter(Class::isInterface) //仅选择接口
                .filter(interfaceClass ->
                        findAnnotation(interfaceClass, RestClient.class) != null) //仅选择标注
                .forEach(restClientClass -> {
                // @RestClient接7编程JDK动态代理
                    Proxy.newProxyInstance(classLoader);
                //过滤@RequestMapping方法

                });
    }
}