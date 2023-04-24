package com.yan.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrix
@EnableTurbine
public class SpringCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixApplication.class, args);
    }

}
