package com.douma.line.algo.linkedlist;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _203_RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode curr = head;
        ListNode prev = dummyNode;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = prev.next;
        }
        return dummyNode.next;
    }

    public ListNode removeElements1(ListNode head, int val) {
        // 1. 删除表头节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }
        if (head == null) return null;

        // 2. 删除非表头节点
        ListNode curr = head.next;
        ListNode prev = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = prev.next;
        }

        return head;
    }
}
