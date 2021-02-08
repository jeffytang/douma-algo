package com.douma.highlevel.map.train;

import java.util.HashMap;

public class _1_TwoSum2 {
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    // 空间换时间
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        // 数据预处理
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) { // O(n)
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) { // O(n)
            int x = nums[i];
            // 2. 哈希查找 - O(1)
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                // i 和 index 不是同一个元素，同一个元素不能使用两次
                if (i != index) return new int[]{i, index};
            }
        }

        return new int[0];
    }
}
