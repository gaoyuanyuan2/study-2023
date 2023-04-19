package com.yan.spring.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientApplication.class, args);
    }

}
