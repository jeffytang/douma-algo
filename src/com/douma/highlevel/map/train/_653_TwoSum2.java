package com.douma.highlevel.map.train;

import java.util.HashSet;
import java.util.Set;

public class _653_TwoSum2 {
    public boolean findTarget(TreeNode root, int target) {
        if (root == null) return false;
        return find(root, target, new HashSet<>());
    }

    private boolean find(TreeNode node, int target, Set<Integer> set) {
        if (node == null) return false;
        if (set.contains(target - node.val)) return true;
        set.add(node.val);
        return find(node.left, target, set) || find(node.right, target, set);
    }
}
