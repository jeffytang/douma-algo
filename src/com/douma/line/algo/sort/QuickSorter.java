package com.douma.line.algo.sort;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class QuickSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length < 2) return;
        sort(data, 0, data.length - 1);
    }

    // 子问题
    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        // 分区
        int j = partition(data, lo, hi);
        // 对左边数组排序
        sort(data, lo, j - 1);// log2n
        // 对右边数组排序
        sort(data, j + 1, hi); // log2n
    }

    private int partition(int[] data, int lo, int hi) {
        int pivot = data[hi];
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            if (data[great] < pivot) {
                swap(data, less, great);
                less++;
            }
        }
        swap(data, less, hi);
        return less;
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new QuickSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }
}
