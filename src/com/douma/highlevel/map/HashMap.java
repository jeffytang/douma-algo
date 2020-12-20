package com.douma.highlevel.map;

import java.util.NoSuchElementException;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class HashMap<K, V> implements Map<K, V> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + "->" + value.toString();
        }
    }

    private Node<K, V>[] data;
    private int size;
    private double loadFactor;

    public HashMap(int initCapacity, double loadFactor) {
        this.data = new Node[initCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public HashMap(int initCapacity) {
        this(initCapacity, 0.75);
    }

    public HashMap(double loadFactor) {
        this(10, loadFactor);
    }

    public HashMap() {
        this(10, 0.75);
    }
    // 计算一个 key 对应的索引值
    private int hash(K key, int length) {
        return Math.abs(key.hashCode()) % length;
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
        int index = hash(key, data.length);
        Node<K, V> curr = getNode(key, index);
        if (curr == null) {
            data[index] = new Node(key, value, data[index]);
            size++;
            if (size >= data.length * loadFactor) {
                resize(2 * data.length);
            }
        } else {
            curr.value = value;
        }
    }

    private Node<K, V> getNode(K key, int index) {
        Node<K, V> curr = data[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    private void resize(int newCapacity) {
        Node[] newData = new Node[newCapacity];
        for (int i = 0; i < data.length; i++) {
            Node<K, V> curr = data[i];
            while (curr != null) {
                K key = curr.key;
                int newIndex = hash(key, newCapacity);
                Node head = newData[newIndex];
                newData[newIndex] = new Node(key, curr.value);
                if (head != null) {
                    newData[newIndex].next = head;
                }
                curr = curr.next;
            }
        }
        data = newData;
    }

    @Override
    public V remove(K key) {
        int index = hash(key, data.length);
        if (data[index] == null) {
            return null;
        }
        Node<K, V> prev = null;
        Node<K, V> curr = data[index];
        while (curr != null) {
            if (key.equals(curr.key)) {
                if (prev == null) {
                    // 删除头节点
                    data[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                curr.next = null;
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        if (node == null) {
            throw new NoSuchElementException("没有对应的 key ：" + key);
        }
        node.value = newValue;
    }

    @Override
    public V get(K key) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        return node == null ? null : node.value;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        return node != null;
    }
}
