package com.douma.highlevel.heap;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class DataStream2 {
    private MaxHeap<Integer> maxHeap;

    public DataStream2() {
        maxHeap = new MaxHeap<>();
    }

    // O(logn)
    public void add(int val) {
       maxHeap.add(val);
    }

    // O(logn)
    public int removeMax() {
        return maxHeap.removeMax();
    }
}
