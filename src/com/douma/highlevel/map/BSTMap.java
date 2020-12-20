package com.douma.highlevel.map;

import java.util.NoSuchElementException;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private TreeNode root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private TreeNode add(TreeNode node, K key, V value) {
        // 1. 递归终止条件
        if (node == null) {
            size++;
            return new TreeNode(key, value);
        }

        // 2. 递归调用
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        return node;
    }

    @Override
    public V remove(K key) {
        TreeNode node = get(root, key);
        if (node == null) return null;
        root = remove(root, key);
        return node.value;
    }

    private TreeNode remove(TreeNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 要删除的节点就是 node
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // node 的 left 和 right 都不为空
            TreeNode successor = minValue(node.right);

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;
            size--;
            return successor;
        }
    }

    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    // 时间复杂度：O(logn)
    private void removeMin() {
        root = removeMin(root);
    }
    // 删除以  node 为根节点的子树的最小节点
    // 并返回删除完最小节点的子树的根节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        TreeNode leftRoot = removeMin(node.left);
        node.left = leftRoot;

        return node;
    }

    @Override
    public void set(K key, V newValue) {
        TreeNode node = get(root, key);
        if (node == null) {
            throw new NoSuchElementException("没有对应的 key ：" + key);
        }
        node.value = newValue;
    }

    @Override
    public V get(K key) {
        TreeNode node = get(root, key);
        return node == null ? null : node.value;
    }

    private TreeNode get(TreeNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        TreeNode node = get(root, key);
        return node != null;
    }
}
