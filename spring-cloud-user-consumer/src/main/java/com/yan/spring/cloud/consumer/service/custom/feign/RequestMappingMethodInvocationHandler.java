package com.yan.spring.cloud.consumer.service.custom.feign;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理执行类
 *
 * @author : Y
 * @since 2023/5/26 17:59
 */
public class RequestMappingMethodInvocationHandler implements InvocationHandler {
    private final ParameterNameDiscoverer parameterNameDiscoverer
            = new DefaultParameterNameDiscoverer();
    private final String serviceName;
    private final BeanFactory beanFactory;

    public RequestMappingMethodInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤@RequestMapping方法
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (requestMapping != null) {
            //得到URI
            String[] uri = requestMapping.value();
            //获取方法参数数量
            int count = method.getParameterCount();
            //方法参数是有顺序
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
            Annotation[][] annotations = method.getParameterAnnotations();
            Class<?>[] paramTypes = method.getParameterTypes();
            StringBuffer queryString = new StringBuffer();
            for (int i = 0; i < count; i++) {
                Annotation[] paramAnnotations = annotations[i];
                Class<?> paramType = paramTypes[i];
                RequestParam requestParam = (RequestParam) paramAnnotations[0];
                if (requestParam != null) {
                    String paramName = paramNames[i];
                    //HTTP请求参数
                    String requestParamName = StringUtils.hasText(requestParam.value()) ? requestParam.value() : paramName;
                    String requestParamValue = String.class.equals(paramType) ? (String) args[i] : String.valueOf(args[i]);
                    // uri?name=value&n2=v2&n3=v3
                    queryString.append("&")
                            .append(requestParamName).append("=").append(requestParamValue);
                }
            }
            // http://${serviceNamel}/${uri}
            StringBuilder urlBuilder = new StringBuilder("http://").append(serviceName).append("/").append(uri[0]);
            if (StringUtils.hasText(queryString)) {
                urlBuilder.append("?").append(queryString);
            }
            //获得BeanFactory
            RestTemplate restTemplate = beanFactory.getBean("loadBalancedRestTemplate", RestTemplate.class);
            return restTemplate.getForObject(urlBuilder.toString(), method.getReturnType());

        }
        return null;
    }
}