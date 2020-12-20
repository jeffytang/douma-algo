package com.douma.line.stack.practice;

import java.util.Stack;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _20_ValidParentheses {
    public boolean isValid(String s) {
        if (s == null) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '(' || c == '{' || c == '[') {
                // 如果是左括号，则直接入栈
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                // 判断栈顶元素是否等于左括号对应的右括号
                char topElement = stack.pop();
                char matched = '#';
                if (c == ')')
                    matched = '(';
                else if (c == ']')
                    matched = '[';
                else
                    matched = '{';

                if (matched != topElement)
                    return false;
            }
        }
        // 如果栈不为空，那么也算没有匹配好
        return stack.isEmpty();
    }






    public boolean isValid1(String s) {
        StringBuilder sb = new StringBuilder(s);
        int count = sb.length() / 2;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < sb.length() - 1; j++) {
                char c1 = sb.charAt(j);
                char c2 = sb.charAt(j + 1);
                // 如果相邻的字符符合要求，则删除
                if (isMatched(c1, c2)) {
                    sb.delete(j, j + 2);
                    break;
                }
            }
        }
        return sb.length() == 0;
    }

    private boolean isMatched(char c1, char c2) {
        if (c1 == '(')
            return c2 == ')';
        else if (c1 == '[')
            return c2 == ']';
        else if (c1 == '{')
            return c2 == '}';
        else
            return false;
    }
}
