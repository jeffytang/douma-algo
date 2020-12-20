package com.douma.highlevel.set;

import com.douma.line.linkedlist.LinkedList;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
        this.data = new LinkedList<>();
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(E e) { // O(n)
        if (!data.contains(e)) {
            data.addFirst(e);
        }
    }

    @Override
    public void remove(E e) { // O(n)
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) { // O(n)
        return data.contains(e);
    }

    @Override
    public String toString() {
        return "LinkedListSet{" +
                "data=" + data.toString() +
                '}';
    }

    public static void main(String[] args) {
        Set<Integer> set = new LinkedListSet<>();
        set.add(2);
        set.add(4);
        set.add(1);

        System.out.println(set);

        set.add(2);
        System.out.println(set);

        System.out.println(set.contains(4));

        set.remove(1);
        System.out.println(set);
    }
}
