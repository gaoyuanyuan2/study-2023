package com.yan.spring.cloud.provider.rule;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 基于uptime决定权重实现
 *
 * @author : Y
 * @since 2024/10/7 14:40
 */
public class DefaultUpTimeWeightStrategy implements UpTimeWeightStrategy{
    public final TemporalUnit MILLIS_UNIT = ChronoUnit.SECONDS;

    private final long RANGE_INIT = 30;
    private final long RANGE_NORMAL = 10 * RANGE_INIT;

    /**
     * [0,1000) return 1.0
     * [1000, 30*1000) return 10.0
     * [30*1000, +) return 100
     */

    @Override
    public double getWeight(Duration duration) {
        long value = duration.get(MILLIS_UNIT);
        if (value < RANGE_INIT)
            return 1.0;
        else if (value < RANGE_NORMAL) {
            return 10.0;
        } else return NORMAL_WEIGHT;
    }
}