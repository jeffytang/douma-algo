package com.douma.line.algo.recusion;

/**
 * @微信公众号 : 抖码课堂
 * @作者 : 老汤
 */
public class Test {
    public static int sum(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        int one = sum(1);
        int two = one + sum(1);
        int three = two + sum(2);
    }
}
