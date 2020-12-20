package com.douma.line.algo.sort;

import com.douma.line.algo.sort.compare.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class JavaSorter {
    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        Arrays.sort(data); // 通用的排序
        System.out.println(Arrays.toString(data));

        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};
        //Arrays.sort(people);
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        //Arrays.sort(people, comparator);
        // 小规模数据的话选择插入排序
        // 大规模数据的话选择归并排序
        //      (老版本使用的是递归实现的归并，而新版本使用的则不是递归实现的归并)
        //System.out.println(Arrays.toString(people));

        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        Collections.sort(list, comparator);
        // 底层：Arrays.sort
        System.out.println(list);
    }
}
