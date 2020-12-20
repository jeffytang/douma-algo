package com.douma.highlevel.heap;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class DataStream1 {
    private class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node dummyNode;

    public DataStream1() {
        this.dummyNode = new Node(-1);
    }

    // O(n)
    public void add(int val) {
        // 找到第一个比 val 小的节点
        Node prev = dummyNode;
        Node curr = dummyNode.next;
        while (curr != null) {
            if (curr.val < val) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        // 将元素添加到第一个比它小的节点的前面
        Node newNode = new Node(val);
        newNode.next = curr;
        prev.next = newNode;
    }

    // O(1)
    public int removeMax() {
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax 失败，因为数据流中没有元素");

        // 保存需要返回的最大值 (头节点的值)
        int res = dummyNode.next.val;

        // 删除头节点
        Node head = dummyNode.next;
        dummyNode.next = head.next;
        head.next = null;

        return res;
    }
}
