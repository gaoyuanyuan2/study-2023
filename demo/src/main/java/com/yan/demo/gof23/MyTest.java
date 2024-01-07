package com.yan.demo.gof23;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MyTest {
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        Set<String> set = new HashSet<>(50000000);
        for (int i = 0; i < 10000000; i++) {
            print(set);
        }
        System.out.println(set.size());
        System.out.println(System.currentTimeMillis() - s);
        for (int i = 0; i < 10000000; i++) {
            print(set);
        }
        System.out.println(set.size());
        System.out.println(System.currentTimeMillis() - s);
        for (int i = 0; i < 10000000; i++) {
            print(set);
        }
        System.out.println(set.size());
        System.out.println(System.currentTimeMillis() - s);
        for (int i = 0; i < 10000000; i++) {
            print(set);
        }
        System.out.println(set.size());
        System.out.println(System.currentTimeMillis() - s);
    }


    public static void print(Set<String> set) {
        String generateUUIDNo = String.format("%010d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""),
                        16));
        // To decide length of unique positive long number
        // generateUUIDNo.length() - uniqueNoSize is being
        // used
        String unique_no =
                generateUUIDNo.substring(generateUUIDNo.length() - 18);
        set.add(unique_no);
    }
}
