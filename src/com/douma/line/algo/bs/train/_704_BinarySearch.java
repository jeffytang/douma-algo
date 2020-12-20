package com.douma.line.algo.bs.train;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _704_BinarySearch {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 搜索区间是 [left...right] 中的每个元素
        while (left + 1 < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid])
                right = mid;
            else
                left = mid;
        }
        // 循环结束后：left + 1 == right
        // 需要后处理，因为在循环中，还有两个个元素没有处理
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 搜索区间是 [left...right] 中的每个元素
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid])
                right = mid - 1;
            else
                left = mid;
        }
        // 循环结束后：left == right
        // 需要后处理，因为在循环中，还有一个元素没有处理
        if (nums[left] == target) return left;
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 搜索区间是 [left...right] 中的每个元素
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid])
                left = mid + 1;
            else
                right = mid;
        }
        // 循环结束后：left == right
        // 需要后处理，因为在循环中，还有一个元素没有处理
        if (nums[left] == target) return left;
        return -1;
    }

    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 搜索区间是 [left...right] 中的每个元素
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid])
                // 下一轮搜索区间是：[left...mid - 1]
                right = mid - 1;
            else
                // 下一轮搜索区间是：[mid + 1...right]
                left = mid + 1;
        }
        // 循环结束后：left > right
        // 没有任何的数据需要后处理
        return -1;
    }
}
