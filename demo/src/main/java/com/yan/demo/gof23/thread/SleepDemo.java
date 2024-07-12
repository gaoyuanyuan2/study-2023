package com.yan.demo.gof23.thread;

/**
 * 如果线程在sleep（）状态下停止，则该线程会进入catch语句，并且清除停止状态值，变为false；
 */
public class SleepDemo {
    static class MyThread1T1114 extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("run begin");
                //与前几个测试类的主要区别 会进入睡眠 状态 即调用sleep方法。
                Thread.sleep(1000);
                System.out.println("run end");
            } catch (InterruptedException e) {
                System.out.println("在沉睡中被停止！进入catch！" + this.isInterrupted());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread1T1114 myThread1T1114 = new MyThread1T1114();
        myThread1T1114.start();
        myThread1T1114.interrupt();
        System.out.println("Run2T1114 end");
    }
}
