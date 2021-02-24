package com.douma.highlevel.map.train;

import java.util.*;

public class _170_TwoSum2 {
    private Map<Integer, Integer> nums;

    public _170_TwoSum2() {
        nums = new HashMap<>();
    }

    // 添加一个元素
    public void add(int number) {
        nums.put(number, nums.getOrDefault(number, 0) + 1);
    }

    // 查找是否存在两个数，这两个数的和等于 value
    public boolean find(int value) { // O(n)
        for (Integer num : nums.keySet()) {
            int target = value - num;
            if (target == num && nums.get(target) > 1) return true;
            if (target != num && nums.containsKey(target)) return true;
        }
        return false;
    }
}
