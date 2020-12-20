package com.douma.highlevel.set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class HashSet2<E> implements Set<E> {
    private class Item {
        E data;
        boolean isDeleted;

        public Item(E data) {
            this.data = data;
            this.isDeleted = false;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }

    private Item[] items;
    private int size;
    // 装载因子
    private double loadFactor = 0.75;

    public HashSet2() {
        items = (Item[]) new Object[10];
        this.size = 0;
    }

    public HashSet2(int loadFactor) {
        this();
        this.loadFactor = loadFactor;
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
    public void add(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);
        if (items[index] != null && e.equals(items[index].data)) {
            return;
        }

        // 找到等于 null 或者已经删除的索引
        while (items[index] != null && !items[index].isDeleted) {
            index++;
            index = index % items.length;
        }

        // 这个时候的 index 对应的元素要么是 null ，要么是已经删除的元素
        items[index] = new Item(e);
        size++;

        if (size >= items.length * loadFactor) {
            resize(2 * items.length);
        }

    }

    private void resize(int newCapacity) {
        Item[] newData = (Item[])new Object[newCapacity];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && !items[i].isDeleted) {
                int newIndex = hash(items[i].data, newCapacity);
                newData[newIndex] = items[i];
            }
        }
        items = newData;
    }

    @Override
    public void remove(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);
        // 找到等于 e 或者元素为 null 的索引
        while (items[index] != null && !e.equals(items[index].data)) {
            index++;
            index = index % items.length;
        }
        if (items[index] != null) {
            items[index].isDeleted = true;
            size--;
        }
    }

    @Override
    public boolean contains(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);

        // 找到等于 e 或者元素为 null 的索引
        while (items[index] != null && !e.equals(items[index].data)) {
            index++;
            index = index % items.length;
        }

        return items[index] != null && !items[index].isDeleted;
    }
}
