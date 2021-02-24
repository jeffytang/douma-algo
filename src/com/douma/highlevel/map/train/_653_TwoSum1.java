package com.douma.highlevel.map.train;

import java.util.ArrayList;
import java.util.List;

public class _653_TwoSum1 {
    public boolean findTarget(TreeNode root, int target) {
        if (root == null) return false;

        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums); // O(n)

        int len = nums.size();
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if ( sum == target)
                return true;
            else if (sum < target)
                left++;
            else
                right--;
        }

        return false;
    }

    private void inOrder(TreeNode node, List<Integer> nums) {
        if (node == null) return;

        inOrder(node.left, nums);
        nums.add(node.val);
        inOrder(node.right, nums);
    }


}
