package com.yan.spring.cloud.hystrix.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 高级熔断注解
 *
 * @author : Y
 * @since 2023/5/25 20:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TimeoutCircuitBreaker {
    /**
     * 信号量
     *
     * @return设置超时时间
     */
    int timeout();

}
