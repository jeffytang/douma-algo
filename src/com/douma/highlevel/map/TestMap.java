package com.douma.highlevel.map;

import com.douma.TestFileReader;

import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class TestMap {
    static List<String> words = TestFileReader.readFile("data\\test-data.txt");

    private static double testMap(Map<String, Integer> map) {
        long startTime = System.nanoTime();
        for (String word : words) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.set(word, count + 1);
            } else {
                map.add(word, 1);
            }
        }
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        Map<String, Integer> llMap = new LinkedListMap<>();
        double time1 = testMap(llMap);
        System.out.println("链表实现的 Map 花的时间：" + time1);

        Map<String, Integer> bstMap = new BSTMap<>();
        double time2 = testMap(bstMap);
        System.out.println("BST 实现的 Map 花的时间：" + time2);

        Map<String, Integer> hashMap = new HashMap<>();
        double time3 = testMap(hashMap);
        System.out.println("Hash 实现的 Map 花的时间：" + time3);
    }
}
