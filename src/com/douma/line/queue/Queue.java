package com.douma.line.queue;

public interface Queue<E> {
    /**
     * 查询队列中的元素个数
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入队
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 查看队首的元素
     * @return
     */
    E getFront();
}
