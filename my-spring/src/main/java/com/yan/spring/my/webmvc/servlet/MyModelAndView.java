package com.yan.spring.my.webmvc.servlet;

import java.util.Map;

/**
 * ModelAndView
 *
 * @author : Y
 * @since 2023/7/17 20:18
 */
public class MyModelAndView {
    private String viewName;
    private Map<String,?> model;

    public MyModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public MyModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}