package com.douma.line.algo.sort;

import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class SortCompare {
    private static Random random = new Random();

    private static int[] genData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    private static long time(String sortType, int[] data) {
        long start = System.currentTimeMillis();

        if (sortType.equals("bubble")) new BubbleSorter().sort(data);
        else if (sortType.equals("selection")) new SelectionSorter().sort(data);
        else if (sortType.equals("insertion")) new InsertionSorter().sort(data);
        else if (sortType.equals("insertion1")) new InsertionSorter().sort1(data);
        else if (sortType.equals("shell")) new ShellSorter().sort(data);

        return System.currentTimeMillis() - start;
    }

    private static long manyTimesSort(String sortType, int n, int k) {
        long totalTime = 0;
        for (int i = 0; i < k; i++) {
            totalTime += time(sortType, genData(n));
        }
        return totalTime;
    }

    public static void main(String[] args) {
        double t1 = manyTimesSort("bubble", 1000, 100);
        double t2 = manyTimesSort("selection", 1000, 100);
        double t3 = manyTimesSort("insertion1", 1000, 100);
        double t4 = manyTimesSort("insertion", 1000, 100);
        double t5 = manyTimesSort("shell", 1000, 100);
        System.out.println(t1 / t2); // t1 > t2
        System.out.println(t2 / t3); // t2 > t3
        System.out.println(t3 / t4); // t3 > t4
        // 结论：插入排序性能最好，其次是选择排序，最后是冒泡排序
        System.out.println(t3 / t5); // t3 > t5
    }
}
