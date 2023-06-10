package com.yan.spring.cloud.hystrix.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Timeout {

    /**
     * 超时时间
     *
     * @return
     */
    long value();

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 补偿方法，默认可以为空
     * @return
     */
    String fallback() default "";
}
