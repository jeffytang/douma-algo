package com.douma;

/**
 * @微信公众号 : 抖码课堂
 * @作者 : 老汤
 */
public class Test {
    public static void main(String[] args) {
        Integer data = 60;

        int index = data.hashCode() % 16;

        System.out.println(index);

    }
}
