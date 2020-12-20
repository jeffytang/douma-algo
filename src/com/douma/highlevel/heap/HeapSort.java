package com.douma.highlevel.heap;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class HeapSort {
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public void sort(Integer[] data) {
        // 1. 建堆，堆化 O(n)
        MaxHeap<Integer> maxHeap = new MaxHeap<>(data);

        // 2. 排序
        Integer[] tmp = new Integer[data.length];
        int i = data.length - 1;
        // O(nlogn)
        while (!maxHeap.isEmpty()) { // O(n)
            tmp[i] = maxHeap.removeMax(); // O(logn)
            i--;
        }

        // 3. 拷贝
        for (int j = 0; j < data.length; j++) {
            data[j] = tmp[j];
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{15, 17, 19, 13, 22, 16, 28, 30, 42, 66};
        new HeapSort().sort(data);
        System.out.println(Arrays.toString(data));
    }
}
