package com.yan.spring.cloud.config.test;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties 转xml
 *
 * @author : Y
 * @since 2023/6/6 20:41
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", "yan");
        properties.setProperty("age", "32");
        properties.storeToXML(System.out, "This is a comment", "UTF-8");
    }
}