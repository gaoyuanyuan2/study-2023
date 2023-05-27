package com.yan.spring.cloud.consumer.service.custom.feign;

import com.yan.spring.cloud.consumer.enums.EnableRestClient;
import com.yan.spring.cloud.consumer.enums.RestClient;
import com.yan.spring.cloud.consumer.service.custom.feign.bean.RestClientClassFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * 自定义Feign 注册
 *
 * @author : Y
 * @since 2023/5/26 17:40
 */
public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware, EnvironmentAware {
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private BeanFactory beanFactory;
    private Environment environment;

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
                    RestClient restClient = findAnnotation(restClientClass, RestClient.class);
//                    String serviceName = restClient.value();
                    //获取应用名称(处理占位符)
                    String serviceName = environment.resolvePlaceholders(restClient.value());
                    // @RestClient接7编程JDK动态代理,生成代理对象
                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass}, new RequestMappingMethodInvocationHandler(serviceName, beanFactory));
                    // 注册bean
                    registerBeanByFactoryBean(serviceName, proxy, restClientClass, registry);
//                    // 方法二 注册bean
//                    registerBeanByFactoryBean2(serviceName, proxy, registry);
                });
    }

    private static void registerBeanByFactoryBean2(String serviceName, Object proxy, BeanDefinitionRegistry registry) {
        String beanName = "RestClient." + serviceName;
        if (registry instanceof SingletonBeanRegistry) {
            SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry) registry;
            singletonBeanRegistry.registerSingleton(beanName, proxy);
        }
    }


    /**
     * 注册bean
     *
     * @param serviceName     服务名
     * @param proxy           代理对象
     * @param restClientClass 类型
     * @param registry        注册器
     */
    private static void registerBeanByFactoryBean(String serviceName, Object proxy, Class<?> restClientClass, BeanDefinitionRegistry registry) {
        String beanName = "RestClient." + serviceName;
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);
        //增加第一个构造器参数引用 : proxy
        beanDefinitionBuilder.addConstructorArgValue(proxy);
        //增加第二个构造器参数引用: restClientClass
        beanDefinitionBuilder.addConstructorArgValue(restClientClass);
        /**
         * <bean class= "User">
         <constructor-arg> ${} </construtor-arg>
         </bean>
         */
        BeanDefinition beanDefinition = null;
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}