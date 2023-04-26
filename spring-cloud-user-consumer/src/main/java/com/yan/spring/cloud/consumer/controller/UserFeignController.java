package com.yan.spring.cloud.consumer.controller;

import com.yan.api.domain.User;
import com.yan.api.service.UserService;
import com.yan.spring.cloud.consumer.service.feign.UserFeignServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user/feign")
public class UserFeignController {

    @Autowired
    private UserFeignServiceClient userFeignServiceClient;

    @PostMapping("save")
    User save(@RequestBody User user) {
        return userFeignServiceClient.save(user);
    }

    @GetMapping("list")
    private Collection<User> getAllUser() {
        return userFeignServiceClient.findAll();
    }
}
