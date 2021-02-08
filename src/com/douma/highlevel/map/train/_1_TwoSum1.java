package com.douma.highlevel.map.train;

public class _1_TwoSum1 {
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 线性查找 - O(n)
            // 1. 二分查找 - O(logn)
            // 2. 哈希查找 - O(1)
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - x) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[0];
    }
}
