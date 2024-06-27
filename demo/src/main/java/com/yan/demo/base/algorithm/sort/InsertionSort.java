package com.yan.demo.base.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort<T extends Comparable<T>> implements Sort<T> {
    public static void main(String[] args) {
        Integer[] values = Sort.of(7,3, 1, 2, 5, 4);
        Sort<Integer> sort = new InsertionSort<>();
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));
    }


    @Override
    public void sort(T[] values) {
        // 第i 个元素和已经排好序的数据做比较
        for (int i = 1; i < values.length; i++) {
            T t = values[i];
            int j = i;
            while (j > 0 && t.compareTo(values[j - 1]) < 0) {
                //往后移动让出插入空间
                values[j] = values[j - 1];
                j--;
            }
            //插入values[i]到对应位置
            values[j] = t;
        }

    }
}
