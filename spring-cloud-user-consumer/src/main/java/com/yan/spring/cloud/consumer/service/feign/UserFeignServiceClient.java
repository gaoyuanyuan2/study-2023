package com.yan.spring.cloud.consumer.service.feign;

import com.yan.api.feign.IUserFeignService;
import org.springframework.cloud.netflix.feign.FeignClient;
/**
 *
 * @author : Y
 * @since 2023/4/26 20:01
 */
@FeignClient
public interface UserFeignServiceClient extends IUserFeignService {
}