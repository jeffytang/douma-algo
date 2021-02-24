package com.douma.highlevel.map.train;

public class _167_TwoSum2 {
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[0];
    }


}
