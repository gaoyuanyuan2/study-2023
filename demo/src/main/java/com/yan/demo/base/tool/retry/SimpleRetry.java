package com.yan.demo.base.tool.retry;

import java.util.concurrent.Callable;

/**
 * 下面是一个超简化版的 Java 重试实现
 *
 * @author : Y
 * @since 2025/6/4 20:27
 */
public class SimpleRetry {
    public static <T> T execute(Callable<T> task, int maxRetries, long delayMillis) throws Exception {
        Exception lastException = null;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                return task.call();
            } catch (Exception e) {
                lastException = e;
                if (attempt < maxRetries) Thread.sleep(delayMillis);
            }
        }
        throw lastException;
    }

    public static void main(String[] args) {
        try {
            String result = SimpleRetry.execute(
                    () -> {
                        System.out.println("尝试执行...");
                        if (Math.random() > 0.3) throw new RuntimeException("失败");
                        return "成功";
                    },
                    3, // 重试3次
                    1000 // 间隔1秒
            );
            System.out.println("结果: " + result);
        } catch (Exception e) {
            System.out.println("最终失败: " + e.getMessage());
        }
    }
}