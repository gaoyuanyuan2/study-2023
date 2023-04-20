package com.yan.spring.cloud.consumer.controller;

import com.yan.api.domain.User;
import com.yan.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    private Collection<User> getAllUser(){
        return userService.findAll();
    }
}
