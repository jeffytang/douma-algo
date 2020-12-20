package com.douma.line.algo.bs;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BasicBinarySearch {
    // 时间复杂度：O(logn)
    // 空间复杂度：O(1)
    public boolean contains(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // bug : left + right 会溢出
            // 整型的最大值是 (2^31) - 1 = 2147483647
            int mid = left + (right - left) / 2;
            // int mid = (left + right) >>> 1;
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1; // 下一次搜索区间：[left...mid - 1]
            } else { // target > nums[mid]
                left = mid + 1; // 下一次搜索区间：[mid + 1...right]
            }
        }

        // left > right ：没有元素

        return false;
    }

    // 时间复杂度：O(logn)
    // 空间复杂度：O(logn)
    public boolean containsR(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        return contains(nums, 0, nums.length - 1, target);
    }

    private boolean contains(int[] nums, int left, int right, int target) {
        if (left > right) return false;

        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return true;

        if (target < nums[mid]) {
            return contains(nums, left, mid - 1, target);
        } else {
            return contains(nums, mid + 1, right, target);
        }
    }
}
