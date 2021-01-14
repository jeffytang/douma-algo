package com.douma.highlevel.set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class HashSet<E> implements Set<E> {
    private E[] data;
    private int size;

    public HashSet() {
        data = (E[]) new Object[10];
        this.size = 0;
    }

    private int hash(E e, int length) {
        return Math.abs(e.hashCode()) % length;
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
    public void add(E e) { // O(1)
        int index = hash(e, data.length);
        if (data[index] != null) {
            data[index] = e;
            size++;

            if (size == data.length) {
                resize(2 * data.length);
            }
        }
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                int newIndex = hash(data[i], newCapacity);
                newData[newIndex] = data[i];
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) { // O(1)
        int index = hash(e, data.length);
        // bug 修复：在删除某个元素之前，先判断这个元素是否存在
        // 存在的话才删除
        if (data[index] != null) {
            data[index] = null;
            size--;
        }
    }

    @Override
    public boolean contains(E e) { // O(1)
        int index = hash(e, data.length);
        return data[index] != null;
    }
}
