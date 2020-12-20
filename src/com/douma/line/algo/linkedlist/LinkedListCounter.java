package com.douma.line.algo.linkedlist;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class LinkedListCounter {
    /**
     * 计算一个链表中包含多少个节点数
     * @param head  表头
     * @return  链表包含的节点数
     */
    public int count(ListNode head) {
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        // 使用 while 循环，遍历整个链表
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    public int countFor(ListNode head) {
        if (head == null) return 0;
        int cnt = 0;
        // 使用 for 循环，遍历整个链表
        for (ListNode curr = head; curr != null; curr = curr.next) {
            cnt++;
        }
        return cnt;
    }

    /**
     * 计算链表等于目标值的节点个数
     * @param head  表头
     * @param target 目标值
     * @return 等于目标值的节点个数
     */
    public int countTarget(ListNode head, int target) {
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        // 使用 while 循环，遍历整个链表
        while (curr != null) {
            if (curr.val == target) cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34, 12});
        int cnt = new LinkedListCounter().countTarget(head, 12);
        System.out.println(cnt);
    }
}
