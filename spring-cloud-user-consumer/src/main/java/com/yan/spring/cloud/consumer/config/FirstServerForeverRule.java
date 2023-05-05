package com.yan.spring.cloud.consumer.config;

import com.netflix.loadbalancer.ClientConfigEnabledRoundRobinRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 第一个
 *
 * @author : Y
 * @since 2023/5/3 14:37
 */
public class FirstServerForeverRule extends ClientConfigEnabledRoundRobinRule {

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = getLoadBalancer();
        List<Server> allServers = loadBalancer.getAllServers();
        return allServers.get(0);
    }

}