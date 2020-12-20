package com.douma.highlevel.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        // Java 内置优先队列，默认使用小顶堆实现的
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);

        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }
}
