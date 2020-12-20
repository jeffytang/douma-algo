package com.douma.line.algo.sort;

import java.util.ArrayList;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class IntegerArrayQuickSorter extends Sorter {
    public void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1);
        Integer[] dataArr = data.toArray(new Integer[data.size()]);
        sort(dataArr, 0, dataArr.length - 1);

        data.clear(); // 清空
        for (Integer i : dataArr) data.add(i);
    }
    private void sort(Integer[] data, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(data, lo, hi);
        sort(data, lo, j - 1);
        sort(data, j + 1, hi);
    }

    private int partition(Integer[] data, int lo, int hi) {
        int pivot = data[hi];
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            if (data[great] < pivot) {
                swap(data, less, great);
                less++;
            }
        }
        swap(data, less, hi);
        return less;
    }
}
