package com.yan.spring.my.context;

import com.yan.spring.my.beans.MyBeanWrapper;
import com.yan.spring.my.beans.config.MyBeanDefinition;
import com.yan.spring.my.beans.support.MyBeanDefinitionReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ApplicationContext
 *
 * @author : Y
 * @since 2023/7/11 21:06
 */
public class MyApplicationContext {

    private MyBeanDefinitionReader reader;

    private Map<String, MyBeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String,MyBeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    private Map<String,Object> factoryBeanObjectCache = new HashMap<>();

    public MyApplicationContext(String... configLocations) {

        //1、加载配置文件
        reader = new MyBeanDefinitionReader(configLocations);

        try {
            //2、解析配置文件，封装成BeanDefinition
            List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

            //3、把BeanDefinition缓存起来
            doRegisterBeanDefinition(beanDefinitions);
            //4、 属性赋值
            doAutowired();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doAutowired() {
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) {
    }
}