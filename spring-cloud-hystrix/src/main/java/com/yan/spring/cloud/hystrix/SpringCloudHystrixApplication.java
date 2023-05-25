package com.yan.spring.cloud.hystrix;

import com.yan.spring.cloud.hystrix.aop.ServerControllerAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableHystrix
@EnableTurbine
@Import(ServerControllerAspect.class)
public class SpringCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixApplication.class, args);
    }

}
