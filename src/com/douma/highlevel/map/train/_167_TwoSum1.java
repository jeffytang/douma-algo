package com.douma.highlevel.map.train;

public class _167_TwoSum1 {
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 线性查找 - O(n)
            // 1. 二分查找 - O(logn)
            // [i + 1, nums.length - 1] 区间二分查找 target - x
            int index = binarySearch(nums, i + 1, nums.length - 1, target - x);
            if (index != -1) {
                return new int[]{i + 1, index + 1};
            }
        }

        return new int[0];
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}
