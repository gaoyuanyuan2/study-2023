package com.yan.demo.base.tool.bean;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 对象中所有 BigDecimal 类型的属性四舍五入并保留两位小数
 *
 * @author : Y
 * @since 2025/6/4 20:24
 */
public class BigDecimalScaleUtils {

    /**
     * 使用 Spring 的ReflectionUtils
     *
     * @param obj
     */
    public static void processBigDecimalFields(Object obj) {
        if (obj == null) return;

        ReflectionUtils.doWithFields(obj.getClass(), field -> {
            if (BigDecimal.class.isAssignableFrom(field.getType())) {
                ReflectionUtils.makeAccessible(field); // Spring安全的访问控制绕过
                Object value = field.get(obj);
                if (value instanceof BigDecimal) {
                    BigDecimal bd = (BigDecimal) value;
                    if (bd != null) {
                        field.set(obj, bd.setScale(2, RoundingMode.HALF_UP));
                    }
                }
            }
        });
    }

    /**
     * 使用 Spring 的BeanWrapper接口（类型安全）
     *
     * @param obj
     */
    public static void processBigDecimalFields2(Object obj) {
        if (obj == null) return;

        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        for (PropertyDescriptor pd : beanWrapper.getPropertyDescriptors()) {
            if (BigDecimal.class.isAssignableFrom(pd.getPropertyType())) {
                String propertyName = pd.getName();
                Object value = beanWrapper.getPropertyValue(propertyName);
                if (value instanceof BigDecimal) {
                    BigDecimal bd = (BigDecimal) value;
                    if (bd != null) {
                        beanWrapper.setPropertyValue(propertyName, bd.setScale(2, RoundingMode.HALF_UP));
                    }
                }
            }
        }
    }
}
