package com.douma.line.algo.linkedlist;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _19_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        // fast 先走 n + 1
        while (n-- >= 0) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        // 1. 计算链表长度
        int length = 0;
        ListNode curr = dummyNode;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        // 2. 找到倒数第 n 个节点的前一个节点
        ListNode prev = dummyNode;
        for (int i = 1; i < length - n; i++) {
            prev = prev.next;
        }
        // 3. 删除倒数第 n 个节点
        ListNode delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }
}
