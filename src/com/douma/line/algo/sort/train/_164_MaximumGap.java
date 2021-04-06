package com.douma.line.algo.sort.train;

import com.douma.line.algo.sort.RadixSorter;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _164_MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        // 1. 找到最大最小值
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (max == min) return 0;

        int gap = (int)Math.ceil((double)(max - min)/(nums.length - 1));

        // 2. 初始化桶数组
        int bucketNum = nums.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        // 3. 将所有元素添加到对应的桶中
        for (int num : nums) {
            int bucketId = (num - min) / gap;
            buckets[bucketId].hasData = true;
            buckets[bucketId].min = Math.min(buckets[bucketId].min, num);
            // bug 修复：这是是求最大值，所以需要用 max 函数
            buckets[bucketId].max = Math.max(buckets[bucketId].max, num);
        }

        // 4. 计算最大间隔
        int maxGap = 0;
        int prevBucketMax = min;
        for (Bucket bucket : buckets) {
            if (!bucket.hasData) continue;
            maxGap = Math.max(maxGap, bucket.min - prevBucketMax);
            prevBucketMax = bucket.max;
        }

        return maxGap;
    }

    private class Bucket {
        public boolean hasData = false;
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }

    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        new RadixSorter().sort(nums); // O(n)

        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }

        return maxGap;
    }
}
