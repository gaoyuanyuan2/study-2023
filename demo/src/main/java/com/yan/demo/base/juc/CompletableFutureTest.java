package com.yan.demo.base.juc;

import java.util.concurrent.*;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 1. 创建 CompletableFuture
        System.out.println("1. 创建 CompletableFuture");
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("任务 1 开始执行，线程: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("任务 1 执行完成");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务 2 开始执行，线程: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("任务 2 执行完成");
                return "Hello";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }, executor);

        // 2. 链式调用 - 处理结果
        System.out.println("\n2. 链式调用 - 处理结果");
        CompletableFuture<String> future3 = future2.thenApply(result -> {
            System.out.println("处理结果: " + result);
            return result + " World";
        });

        // 3. 组合多个 CompletableFuture
        System.out.println("\n3. 组合多个 CompletableFuture");
        CompletableFuture<String> future4 = future3.thenCompose(result -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("继续处理: " + result);
                return result + "!";
            }, executor);
        });

        // 4. 并行执行多个任务
        System.out.println("\n4. 并行执行多个任务");
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "A";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }, executor);

        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return "B";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }, executor);

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(taskA, taskB);
        CompletableFuture<String> combinedResult = allTasks.thenApply(v -> {
            try {
                return taskA.get() + taskB.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 5. 异常处理
        System.out.println("\n5. 异常处理");
        CompletableFuture<Object> futureWithException = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("模拟异常");
        }).exceptionally(ex -> {
            System.out.println("捕获异常: " + ex.getMessage());
            return "默认值";
        });

        // 6. 异步回调
        System.out.println("\n6. 异步回调");
        future4.thenAcceptAsync(result -> {
            System.out.println("异步消费结果: " + result);
        }, executor);

        // 等待所有任务完成
        System.out.println("\n等待所有任务完成...");
        future1.get();
        System.out.println("最终结果: " + future4.get());
        System.out.println("组合结果: " + combinedResult.get());
        System.out.println("异常处理结果: " + futureWithException.get());

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

}
