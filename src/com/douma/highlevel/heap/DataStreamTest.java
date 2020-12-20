package com.douma.highlevel.heap;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class DataStreamTest {
    public static void main(String[] args) {
        DataStream2 dataStream = new DataStream2();
        dataStream.add(3);
        System.out.println(dataStream.removeMax()); // 打印 3
        dataStream.add(6);
        dataStream.add(5);
        System.out.println(dataStream.removeMax()); // 打印 6
        dataStream.add(2);
        dataStream.add(1);
        System.out.println(dataStream.removeMax()); // 打印 5
    }
}
