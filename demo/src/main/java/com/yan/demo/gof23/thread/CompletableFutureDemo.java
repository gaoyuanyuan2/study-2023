package com.yan.demo.gof23.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFutureDemo {

    /**
     * 在实际开发中，这个方法还是非常有用的。比如说，task1 和 task2 都是异步执行的，
     * 但 task1 必须执行完成后才能开始执行 task2（task2 依赖 task1 的执行结果）。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        assertEquals("hello!world!", future.get());
    }

    /**
     * thenCompose() 可以链接两个 CompletableFuture 对象，并将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
     * thenCombine() 会在两个任务都执行完成后，把两个任务的结果合并。两个任务是并行执行的，它们之间并没有先后依赖顺序。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> "world!"), (s1, s2) -> s1 + s2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "nice!"));
        assertEquals("hello!world!nice!", completableFuture.get());
    }

    CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
        System.out.println("任务1开始执行，当前时间：" + System.currentTimeMillis());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务1执行完毕，当前时间：" + System.currentTimeMillis());
        return "task1";
    });

    /**
     * 如果我们想要实现 task1 和 task2 中的任意一个任务执行完后就执行 task3 的话，可以使用 acceptEither()。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1执行完毕，当前时间：" + System.currentTimeMillis());
            return "task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2执行完毕，当前时间：" + System.currentTimeMillis());
            return "task2";
        });

        task.acceptEitherAsync(task2, (res) -> {
            System.out.println("任务3开始执行，当前时间：" + System.currentTimeMillis());
            System.out.println("上一个任务的结果为：" + res);
        });

        // 增加一些延迟时间，确保异步任务有足够的时间完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture 的 allOf()这个静态方法来并行运行多个 CompletableFuture 。
     * <p>
     * 实际项目中，我们经常需要并行运行多个互不相关的任务，这些任务之间没有依赖关系，可以互相独立地运行
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> task1 =
                CompletableFuture.supplyAsync(() -> null);

        CompletableFuture<Void> task2 =
                CompletableFuture.supplyAsync(() -> null);

        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(task1, task2);

        try {
            headerFuture.join();
        } catch (Exception ex) {

        }
        System.out.println("all done. ");
    }
}
