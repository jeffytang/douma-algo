package com.douma.line.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class QueueTest {
    public static void main(String[] args) {
        java.util.Deque<Integer> queue = new LinkedList<>();
        queue.addFirst(10);
        System.out.println(queue);

        queue.addFirst(20);
        System.out.println(queue);

        queue.addFirst(30);
        System.out.println(queue);

        queue.removeLast();
        System.out.println(queue);

        queue.removeLast();
        System.out.println(queue);
    }
}
