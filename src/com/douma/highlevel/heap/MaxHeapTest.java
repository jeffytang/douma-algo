package com.douma.highlevel.heap;

import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class MaxHeapTest {
    public static void main(String[] args) {
        int n = 10000;
        Random random = new Random();

        MaxHeap<Integer> heap = new MaxHeap<>();

        // 1. 往堆中添加 10000 个随机整数
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt());
        }

        // 2. 依次从堆中取出 10000 个整数，并依次放入到数组中
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.removeMax();
        }

        // 3. 判断先拿出来的元素是最大的，如果不是的话，则说明不符合堆的性质
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("Error");
            }
        }

        System.out.println("Test MaxHeap Succ");
    }
}
