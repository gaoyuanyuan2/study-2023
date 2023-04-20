package com.yan.spring.cloud.consumer.service;

import com.yan.api.domain.User;
import com.yan.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author : Y
 * @since 2023/4/20 20:39
 */
@Service
public class DefaultUserService implements UserService {

    public static final String PROVIDER_SERVER_URL_PREFIX = "http://spring-cloud-config-provider";


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User save(User user) {
        return restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX + "user/save", user, User.class);
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "user/list", Collection.class, new HashMap<>());
    }
}