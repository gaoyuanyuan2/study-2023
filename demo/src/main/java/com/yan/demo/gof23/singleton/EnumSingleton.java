package com.yan.demo.gof23.singleton;

/**
 * 枚举单例
 *
 * @author : Y
 * @since 2023/6/19 21:27
 */
public enum EnumSingleton {

    SINGLETON("单例", 1),
    ;

    private String desc;
    private int code;

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    EnumSingleton(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }
}
