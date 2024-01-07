package com.yan.demo.gof23.id;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigInteger;
import java.util.UUID;

public class IdTest {

    public static void main(String[] args) {
        System.out.println(getId());
        System.out.println(getId2());
//        for (int i = 0; i < 100000; i++) {
//            System.out.println(getId2());
//        }

    }

    /**
     * 生成 18位随机数
     *
     * @return 18 位纯数字
     */
    public static String getId() {
        String generateUUIDNo = String.format("%010d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""),
                        16));
        // To decide length of unique positive long number
        // generateUUIDNo.length() - uniqueNoSize is being
        // used
        return generateUUIDNo.substring(generateUUIDNo.length() - 18);
    }

    /**
     * 生成 18位随机数
     *
     * @return 18 位纯数字
     */
    public static long getId2() {
        return RandomUtils.nextLong(100000000000000000L, 999999999999999999L);
    }
}
