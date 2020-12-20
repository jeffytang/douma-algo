package com.douma.line.algo.sort.compare;

import java.util.Comparator;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class TestObjectCompare {
    public static void main(String[] args) {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        int res = comparator.compare(p1, p2);
        System.out.println(res);
    }
}


