package com.douma.line.algo.sort.compare;

import com.douma.line.algo.sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ThreeWayQuickSorter<E extends Comparable<E>> extends Sorter {
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int lo, int hi) {
        if (lo >= hi) return;
        // 分区
        E pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if (data[i].compareTo(pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i].compareTo(pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great +1, hi);
    }

    public void sort(E[] data, Comparator<E> c) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1, c);
    }

    private void sort(E[] data, int lo, int hi, Comparator<E> c) {
        if (lo >= hi) return;
        // 分区
        E pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if (c.compare(data[i], pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (c.compare(data[i], pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1, c);
        sort(data, great +1, hi, c);
    }

    public static void main(String[] args) {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};
        Comparator<Person> comparator = ((o1, o2) -> o2.getAge() - o1.getAge());
        new ThreeWayQuickSorter().sort(people, comparator);
        System.out.println(Arrays.toString(people));
    }
}
