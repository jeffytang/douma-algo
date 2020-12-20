package com.douma.highlevel.skiplist;

import java.util.Random;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class SkipList<E extends Comparable<E>> {
    // 表示跳表的高度，包括原始链表这一层
    private static final int MAX_LEVEL = 16;

    /**
     * 跳表的节点，每个节点记录了当前节点数据和所在层的下一个节点
     */
    private class Node<E extends Comparable<E>> {
        E data;
        /*
        表示当前节点在所有层的下一个节点的指针，从上层切换到下层，就是数组下标减去 1
            nextNodes[0] 表示当前节点在第一层的下一个节点
            nextNodes[1] 表示当前节点在第二层的下一个节点
            nextNodes[2] 表示当前节点在第三层的下一个节点
            nextNodes[3] 表示当前节点在第四层的下一个节点
            ...
            nextNodes[15] 表示当前节点在第十六层的下一个节点
        */
        Node[] nextNodes;

        // 记录这个节点维护的索引节点的最大高度
        int maxIndexLevel = 0;

        Node(E data) {
            this.data = data;
            nextNodes = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxIndexLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
    // 表示当前跳表最大的高度 (包含了原始链表层，所以默认初始值是 1)
    private int levelCount;
    // 虚拟头头节点
    private Node dummyHead;
    private Random random = new Random();


    public SkipList() {
        this.levelCount = 1;
        this.dummyHead = new Node(-1);
    }

    public boolean contains(E e) {
        return get(e) != null;
    }

    public Node get(E e) {
        Node curr = dummyHead;
        // 从最高一层往下一层一层的寻找查找元素所在的区间
        for (int i = levelCount - 1; i >= 0; i--) {
            while (curr.nextNodes[i] != null
                    && curr.nextNodes[i].data.compareTo(e) < 0) {
                curr = curr.nextNodes[i];
            }
        }

        if (curr.nextNodes[0] != null
                && curr.nextNodes[0].data.compareTo(e) == 0) {
            return curr.nextNodes[0];
        }

        return null;
    }



    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void add(E e) {
        // 维护一层索引
        int level = dummyHead.nextNodes[0] == null ? 1 : randomLevel();
        // 先将两层的前一个节点都初始化为虚拟头节点
        Node[] prevNodes = new Node[level];
        for (int i = 0; i < level; i++) {
            prevNodes[i] = dummyHead;
        }

        // 1. 找到节点要插入的位置的前一个节点 prev
        Node prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (prev.nextNodes[i] != null
                    && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }

            // 维护每一层的前一个节点
            if (i < level)
                prevNodes[i] = prev;
        }

        // 2. 在每一层的正确的位置插入新节点
        Node newNode = new Node(e);
        newNode.maxIndexLevel = level;
        for (int i = 0; i < level; i++) {
            newNode.nextNodes[i] = prevNodes[i].nextNodes[i];
            prevNodes[i].nextNodes[i] = newNode;
        }

        // 更新链表的高度
        if (levelCount < level) levelCount = level;
    }

    public void remove(E e) {
        // 1. 找到需要删除节点的前一个节点，以及删除节点对应的索引节点的前一个索引节点
        Node<E>[] prevNodes = new Node[levelCount];
        Node prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (prev.nextNodes[i] != null
                    && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }

            prevNodes[i] = prev;
        }

        // 2. 对每一层进行删除节点
        // 先判断节点是否存在，存在的话才执行删除
        if (prev.nextNodes[0] != null
                && prev.nextNodes[0].data.compareTo(e) == 0) {
            // 对每一层进行删除节点
            for (int i = levelCount - 1; i > 0; i--) {
                Node delNode = prevNodes[i].nextNodes[i];
                if (delNode != null && delNode.data.compareTo(e) == 0) {
                    prevNodes[i].nextNodes[i] = delNode.nextNodes[i];
                    delNode.nextNodes[i] = null;
                }
            }
        }
    }
}
