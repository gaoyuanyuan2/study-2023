package com.yan.spring.my.beans.support;

import com.yan.spring.my.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * BeanDefinitionReader
 *
 * @author : Y
 * @since 2023/7/11 21:09
 */
public class MyBeanDefinitionReader {
    //保存扫描的结果
    private List<String> registerBeanClasses = new ArrayList<>();
    private Properties contextConfig = new Properties();

    public MyBeanDefinitionReader(String[] configLocations) {
        doLoadConfig(configLocations[0]);
        //扫描配置文件中的配置的相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    /**
     * 扫描需要注册的类
     *
     * @param scanPackage 包名
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = scanPackage + file.getName().replace(".class", "");
                registerBeanClasses.add(className);
            }
        }

    }

    private void doLoadConfig(String configLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation.replaceAll("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<MyBeanDefinition> loadBeanDefinitions() throws ClassNotFoundException {
        List<MyBeanDefinition> result = new ArrayList<>();
        for (String className : registerBeanClasses) {
            Class<?> beanClass = Class.forName(className);
            result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
            for (Class<?> i : beanClass.getInterfaces()) {
                result.add(doCreateBeanDefinition(toLowerFirstCase(i.getSimpleName()), i.getName()));
            }
        }
        return result;
    }

    private MyBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }

    //自己写，自己用
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
//        if(chars[0] > )
        chars[0] += 32;
        return String.valueOf(chars);
    }

}