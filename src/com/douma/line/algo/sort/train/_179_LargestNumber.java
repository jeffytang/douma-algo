package com.douma.line.algo.sort.train;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _179_LargestNumber {
    public String largestNumber(int[] nums) {
        // 排序
        // sort(nums, 0, nums.length - 1);

        // 1. 将所有元素转成字符串
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 降序排列
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                String xy = x + y;
                String yx = y + x;
                return yx.compareTo(xy);
            }
        });

        if (nums[0] == 0) return "0"; // "00000"

        StringBuilder sb = new StringBuilder();
        // bug 修复：这里应该是拼接 str[] 里的元素
        for (String num : strs) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        // 分区
        int pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            String xy = data[i] + "" + pivot;
            String yx = pivot + "" + data[i];
            if (xy.compareTo(yx) > 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great +1, hi);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
