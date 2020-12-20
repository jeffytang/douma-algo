package com.douma.line.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ShellSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列
        int n = data.length;
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;
        do {
            h = ((int)Math.pow(3, k) - 1) / 2;
            if (h > n / 3) break;
            list.add(h); // 1, 4, 13, 40, 121......
            k++;
        } while (h <= n / 3);

        // 2. 希尔排序
        for (k = list.size() - 1; k >= 0 ; k--) { // 倒序遍历
            h = list.get(k);
            // 将数组变为 h 有序
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j , j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void sort1(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列
        int n = data.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121......

        // 2. 希尔排序
        while (h >= 1) {
            // 将数组变为 h 有序
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j , j - h);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new ShellSorter().sort1(data);
        System.out.println(Arrays.toString(data));
    }
}
