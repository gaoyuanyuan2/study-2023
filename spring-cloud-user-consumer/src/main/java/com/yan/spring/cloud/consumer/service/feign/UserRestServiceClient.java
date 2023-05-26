package com.yan.spring.cloud.consumer.service.feign;

import com.yan.api.feign.IUserFeignService;
import com.yan.spring.cloud.consumer.enums.RestClient;

/**
 *
 * @author : Y
 * @since 2023/4/26 20:01
 */
@RestClient("spring-cloud-user-provider")
public interface UserRestServiceClient extends IUserFeignService {
}