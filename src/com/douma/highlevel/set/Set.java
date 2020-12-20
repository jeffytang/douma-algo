package com.douma.highlevel.set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void add(E e);

    void remove(E e);

    boolean contains(E e);
}
