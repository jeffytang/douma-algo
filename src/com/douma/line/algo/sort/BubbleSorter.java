package com.douma.line.algo.sort;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BubbleSorter extends Sorter {
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        for (int round = 1; round <= data.length; round++) { // 控制冒泡轮数
            boolean hasSwap = false;
            int compareTimes = data.length - round;
            for (int i = 0; i < compareTimes; i++) { // 控制每轮比较次数
                if (data[i] > data[i + 1]) {
                    swap(data, i, i + 1);
                    hasSwap = true;
                }
            }
            if (!hasSwap) break;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new BubbleSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }
}
