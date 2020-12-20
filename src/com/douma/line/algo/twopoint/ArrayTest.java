package com.douma.line.algo.twopoint;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        // 将数组传入方法 f
        f(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void f(int[] nums) {
        // 对传进来的数组进行修改
        nums[2] = 999999;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
