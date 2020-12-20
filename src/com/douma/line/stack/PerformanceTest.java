package com.douma.line.stack;

import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class PerformanceTest {
    private static Random random = new Random();

    private static double testStack(Stack<Integer> stack, int cnt) {
        long startTime = System.nanoTime();

        for (int i = 0; i < cnt; i++) {
            stack.push(random.nextInt());
        }
        for (int i = 0; i < cnt; i++) {
            stack.pop();
        }

        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int cnt = 100000000;

        Stack<Integer> stack = new DynamicArrayStack<>(10);
        double time1 = testStack(stack, cnt);
        System.out.println("DynamicArrayStack 花费的时间：" + time1);

        stack = new LinkedListStack<>();
        double time2 = testStack(stack, cnt);
        System.out.println("LinkedListStack 花费的时间：" + time2);
    }


}
