package com.douma.line.algo.sort.train;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _Jianzhi51_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 为了不改变原数组，这里先将原数组拷贝一份
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[nums.length];
        return reversePairs(copy, 0, nums.length - 1, temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int leftReversePairs = reversePairs(nums, left, mid, temp);
        int rightReversePairs = reversePairs(nums, mid + 1, right, temp);

        int mergeReversePairs = mergeAndCountReversePairs(nums, left, mid, right, temp);

        return leftReversePairs + rightReversePairs + mergeReversePairs;
    }

    private int mergeAndCountReversePairs(int[] data,
                                          int left,
                                          int mid,
                                          int right,
                                          int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int count = 0;
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) { // 左边没有元素，右边有元素
                data[k] = tmp[j++];
            } else if (j == right + 1) { // 左边有元素，右边没有元素
                data[k] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[k] = tmp[i++];
            } else { // tmp[i] > tmp[j]
                data[k] = data[j++];
                // 计算 temp[j] 的逆序对
                count += mid - i + 1;
            }
        }

        return count;
    }

    public int reversePairs1(int[] nums) { // O(n^2)
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }

        return count;
    }
}
