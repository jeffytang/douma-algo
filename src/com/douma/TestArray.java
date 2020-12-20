package com.douma;

/**
 * @微信公众号 : 抖码课堂
 * @作者 : 老汤
 */
public class TestArray {

    // 时间复杂度 ： O(n)
    public static boolean contains(int[] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }
}
