package com.yan.spring.cloud.sleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试
 *
 * @author : Y
 * @since 2023/5/10 22:08
 */
@RestController
public class TestController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/to/zuul/person/find/al1")
    public Object toZuul() {
        String url = "http://spring-cloud-zull/spring-cloud-user-consumer/user/list";
        return restTemplate.postForObject(url, null, Object.class);
    }
}