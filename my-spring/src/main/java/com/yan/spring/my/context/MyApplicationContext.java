package com.yan.spring.my.context;

import com.yan.spring.my.annotation.MyAutowired;
import com.yan.spring.my.annotation.MyController;
import com.yan.spring.my.annotation.MyService;
import com.yan.spring.my.beans.MyBeanWrapper;
import com.yan.spring.my.beans.config.MyBeanDefinition;
import com.yan.spring.my.beans.support.MyBeanDefinitionReader;

import java.lang.reflect.Field;
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

    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    private Map<String, Object> factoryBeanObjectCache = new HashMap<>();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            getBean(beanName);
        });
    }

    private Object getBean(String beanName) {
        //1、先拿到BeanDefinition配置信息
        MyBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        //2、反射实例化newInstance();
        Object instance = instantiateBean(beanName, beanDefinition);
        //3、封装成一个叫做BeanWrapper
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);
        //4、保存到IoC容器
        factoryBeanInstanceCache.put(beanName, beanWrapper);
        //5、执行依赖注入
        populateBean(beanName, beanDefinition, beanWrapper);
        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, MyBeanDefinition beanDefinition, MyBeanWrapper beanWrapper) {
        Object wrapperInstance = beanWrapper.getWrapperInstance();
        Class<?> wrappedClass = beanWrapper.getWrappedClass();
        if (!(wrappedClass.isAnnotationPresent(MyController.class) || (wrappedClass.isAnnotationPresent(MyService.class)))) {
            return;
        }
        for (Field field : wrappedClass.getFields()) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }
            MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
            String autowiredBeanName = myAutowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(false);
            Object obj = factoryBeanObjectCache.get(autowiredBeanName);
            try {
                field.set(wrapperInstance, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object instantiateBean(String beanName, MyBeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            Class<?> c = Class.forName(className);
            instance = c.newInstance();
            factoryBeanObjectCache.put(beanName, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists");
            }
            beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }
}