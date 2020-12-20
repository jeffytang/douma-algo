package com.douma.highlevel.map.train;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _349_IntersectionOfTwoArrays {
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        Set<Integer> resultSet = new HashSet<>();
        int i = 0, j = 0;
        // O(m + n)
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[2]) {
                resultSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[i]) {
                i++;
            } else {
                j++;
            }
        }

        int[] ans = new int[resultSet.size()];
        int k = 0;
        for (int num : resultSet) {
            ans[k++] = num;
        }

        return ans;
    }

    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        // O(m)
        for (int num : nums1) set.add(num);

        Set<Integer> resultSet = new HashSet<>();
        // O(n)
        for (int num2 : nums2) { // O(n)
            // 哈希查找
            if (set.contains(num2)) { // O(1)
                resultSet.add(num2);
            }
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }

        return ans;
    }

    // 时间复杂度：O((m + n) * logm)
    // 空间复杂度：O(min(m, n))
    public int[] intersection2(int[] nums1, int[] nums2) {
        // O(mlogm)
        Arrays.sort(nums1);

        Set<Integer> resultSet = new HashSet<>();
        // O(n*logm)
        for (int num2 : nums2) { // O(n)
            // 二分查找
            if (binarySearch(nums1, num2)) { // O(logm)
                resultSet.add(num2);
            }
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }

        return ans;
    }

    private boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }

    // 时间复杂度：O(n*m)
    // 空间复杂度：O(min(m, n))
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> resultSet = new HashSet<>();
        for (int num2 : nums2) {
            // 线性查找
            for (int num1 : nums1) {
                if (num2 == num1) resultSet.add(num2);
            }
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }

        return ans;
    }
}
