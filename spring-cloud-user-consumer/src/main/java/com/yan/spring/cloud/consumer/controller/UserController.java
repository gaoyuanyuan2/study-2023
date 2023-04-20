package com.yan.spring.cloud.consumer.controller;

import com.yan.api.domain.User;
import com.yan.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("save")
    User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("list")
    private Collection<User> getAllUser() {
        return userService.findAll();
    }
}
