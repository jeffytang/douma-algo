package com.douma.tree.btree;

import java.util.*;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(23);
        TreeNode<Integer> node1 = new TreeNode<>(34);
        TreeNode<Integer> node2 = new TreeNode<>(21);
        TreeNode<Integer> node3 = new TreeNode<>(99);
        TreeNode<Integer> node4 = new TreeNode<>(77);
        TreeNode<Integer> node5 = new TreeNode<>(90);
        TreeNode<Integer> node6 = new TreeNode<>(45);
        TreeNode<Integer> node7 = new TreeNode<>(60);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node4;
        node3.right = node5;
        node2.left = node6;
        node2.right = node7;

        System.out.println(levelOrder_2(root));
    }
    // 前序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> preOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root ==null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            res.add(curr.data);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return res;
    }
    // 前序遍历(递归)
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public static List<Integer> preOrderR(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }
    // 时间复杂度：O(n)
    // 空间复杂度：最坏是 O(n)，最好的情况 O(logn)
    private static void preOrder(TreeNode<Integer> node, List<Integer> res) {
        // 退出边界条件
        if (node == null) return;
        // 先访问当前节点
        res.add(node.data);
        // 再前序遍历左子树
        preOrder(node.left, res);
        // 最后前序遍历右子树
        preOrder(node.right, res);
    }
    // 中序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> inOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root ==null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode<Integer> node = stack.pop();
            res.add(node.data);

            curr = node.right;
        }
        return res;
    }
    // 中序遍历（递归）
    public static List<Integer> inOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }
    private static void inOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node ==null) return;
        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }
    // 后序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> postOrder(TreeNode<Integer> root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root ==null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            res.addFirst(curr.data);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        return res;
    }
    // 后序遍历(递归)
    public static List<Integer> postOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }
    private static void postOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node == null) return;
        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.data);
    }
    // 层序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> levelOrder(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一个节点
            TreeNode<Integer> curr = queue.poll();
            res.add(curr.data);
            // 将遍历处理的节点的左右子节点入队，等到后序的处理
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return res;
    }
    // 层序遍历
    public static List<List<Integer>> levelOrder_2(TreeNode<Integer> root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> curr = queue.poll();
                levelNodes.add(curr.data);
                // 将遍历处理的节点的左右子节点入队，等到后序的处理
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelNodes);
        }
        return res;
    }
}
