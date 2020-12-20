package com.douma.line.algo.sort;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class CountingSorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 找到数组中的最大值，初始化计数器
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) { // O(n)
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }
        int[] count = new int[max - min + 1];

        // 2. 计数
        for (int i = 0; i < data.length; i++) { // O(n)
            count[data[i] - min]++;
        }

        // 3. 计数累加
        for (int i = 1; i < count.length; i++) { // O(k)
            count[i] += count[i - 1];
        }

        // 4. 计算每个元素在排序数组中的位置
        int[] output = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) { // O(n)
            int j = data[i];
            int k = count[j - min] - 1;
            output[k] = data[i];

            count[j - min]--;
        }

        // 5. 拷贝数组
        for (int i = 0; i < data.length; i++) { // O(n)
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {
        CountingSorter sorter = new CountingSorter();

        int[] data = { 4, 2, -2, 8, 3, 3, 1 };
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }
}
