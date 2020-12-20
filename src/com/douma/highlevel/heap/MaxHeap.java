package com.douma.highlevel.heap;

import com.douma.line.array.ArrayList;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap(E[] arr) {
        this.data = new ArrayList<>(arr);
        // O(n)
        for (int i = lastNonLeafIndex(); i >= 0 ; i--) { // O(n)
            siftDown(i); // O(logn)
        }
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }
    // 返回一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }
    // 返回一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }
    // 返回一个索引所表示的元素的父节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }
    // 返回最后一个非叶子节点的索引值
    private int lastNonLeafIndex() {
        // 最后一个叶子节点的索引
        int lastLeafIndex = data.getSize() - 1;
        // 返回最后一个叶子节点的父节点的索引值就是最后一个非叶子节点的索引
        return parent(lastLeafIndex);
    }
    // 往大顶堆中添加一个元素
    // 时间复杂度：O(logn)
    public void add(E e) {
        // 1. 先将元素插入到数组的最后
        data.addLast(e);
        // 2. 将最后一个节点 e 上浮
        siftUp(data.getSize() - 1);
    }
    // 将数组中索引为 index 的元素进行上浮
    private void siftUp(int index) {
        E e = data.get(index);
        // 循环比较并上浮，一直到节点是根节点
        while (index > 0) {
            E parentNode = data.get(parent(index));

            if (e.compareTo(parentNode) <= 0) break;

            // 将父节点插入到子节点中
            data.set(index, parentNode);

            // 更新需要插入的元素节点的索引为它之前的父节点的索引
            index = parent(index);
        }
        // 将节点插入到正确的位置上
        data.set(index, e);
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new RuntimeException("Heap is Empty");
        return data.getFirst();
    }

    // 从大顶堆中取出并删除最大值
    // 时间复杂度：O(logn)
    public E removeMax() {
        E max = findMax();
        // 1. 将最后一个节点替换根节点
        E last = data.getLast();
        data.set(0, last);

        // 2. 删除最后一个节点
        data.removeLast();

        // 3. 将新的根节点做下沉操作
        if (!data.isEmpty()) siftDown(0);

        return max;
    }

    private void siftDown(int index) {
        E e = data.get(index);
        while (leftChild(index) < data.getSize()) {
            // 1. 找到节点的左右子节点值最大的那个节点
            int maxNodeIndex = leftChild(index);
            if (rightChild(index) < data.getSize()) {
                if (data.get(rightChild(index)).compareTo(data.get(leftChild(index))) > 0) {
                    maxNodeIndex = rightChild(index);
                }
            }

            if (e.compareTo(data.get(maxNodeIndex)) >= 0) break;

            // 2. 将节点和最大节点的值比较，如果小的话，就需要交换
            data.set(index, data.get(maxNodeIndex));

            index = maxNodeIndex;
        }
        data.set(index, e);
    }
}
