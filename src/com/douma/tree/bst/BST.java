package com.douma.tree.bst;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BST<E extends Comparable<E>> {
    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }

    private TreeNode root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    /*************************插入操作*******************************/
    // 时间复杂度：O(logn)
    public void add(E e) {
        if (root == null) {
            root = new TreeNode(e);
        } else {
            TreeNode curr = root;
            while (curr != null) {
                if (e.compareTo(curr.data) == 0) {
                    return;
                } else if (e.compareTo(curr.data) < 0 && curr.left == null) {
                    curr.left = new TreeNode(e);
                    size++;
                    return;
                } else if (e.compareTo(curr.data) > 0 && curr.right == null){
                    curr.right = new TreeNode(e);
                    size++;
                    return;
                }

                if (e.compareTo(curr.data) < 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }
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
        if (root == null) return null;

        TreeNode curr = root;
        while (curr != null) {
            if (target.compareTo(curr.data) == 0) {
                return curr;
            } else if (target.compareTo(curr.data) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    public List<E> preOrder() {
        List<E> res = new ArrayList<>();

        if (root == null)
            return res;
        // 1. 初始化栈对象
        Stack<TreeNode> stack = new Stack<>();

        // 2. 将根节点压入栈中
        stack.push(root);

        // 3. 当栈不为空的时候，循环遍历
        while (!stack.isEmpty()) {
            // 3.1 取出栈顶节点
            TreeNode curr = stack.pop();
            // 3.2 处理栈顶节点
            res.add(curr.data);

            // 3.3 先将栈顶节点的右子节点压入栈中，再将左子节点压入栈中
            // 压入栈的目的是为了下一次循环的处理
            // 先压入右子节点的目的是先处理左子节点
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }

        return res;
    }

    public List<E> inOrder() {
        ArrayList<E> res = new ArrayList<>();
        if (root ==null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            res.add(node.data);

            curr = node.right;
        }
        return res;
    }

    public List<E> postOrder() {
        LinkedList res = new LinkedList<>();

        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.addFirst(curr.data);

            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }

        return res;
    }

    public List<List<E>> levelOrder() {
        List<List<E>> res = new ArrayList<>();

        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<E> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelNodes.add(curr.data);

                // 将遍历后的节点的左右子节点入队，等到下一轮 while 循环的时候遍历处理
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(levelNodes);
        }

        return res;
    }
    // 时间复杂度：O(logn)
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode min = root;
        while (min.left != null) {
            // 一直往左子节点走
            min = min.left;
        }
        return min.data;
    }
    // 时间复杂度：O(logn)
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode max = root;
        while (max.right != null) {
            // 一直往右子节点走
            max = max.right;
        }
        return max.data;
    }

    /*************************删除操作*******************************/
    // 时间复杂度：O(logn)
    public E removeMin() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }

        TreeNode min = root;
        TreeNode parent = null;
        while (min.left != null) {
            parent = min;
            min = min.left;
        }

        if (parent == null) { // 删除根节点
            root = root.right;
        } else {
            parent.left = min.right;
        }
        // bug 修复：统一释放 min 节点
        min.right = null;

        size--;
        return min.data;
    }
    // 时间复杂度：O(logn)
    public E removeMax() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }

        TreeNode max = root;
        TreeNode parent = null;
        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) { // 删除根节点
            root = root.left;
        } else {
            parent.right = max.left;
        }
        // bug 修复：统一释放 max 节点
        max.left = null;

        size--;
        return max.data;
    }
    // 时间复杂度：O(logn)
    public void remove(E e) {
        if (root == null) return;

        TreeNode curr = root;
        TreeNode parent = null;
        // 找到要删除的节点
        while (curr != null && e.compareTo(curr.data) != 0) {
            parent = curr;
            if (e.compareTo(curr.data) < 0) curr = curr.left;
            else curr = curr.right;
        }
        // 如果没有找到需要删除的元素，则直接返回
        if (curr == null) return;

        if (curr.left != null && curr.right != null) {
            // 1. 找到 curr 的右子树的最小值节点
            TreeNode min = curr.right;
            TreeNode minParent = curr;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 2. 覆盖需要删除节点的值为最小值
            curr.data = min.data;

            // 3. 删除最小值节点
            curr = min;
            parent = minParent;
        }

        // 删除节点是叶子节点或者仅有一个子树
        TreeNode child; // 用于存储 需要删除节点 的子节点
        if (curr.left != null) {
            child = curr.left;
            if (parent != null) curr.left = null;
        } else if (curr.right != null) {
            child = curr.right;
            if (parent != null) curr.right = null;
        } else {
            child = null;
        }

        if (parent == null) {
            root = child;
        } else if (curr == parent.left) {
            parent.left = child;
        } else if (curr == parent.right) {
            parent.right = child;
        }
    }

    public void remove1(E e) {
        if (root == null) return;

        TreeNode curr = root;
        TreeNode parent = null;
        // 找到要删除的节点
        while (curr != null && e.compareTo(curr.data) != 0) {
            parent = curr;
            if (e.compareTo(curr.data) < 0) curr = curr.left;
            else curr = curr.right;
        }
        // 如果没有找到需要删除的元素，则直接返回
        if (curr == null) return;

        if (curr.left == null && curr.right == null) { // 删除叶子节点
            if (parent == null) { // 删除根节点
                root = null;
            } else if (curr == parent.left) {
                parent.left = null;
            } else if (curr == parent.right) {
                parent.right = null;
            }
        } else if (curr.left != null && curr.right == null) { // 只有左子树
            if (parent == null) { // 删除根节点
                root = curr.left;
            } else if (curr == parent.left) {
                parent.left = curr.left;
                curr.left = null;
            } else if (curr == parent.right) {
                parent.right = curr.left;
                curr.left = null;
            }
        } else if (curr.left == null && curr.right != null) { // 只有右子树
            if (parent == null) { // 删除根节点
                root = curr.right;
            } else if (curr == parent.left) {
                parent.left = curr.right;
                curr.right = null;
            } else if (curr == parent.right) {
                parent.right = curr.right;
                curr.right = null;
            }
        } else if (curr.left != null && curr.right != null) {
            // 1. 找到 curr 的右子树的最小值节点
            TreeNode min = curr.right;
            TreeNode minParent = curr;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 2. 覆盖需要删除节点的值为最小值
            curr.data = min.data;

            // 3. 删除最小值节点
            minParent.left = null;
        }

    }
}
