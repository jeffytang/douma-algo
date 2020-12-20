package com.douma.highlevel.heap.train;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _215_KthLargestElementInAnArray {
    // 优先队列
    // 时间复杂度：O(Max(klogk, (n - k) * logk))
    // 空间复杂度：O(min(k, n - k))
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if (k < len - k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(k);
            // O(klogk)
            for (int i = 0; i < k; i++) { // k
                pq.add(nums[i]); // logk
            }
            // O((n - k) * logk)
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > pq.peek()) {
                    pq.remove(); // logk
                    pq.add(nums[i]); // logk
                }
            }

            return pq.peek();
        } else {
            int capacity = len - k + 1;
            PriorityQueue<Integer> pq
                    = new PriorityQueue<>(capacity, (a, b) -> b - a);
            // O(klogk)
            for (int i = 0; i < capacity; i++) { // k
                pq.add(nums[i]); // logk
            }
            // O((n - k) * logk)
            for (int i = capacity; i < nums.length; i++) {
                if (nums[i] < pq.peek()) {
                    pq.remove(); // logk
                    pq.add(nums[i]); // logk
                }
            }

            return pq.peek();
        }

    }

    private Random random = new Random(System.currentTimeMillis());
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        int left = 0;
        int right = len - 1;
        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    private int partition(int[] data, int lo, int hi) {
        if (hi > lo) {
            int randomIndex = lo + 1 + random.nextInt(hi - lo);
            swap(data, hi, randomIndex);
        }
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

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
