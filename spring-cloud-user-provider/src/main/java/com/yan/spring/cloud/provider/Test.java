package com.yan.spring.cloud.provider;

import com.yan.spring.cloud.provider.service.ITestService;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 获取接口参数名称
 *  https://docs.oracle.com/javase/tutorial/reflect/member/methodparameterreflection.html
 * @author : Y
 * @since 2023/5/30 20:10
 *
 * 增加java -parameters

 * 大前提 :必须是Java 8 +
 *
 * 为什么不加也可以取到
 */
public class Test {
    public static void main(String[] args) {
        Method method = ReflectionUtils.findMethod(ITestService.class, "test", String.class);
        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Stream.of(discoverer.getParameterNames(method)).forEach(System.out::println);
    }
}