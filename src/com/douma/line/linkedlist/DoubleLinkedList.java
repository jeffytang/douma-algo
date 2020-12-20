package com.douma.line.linkedlist;

import java.util.NoSuchElementException;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class DoubleLinkedList<E> {
    private class Node {
        E e;
        Node prev;
        Node next;

        public Node(Node prev, E e, Node next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(null, e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node first;
    private Node last;
    private int size;

    public DoubleLinkedList() {
        first = last = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 找到指定索引 index 所在的节点的元素值
     * @param index
     * @return
     */
    // 时间复杂度是 O(n)
    public E get(int index) {
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        return node.e;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    // 时间复杂度是 O(n)
    private Node node(int index) {
        if (index < 0 || index >= size)
            return null;

        Node curr = null;
        if (index < size / 2) {
            curr = first;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = last;
            for (int i = 0; i < size - index - 1; i++) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    // 时间复杂度是 O(n)
    public void set(int index, E e) {
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        node.e = e;
    }

    /**
     * 往链表的表头插入节点
     * @param e
     */
    public void addFirst(E e) {
        Node newNode = new Node(e);
        if (first == null) {
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    /**
     * 往链表尾巴插入新节点
     * @param e
     */
    public void addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            first = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * 往指定索引位置插入节点
     * @param index
     * @param e
     */
    // 时间复杂度是 O(n)
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        if (index == size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        } else {
            Node oldNode = node(index);
            Node prev = oldNode.prev;
            Node newNode = new Node(prev, e, oldNode);
            oldNode.prev = newNode;
            prev.next = newNode;

            size++;
        }
    }

    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        E e = first.e;
        Node next = first.next;
        if (next == null) {
            first = null;
            last = null;
        } else {
            first.next = null;
            next.prev = null;
            first = next;
        }
        size--;
        return e;
    }

    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        E e = last.e;
        Node prev = last.prev;
        if (prev == null) {
            last = null;
            first = null;
        } else {
            last.prev = null;
            prev.next = null;
            last = prev;
        }
        size--;
        return e;
    }

    // 时间复杂度是 O(n)
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        Node delNode = node(index);
        E e = delNode.e;
        Node prev = delNode.prev;
        Node next = delNode.next;

        // 将删除节点的前后节点联系起来
        prev.next = next;
        next.prev = prev;

        // 将删除节点从链表中断开
        delNode.next = null;
        delNode.prev = null;

        size--;
        return e;
    }
}
