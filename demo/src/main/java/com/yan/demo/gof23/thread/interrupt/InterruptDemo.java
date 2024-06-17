package com.yan.demo.gof23.thread.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            int i = 0;
            while(!Thread.currentThread().isInterrupted()){
                //
                System.out.println("Test:"+i++);
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt(); //设置 interrupted=true;
    }
}
