package com.yan.demo.base.algorithm.sort;

import java.util.Arrays;

/**
 *
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    public static void main(String[] args) {
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new BubbleSort<>();
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));
    }

    @Override
    public void sort(T[] values) {
        // 循环n-1 次
        for (int i = 0; i < values.length - 1; i++) {
            // 相邻比较后移。每次比较元素数量建议
            for (int j = 0; j < values.length - 1 - i; j++) {
                if (values[j].compareTo(values[j + 1]) > 0) {
                    T temp = values[j];
                    values[j] = values[j+1];
                    values[j + 1] = temp;
                }
            }
        }
    }
}
