package com.douma.highlevel.set;

import com.douma.TestFileReader;

import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class TestSet {
    private static double testSet(Set<String> set, List<String> words) {
        for (String word : words)
            set.add(word);

        long start = System.nanoTime();


        for (int i = 0; i < 10000; i++) {
            set.contains("father");
        }


        long end = System.nanoTime();

        return (end - start) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        List<String> words = TestFileReader.readFile("data/test-data.txt");
        Set<String> arrSet = new ArraySet<>();
        double time1 = testSet(arrSet, words);
        System.out.println("ArraySet ：" + time1);

        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("LinkedListSet ：" + time2);

        // 原因：
        // 数组是一块连续的内存空间
        // 在 cpu 读取数组中的一个元素的时候，会将这个元素旁边的多个元素一起加载进 cpu 的高速缓存
        // 这样下次读取的话，就直接从高速缓存中读取

        // 链表的数据是分散在内存中的
        // cpu 每次读取元素的时候都需要从主存中读取

        // 所以数组的顺序遍历会比链表的顺序遍历要快
    }

}
