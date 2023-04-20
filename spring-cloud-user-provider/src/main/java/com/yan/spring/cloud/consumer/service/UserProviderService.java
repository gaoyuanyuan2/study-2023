package com.yan.spring.cloud.consumer.service;

import com.yan.api.domain.User;
import com.yan.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : Y
 * @since 2023/4/20 20:55
 */
@Service
public class UserProviderService implements UserService {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final AtomicLong count = new AtomicLong(0);

    @Override
    public User save(User user) {
        user.setId(count.getAndIncrement());
        return userMap.put(user.getId(), user);
    }

    @Override
    public Collection<User> findAll() {
        return userMap.values();
    }
}