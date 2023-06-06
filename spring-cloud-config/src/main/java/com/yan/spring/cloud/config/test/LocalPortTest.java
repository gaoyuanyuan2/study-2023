package com.yan.spring.cloud.config.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取端口
 *
 * 常见一些Spring Boot/Cloud中“坑"
 * @Value("server.port") 服务端口不一定靠谱，当server.port=0时
 * @LocalserverPort也不靠谱，因为在注入阶段"localserver.port"不一定存在
 * Spring Cloud + Netflix Ribbon有一个30秒延迟
 * @author : Y
 * @since 2023/6/6 20:55
 */
@RestController
public class LocalPortTest {

    @Autowired
    private Environment environment;

    @GetMapping("getPort")
    private String getPort() {
        return environment.getProperty("local.server.port");
    }
}