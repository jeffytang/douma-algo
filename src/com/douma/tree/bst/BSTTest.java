package com.douma.tree.bst;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class BSTTest {
    public static void main(String[] args) {
        BSTR<Integer> bst = new BSTR<>();
        bst.add(33);
        bst.add(22);
        bst.add(66);
        bst.add(12);
        bst.add(35);
        bst.add(70);
        bst.add(34);
        bst.add(50);
        bst.add(68);
        bst.add(99);
        System.out.println(bst.inOrder());
        System.out.println(bst.contains(34));

        System.out.println(bst.minValue());
        System.out.println(bst.maxValue());

        System.out.println(bst.removeMin());
        System.out.println(bst.inOrder());

        System.out.println(bst.removeMax());
        System.out.println(bst.inOrder());

        bst.remove(66);
        System.out.println(bst.inOrder());

        // bst.set(50, 100); // 破坏了二叉查找树的性质
        System.out.println(bst.inOrder());
    }
}
