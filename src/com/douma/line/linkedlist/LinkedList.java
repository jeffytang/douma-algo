package com.douma.line.linkedlist;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class LinkedList<E> {
    private class Node {
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

        @Override
        public String toString() {
            return e.toString();
        }
    }
    // 虚拟头节点
    private Node dummyHead;
    // 长度
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 查询指定索引的节点的值
     * @param index
     * @return
     */
    // 时间复杂度： O(n)
    public E get(int index) {
        // 检查 index 的合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    // 时间复杂度： O(1)
    public E getFirst() {
        return get(0);
    }

    // 时间复杂度： O(n)
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改指定索引的节点元素
     * @param index
     * @param e
     */
    // 时间复杂度： O(n)
    public void set(int index, E e) {
        // 检查 index 的合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.e = e;
    }

    /**
     * 在链表表头新增节点
     * @param e 新增节点需要存储的数据
     */
    // 时间复杂度： O(1)
    public void addFirst(E e) {
        add(0, e);
    }

    // 时间复杂度： O(n)
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在指定索引的位置插入新的节点
     * @param index 需要插入的位置
     * @param e 需要插入的数据
     */
    // 时间复杂度： O(n)
    public void add(int index, E e) {
        // 检查 index 的合法性
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);

        size++;

    }

    /**
     * 删除链表的头节点
     * @return
     */
    // 时间复杂度： O(1)
    public E removeFirst() {
        return remove(0);
    }

    // 时间复杂度： O(n)
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定索引的节点，并返回删除的节点的值
     * @param index
     * @return
     */
    // 时间复杂度： O(n)
    public E remove(int index) {
        // 检查 index 的合法性
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size--;

        return delNode.e;
    }

    public void removeElement(E e) {
        if (dummyHead.next == null)
            throw new IllegalArgumentException("removeElement failed, LinkedList is Empty");

        Node prev = dummyHead;
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.e.equals(e)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        prev.next = curr.next;
        curr.next = null;
    }

    /**
     * 判断链表中是否存在指定元素
     * @param e
     * @return
     */
    // 时间复杂度： O(n)
    public boolean contains(E e) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.e.equals(e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;
        while (curr != null) {
            sb.append(curr + "=>");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
