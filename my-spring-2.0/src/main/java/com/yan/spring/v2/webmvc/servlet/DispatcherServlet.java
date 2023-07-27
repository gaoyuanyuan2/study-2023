package com.yan.spring.v2.webmvc.servlet;

import com.yan.spring.v2.context.MyApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 职责：负责任务调度，请求分发
 *
 * @author : Y
 * @since 2023/7/11 20:48
 */
public class DispatcherServlet  extends HttpServlet {
    private MyApplicationContext applicationContext;


    //IoC容器，key默认是类名首字母小写，value就是对应的实例对象
    private Map<String,Object> ioc = new HashMap<>();

    private Map<String, Method> handlerMapping = new HashMap<>();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化Spring核心IoC容器
        applicationContext = new MyApplicationContext(config.getInitParameter("contextConfigLocation"));

        //==============MVC部分==============
        //5、初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("My Spring framework is init.");
    }

    private void doInitHandlerMapping() {
    }
}