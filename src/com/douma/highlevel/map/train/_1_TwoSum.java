package com.douma.highlevel.map.train;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _1_TwoSum {
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            // 线性查找
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == target - x) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        // TODO 这里有个 bug，我们不应该对原数组进行排序，因为排序会改变原数组中元素的相对位置，这样返回的索引就不对了
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // nlogn
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 二分查找
            // logn
            int index = binarySearch(nums, i + 1, n - 1, target - x);
            if (index != -1) {
                return new int[]{i, index};
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

    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    // 双指针
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        // TODO 这里有个 bug，我们不应该对原数组进行排序，因为排序会改变原数组中元素的相对位置，这样返回的索引就不对了
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        while (i < j) { // O(n/2)
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[] {i, j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }

    // 时间复杂度：O(2n)
    // 空间复杂度：O(n)
    // 空间换时间
    public int[] twoSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        // 数据预处理
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            map.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 哈希查找
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                // x 和 target - x 不是同一个元素
                if (i != index) return new int[]{i, index};
            }
        }
        return new int[0];
    }

    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    // 空间换时间
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 哈希查找
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                return new int[]{i, index};
            }
            map.put(x, i);
        }
        return new int[0];
    }
}
