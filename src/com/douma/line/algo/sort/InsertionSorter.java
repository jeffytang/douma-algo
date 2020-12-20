package com.douma.line.algo.sort;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class InsertionSorter extends Sorter {
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        // 插入排序的轮数
        for (int i = 1; i < data.length; i++) {
            int tmp = data[i];
            int j;
            for (j = i; j > 0; j--) {
                if (tmp < data[j - 1]) {
                    // 将较大的元素总是向右移动
                    data[j] = data[j - 1];
                } else {
                    break;
                }
            }
            // 找到 i 对应的元素需要插入的位置
            data[j] = tmp;
        }
    }
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort1(int[] data) {
        if (data == null || data.length <= 1) return;

        // 插入排序的轮数
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j , j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new InsertionSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }
}
