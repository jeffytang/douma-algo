package com.douma.highlevel.heap;

import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class Test {
    private static Random random = new Random();
    public static void main(String[] args) {
        int cnt = 10000000;
        double time1 = testHeapify(cnt, false);
        System.out.println("使用 sift up 花费的时间：" + time1);

        double time2 = testHeapify(cnt, true);
        System.out.println("使用 sift down 花费的时间：" + time2);
    }

    private static double testHeapify(int cnt, boolean isSiftDown) {
        Integer[] arr = new Integer[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = random.nextInt();
        }

        long startTime = System.nanoTime();
        if (isSiftDown) {
            MaxHeap<Integer> heap = new MaxHeap<>(arr);
        } else {
            MaxHeap<Integer> heap = new MaxHeap<>();
            for (int i = 0; i < cnt; i++) {
                heap.add(arr[i]);
            }
        }

        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }
}
