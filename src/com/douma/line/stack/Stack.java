package com.douma.line.stack;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public interface Stack<E> {
    /**
     * 查看栈中元素个数
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 进栈
     * 将元素 e 压入栈中
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * 将栈顶的元素出栈
     * @return
     */
    E pop();

    /**
     * 查询栈顶的元素
     * @return
     */
    E peek();
}
