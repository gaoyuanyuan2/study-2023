package com.yan.demo.base.functional;

import io.vavr.CheckedFunction0;
import org.apache.commons.lang3.StringUtils;

/**
 * CheckedFunction 实例
 *
 * @author : Y
 * @since 2024/8/26 21:15
 */
public class CheckedFunctionDemo {

    /**
     *
     * @param action
     * @param <R>
     * @return
     */
    protected static <R> R doInDelegate(CheckedFunction0<R> action) {
        R result = null;

        try {
            result = action.apply();
        } catch (Throwable e) {

        } finally {

        }
        return result;
    }

    private static void after(String ms) {

    }

    private static String add10(String ok, int i) {
        return String.valueOf(Integer.parseInt(ok) + i);
    }

    private static void before(String ms) {
    }

    public static void test1(String ms) {
        System.out.println(doInDelegate(() -> add10(ms, 10)));
    }

    public static void test2(String ms) {
        System.out.println(doInDelegate(() -> StringUtils.length(ms)));
    }

    public static void main(String[] args) {
        test1("123");
        test2("abc");
    }
}