package com.douma.line.algo.linkedlist;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _148_SortList {
    // 自底朝上实现归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 计算链表的长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        for (int step = 0; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;
            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, step);
                // 分割得到下次处理的链表头
                curr = split(right, step);
                // 合并 left 和 right 链表
                prev = merge(left, right, prev);
            }
        }

        return dummy.next;
    }
    // 合并 left 和 right 两个有序链表
    // 将 prev.next 设置为合并之后链表的表头
    // 返回合并之后链表的最后一个节点
    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode curr = prev;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        if (left == null) curr.next = right;
        if (right == null) curr.next = left;
        // 保证 curr 是合并之后链表的最后一个节点
        if (curr.next != null) curr = curr.next;
        return curr;
    }

    // 将 node 从第 step 个节点切断，并返回右边链表的头节点
    private ListNode split(ListNode node, int step) {
        if (node == null) return null;
        // 找到分割点
        for (int i = 1; node.next != null && i < step; i++) {
            node = node.next;
        }
        ListNode right = node.next;
        node.next = null;

        return right;
    }

    // 递归实现
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;

        // 找到中间节点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return dummyNode.next;
    }
}
