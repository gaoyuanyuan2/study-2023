package com.yan.demo.gof23.thread.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo02 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(200);
                } catch (InterruptedException e) {//复位
                    e.printStackTrace();
//                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Test Over");

        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //设置 interrupted=true;
        TimeUnit.SECONDS.sleep(2);
//        Thread.interrupted(); 复位
    }
}
