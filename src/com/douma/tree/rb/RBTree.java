package com.douma.tree.rb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class RBTree<E extends Comparable<E>> {
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        boolean color; // 颜色

        public TreeNode(E data) {
            this.data = data;
            // 1. 在红黑树中，红色的节点代表的就是它和它的父亲在 2-3 树中是在融合在一起的
            // 2. 在 2-3 树中插入新建的节点的时候，都是先和叶子节点进行融合
            this.color = RED;
        }
    }

    private TreeNode root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    // 判断一个节点是否是红色的
    private boolean isRED(TreeNode node) {
        // 根据红黑树的定义，空节点的颜色是黑色的
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /*************************插入操作*******************************/
    //    node                    x
    //    /  \       左旋转      /   \
    //   T1   x     ------->  node  T3
    //       / \              /  \
    //      T2 T3            T1  T2
    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;

        // 左旋
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }
    // 颜色翻转
    private void flipColors(TreeNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    //    node                    x
    //    /  \       右旋转      /   \
    //   x   T2     ------->   y   node
    //  / \                        /  \
    // y  T1                      T1  T2
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;
        // 右旋
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }
    // 时间复杂度：O(logn)
    public void add(E e) {
        root = add(root, e);
        // 保持根节点为黑色
        root.color = BLACK;
    }
    // 将节点 e 插入到以 node 为根节点的子树当中
    // 并返回插入节点后的二叉查找树的根节点
    private TreeNode add(TreeNode node, E e) {
        // 1. 递归终止条件
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // 2. 递归调用
        // bug 修复：插入的时候只考虑不相等的元素，相等的元素不做任何插入动作
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0){
            node.right = add(node.right, e);
        }

        // 维护以 node 为根节点的子树的黑平衡
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /*************************修改操作*******************************/
    // set 方法会破坏二叉查找树的性质
    /*public void set(E src, E target) {
        if (contains(target)) return;
        TreeNode srcNode = find(src);
        srcNode.data = target;
    }*/

    /*************************查询操作*******************************/
    public boolean contains(E target) {
        TreeNode node = find(target);
        if (node == null) return false;
        return true;
    }
    // 时间复杂度：O(logn)
    public TreeNode find(E target) {
        return find(root, target);
    }

    private TreeNode find(TreeNode node, E target) {
        if (node == null) return null;

        if (target.compareTo(node.data) == 0) {
            return node;
        } else if (target.compareTo(node.data) < 0) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }

    public List<E> preOrder() {
        List<E> res = new ArrayList<>();

        preOrder(root, res);

        return res;
    }

    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        res.add(node.data);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    public List<E> inOrder() {
        List<E> res = new ArrayList<>();

        inOrder(root, res);

        return res;
    }

    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }

    public List<E> postOrder() {
        LinkedList res = new LinkedList<>();

        postOrder(root, res);

        return res;
    }

    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.data);
    }

    // 时间复杂度：O(logn)
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }
    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }
    // 时间复杂度：O(logn)
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }
    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    /*************************删除操作*******************************/
    // 时间复杂度：O(logn)
    public E removeMin() {
        E res = minValue();
        root = removeMin(root);
        return res;
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

    // 时间复杂度：O(logn)
    public E removeMax() {
        E res = maxValue();
        root = removeMax(root);
        return res;
    }
    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        TreeNode rightRoot = removeMax(node.right);
        node.right = rightRoot;

        return node;
    }
    // 时间复杂度：O(logn)
    public void remove(E e) {
        root = remove(root, e);
    }
    // 在以 node 为根节点的子树中删除节点 e
    // 并且返回删除后的子树的根节点
    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
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
}
