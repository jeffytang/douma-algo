package com.douma.tree.btree.train;


import java.util.Stack;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _112_PathSum {
    // DFS 递归
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        sum = sum - root.val;
        //如果当前节点是叶子，检查 sum 值是否为 0，也就是是否找到了给定的目标和。
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        // 如果当前节点不是叶子，对它的所有孩子节点，递归调用 hasPathSum 函数，
        // 其中 sum 值减去当前节点的权值
        boolean hasPathLeft = hasPathSum(root.left, sum);
        boolean hasPathRight = hasPathSum(root.right, sum);

        return hasPathLeft || hasPathRight;
    }
    private class Node {
        TreeNode node; // 当前树节点
        int pathRemainSum; // 从根节点到当前节点的路径剩余和

        public Node(TreeNode node, int pathRemainSum) {
            this.node = node;
            this.pathRemainSum = pathRemainSum;
        }
    }
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, sum - root.val));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode currNode = node.node;
            int currPathRemainSum = node.pathRemainSum;
            // 如果当前节点为叶子节点，且当前节点的路径和等于目标和，则直接返回 true
            if (currNode.left == null && currNode.right == null && currPathRemainSum == 0) {
                return true;
            }
            if (currNode.right != null) {
                stack.push(new Node(currNode.right, currPathRemainSum - currNode.right.val));
            }
            if (currNode.left != null) {
                stack.push(new Node(currNode.left, currPathRemainSum - currNode.left.val));
            }
        }
        return false;
    }
}
