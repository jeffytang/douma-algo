package com.douma.highlevel.map.train;

import java.util.*;

public class _15_ThreeSum2 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        Set<List<Integer>> res = new HashSet<>();

        Arrays.sort(nums); // O(nlogn)

        // O(n^2)
        for (int i = 0; i < nums.length; i++) {
            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(res); // O(n)
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new _15_ThreeSum2().threeSum(nums));
    }
}
