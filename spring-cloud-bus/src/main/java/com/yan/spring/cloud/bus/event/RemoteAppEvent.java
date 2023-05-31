package com.yan.spring.cloud.bus.event;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Map;

/**
 * 远程调用事件
 *
 * @author : Y
 * @since 2023/5/30 20:48
 */
public class RemoteAppEvent extends ApplicationEvent {
    /**
     * 事件传输类型HTTP、RPC、 MQ
     */
    private String type;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用实例
     */
    private List<ServiceInstance> serviceInstances;

    private String sender;

    public RemoteAppEvent(Object data, String sender, String appName, List<ServiceInstance> serviceInstances) {
        super(data);
        this.appName = appName;
        this.sender = sender;
        this.serviceInstances = serviceInstances;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ServiceInstance> getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(List<ServiceInstance> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public RemoteAppEvent(Object source) {
        super(source);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}