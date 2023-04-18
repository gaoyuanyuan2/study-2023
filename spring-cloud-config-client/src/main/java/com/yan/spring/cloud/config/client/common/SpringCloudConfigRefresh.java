package com.yan.spring.cloud.config.client.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Configuration
public class SpringCloudConfigRefresh {


    private final ContextRefresher contextRefresher;

    @Autowired
    public SpringCloudConfigRefresh(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    /**
     * 定期刷新 或者事件监听
     */
    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {
        Set<String> updatePropertyNames = contextRefresher.refresh();
        if (!CollectionUtils.isEmpty(updatePropertyNames)) {
            System.err.printf("[Thread:%s] 当前配置已更新，具体项目：%s \n",
                    Thread.currentThread().getName(),
                    updatePropertyNames);
        }
    }
}
