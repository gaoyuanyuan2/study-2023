package com.yan.demo.gof23.thread.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 打印对象头
 */
public class ClassLayoutDemo {

    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        synchronized (classLayoutDemo){
            System.out.println("locking");
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }

    }
}
