package com.yan.demo.distributed;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import org.junit.jupiter.api.Test;

/**
 * 限流
 *
 * @author : Y
 * @since 2024/9/2 20:31
 */
public class CurrentLimitingDemo {
    private static final Bulkhead bulkhead;
    static {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(1) // 最大请求次数
                .build();
        bulkhead = Bulkhead.of("globalBulkheadHandlerInterceptor", config);

    }

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            bulkhead.acquirePermission();
            System.out.println(i);
            // 如需5s请求一次，要五秒钟后释放
            bulkhead.releasePermission();
        }

    }
}