package com.yan.spring.cloud.bus.event;

import org.springframework.context.ApplicationEvent;

/**
 * 远程调用事件改进
 *
 * @author : Y
 * @since 2023/5/30 20:48
 */
public class RemoteAppEvent2 extends ApplicationEvent {
    /**
     * 应用名称
     */
    private String appName;

    private boolean isCluster;

    public RemoteAppEvent2(Object data, String appName, boolean isCluster) {
        super(data);
        this.appName = appName;
        this.isCluster = isCluster;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isCluster() {
        return isCluster;
    }

    public void setCluster(boolean cluster) {
        isCluster = cluster;
    }
}