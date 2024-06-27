package com.yan.demo.base.enums;

@DemoAnnotation(value = "hello")
public class DemoAnnotationDemo {

    public static void main(String[] args) {
        // 获取到代理对象
        DemoAnnotation demoAnnotation = DemoAnnotationDemo.class.getAnnotation(DemoAnnotation.class);
        System.out.println(demoAnnotation.value());
    }
}
