package com.yan.spring.boot.servlets.gateway.service.ribbon;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * 自定义zkLoadBalancer
 *
 * @author : Y
 * @since 2023/5/27 15:02
 */
public class ZookeeperLoadBalancer extends BaseLoadBalancer {

    private final DiscoveryClient discoveryClient;

    public ZookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
//
        updateServers();
    }

    /**
     * 更新所有服务器
     */
    @Scheduled(fixedRate = 5000)
    public void updateServers() {
        discoveryClient.getServices().forEach(serviceName -> {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            serviceInstances.forEach(serviceInstance -> {
                Server server = new Server(serviceInstance.isSecure() ? "https://" : "http://" +
                        serviceInstance.getHost(), serviceInstance.getPort());
                addServer(server);

            });
        });

    }
}