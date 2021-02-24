package com.douma.highlevel.map.train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _170_TwoSum1 {
    private List<Integer> nums;
    private boolean isSorted;

    public _170_TwoSum1() {
        nums = new ArrayList<>();
        isSorted = false;
    }

    // 添加一个元素
    public void add(int number) {
        nums.add(number); // O(1)
        isSorted = false;
    }

    // 查找是否存在两个数，这两个数的和等于 value
    public boolean find(int value) { // O(nlogn)
        if (!isSorted) {
            Collections.sort(nums); // O(nlogn)
            isSorted = true;
        }

        int left = 0;
        int right = nums.size() - 1;
        while (left < right) { // O(n)
            int sum = nums.get(left) + nums.get(right);
            if (sum == value) {
                return true;
            } else if (sum < value) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }
}
