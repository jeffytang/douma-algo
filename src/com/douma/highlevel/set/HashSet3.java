package com.douma.highlevel.set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class HashSet3<E> implements Set<E> {

    private class Node<E> {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    private Node<E>[] data;
    private int size;

    private double loadFactor = 0.75;

    public HashSet3() {
        data = new Node[10];
        this.size = 0;
    }

    public HashSet3(int loadFactor) {
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
    public void add(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            data[index] = new Node(e);
        } else {
            Node<E> prev = null;
            Node<E> curr = data[index];
            while (curr != null) {
                if (e.equals(curr.e)) {
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = new Node(e);
        }
        size++;
        if (size >= data.length * loadFactor) {
            resize(2 * data.length);
        }
    }

    private void resize(int newCapacity) {
        Node[] newData = new Node[newCapacity];
        for (int i = 0; i < data.length; i++) {
            Node<E> curr = data[i];
            while (curr != null) {
                E e = curr.e;
                int newIndex = hash(e, newCapacity);
                Node head = newData[newIndex];
                newData[newIndex] = new Node(e);
                if (head != null) {
                    newData[newIndex].next = head;
                }
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            return;
        }
        Node<E> prev = null;
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                if (prev == null) {
                    // 删除头节点
                    data[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                curr.next = null;
                size--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) return false;
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}
