package com.douma.highlevel.map.train;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _350_IntersectionOfTwoArraysii {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        int i = 0, j = 0;
        int k = 0;
        // O(m + n)
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[2]) {
                nums1[k] = nums1[i];
                i++;
                j++;
                k++;
            } else if (nums1[i] < nums2[i]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
    public int[] intersect1(int[] nums1, int[] nums2) {
        // 使用 Map 记录数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1)
            map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);

        return res;
    }

}
