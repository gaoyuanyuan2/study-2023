package com.yan.spring.cloud.sleuth.controller;

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
        String url  = "http://spring-cloud-zull/spring-cloud-user-consumer/user/list";
        return restTemplate.postForObject(url, Object.class);
    }
}