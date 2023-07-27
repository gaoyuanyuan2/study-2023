package com.yan.spring.v2.context;

import com.yan.spring.my.aop.MyJdkDynamicAopProxy;
import com.yan.spring.my.aop.config.MyAopConfig;
import com.yan.spring.my.aop.support.MyAdvisedSupport;
import com.yan.spring.v2.annotation.MyAutowired;
import com.yan.spring.v2.annotation.MyController;
import com.yan.spring.v2.annotation.MyService;
import com.yan.spring.v2.beans.MyBeanWrapper;
import com.yan.spring.v2.beans.config.MyBeanDefinition;
import com.yan.spring.v2.beans.support.MyBeanDefinitionReader;

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
            //==================AOP开始=========================
            //如果满足条件，就直接返回Proxy对象
            //1、加载AOP的配置文件
            MyAdvisedSupport config = instantiateAopConfig();
            config.setTargetClass(c);
            config.setTarget(instance);

            //判断规则，要不要生成代理类，如果要就覆盖原生对象
            //如果不要就不做任何处理，返回原生对象
            if(config.pointCutMath()){
                instance = new MyJdkDynamicAopProxy(config).getProxy();
            }

            //===================AOP结束========================
            this.factoryBeanObjectCache.put(beanName, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private MyAdvisedSupport instantiateAopConfig() {
        MyAopConfig config = new MyAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new MyAdvisedSupport(config);
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