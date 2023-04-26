package com.yan.spring.cloud.provider.service;

import com.yan.api.domain.User;
import com.yan.api.feign.IUserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 实现类
 *
 * @author : Y
 * @since 2023/4/26 19:59
 */
@RequestMapping("/user/feign")
@RestController
public class DefaultUserFeignService implements IUserFeignService {

    @Autowired
    private UserProviderService userProviderService;

    @Override
    public User save(@RequestBody User user) {
        return userProviderService.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userProviderService.findAll();
    }
}