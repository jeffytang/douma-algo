package com.douma.highlevel.set;

import com.douma.tree.bst.BST;

import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 *
 * 有序的 Set
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public int size() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) { // O(logn)
        bst.add(e);
    }

    @Override
    public void remove(E e) { // O(logn)
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) { // O(logn)
        return bst.contains(e);
    }

    public List<E> getAllElements() {
        return bst.inOrder();
    }

    public static void main(String[] args) {
        BSTSet<Integer> set = new BSTSet<>();
        set.add(2);
        set.add(1);
        set.add(9);
        set.add(5);
        set.add(2);
        System.out.println(set.getAllElements());
    }
}
