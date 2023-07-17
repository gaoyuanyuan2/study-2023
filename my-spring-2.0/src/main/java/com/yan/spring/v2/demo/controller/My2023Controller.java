package com.yan.spring.v2.demo.controller;

import com.yan.spring.v2.annotation.MyAutowired;
import com.yan.spring.v2.annotation.MyController;
import com.yan.spring.v2.annotation.MyRequestMapping;
import com.yan.spring.v2.annotation.MyRequestParam;
import com.yan.spring.v2.demo.service.IModifyService;
import com.yan.spring.v2.demo.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 *
 * @author Tom
 */
@MyController
@MyRequestMapping("/web")
public class My2023Controller {

    @MyAutowired
    IQueryService queryService;
    @MyAutowired
    IModifyService modifyService;

    @MyRequestMapping("/query.json")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @MyRequestParam("name") String name) {
        String result = queryService.query(name);
        out(response, result);
    }

    @MyRequestMapping("/add*.json")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @MyRequestParam("name") String name, @MyRequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        out(response, result);
    }

    @MyRequestMapping("/remove.json")
    public void remove(HttpServletRequest request, HttpServletResponse response,
                       @MyRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        out(response, result);
    }

    @MyRequestMapping("/edit.json")
    public void edit(HttpServletRequest request, HttpServletResponse response,
                     @MyRequestParam("id") Integer id,
                     @MyRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        out(response, result);
    }


    private void out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
