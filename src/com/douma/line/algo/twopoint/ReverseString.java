package com.douma.line.algo.twopoint;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ReverseString {
    // 时间复杂度 O(n/2) = O(n)
    // 空间复杂度 O(1)
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
        return new String(chars);
    }
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public String reverseString1(String s) {
        char[] chars = s.toCharArray();
        char[] tmp = new char[chars.length];

        int j = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            tmp[j] = chars[i];
            j--;
        }
        return new String(tmp);
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(new ReverseString().reverseString(s));
    }
}
