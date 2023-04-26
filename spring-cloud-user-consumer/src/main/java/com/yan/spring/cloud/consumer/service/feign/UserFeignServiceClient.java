package com.yan.spring.cloud.consumer.service.feign;

import com.yan.api.feign.IUserFeignService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author : Y
 * @since 2023/4/26 20:01
 */
@FeignClient("spring-cloud-user-provider")
public interface UserFeignServiceClient extends IUserFeignService {
}