package com.yan.spring.cloud.hystrix.controller;


import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author : Y
 * @since 2023/4/24 20:08
 */
public class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand {

    protected HelloWorldCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("Hello,World"));
    }

    @Override
    protected Object run() throws Exception {
        return "Hello,World!";
    }

    //容错执行
    protected String getFallback() {
        return new HystrixController().errorHelloWorld();
    }
}