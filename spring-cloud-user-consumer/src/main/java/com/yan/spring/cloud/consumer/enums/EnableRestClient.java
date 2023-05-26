package com.yan.spring.cloud.consumer.enums;

import com.yan.spring.cloud.consumer.service.custom.feign.RestClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 {@link @EnableFeignClient}
 *
 * @author : Y
 * @since 2023/5/26 17:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientsRegistrar.class)
public @interface EnableRestClient {
    /**
     * 指定@RestClient 接口
     * @return
     */
    Class<?>[] clients() default {};
}
