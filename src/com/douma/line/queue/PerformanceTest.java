package com.douma.line.queue;

import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class PerformanceTest {
    private static Random random = new Random();

    private static double testQueue(Queue<Integer> queue, int cnt) {
        long startTime = System.nanoTime();

        for (int i = 0; i < cnt; i++) {
            queue.enqueue(random.nextInt());
        }
        for (int i = 0; i < cnt; i++) {
            queue.dequeue();
        }

        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int cnt = 10000000;

        Queue<Integer> queue = new LoopQueue<>();
        double time1 = testQueue(queue, cnt);
        System.out.println("LoopQueue 花费的时间：" + time1);

        queue = new LinkedListQueue2<>();
        double time2 = testQueue(queue, cnt);
        System.out.println("LinkedListQueue2 花费的时间：" + time2);
    }
}
