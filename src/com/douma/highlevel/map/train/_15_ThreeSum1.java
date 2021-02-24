package com.douma.highlevel.map.train;

import java.util.*;

public class _15_ThreeSum1 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        Set<List<Integer>> res = new HashSet<>();

        Arrays.sort(nums); // O(nlogn)

        // O(n^3)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new _15_ThreeSum1().threeSum(nums));
    }
}
