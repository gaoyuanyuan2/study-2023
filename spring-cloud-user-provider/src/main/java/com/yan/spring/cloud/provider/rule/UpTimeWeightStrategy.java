package com.yan.spring.cloud.provider.rule;

import java.time.Duration;

/**
 * 基于uptime决定权重
 *
 * @author : Y
 * @since 2024/10/7 14:39
 */
@FunctionalInterface
public interface UpTimeWeightStrategy {

    double getWeight(Duration duration);

    double NORMAL_WEIGHT = 100.0;
}