package com.douma.tree.avl;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        avl.add(9);
        avl.add(10);
        avl.add(11);

        avl.add(13);

        System.out.println("是否是二叉查找树：" + avl.isBST());
        System.out.println("是否是平衡二叉树：" + avl.isBalanced());
    }
}
