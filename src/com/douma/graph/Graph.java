package com.douma.graph;

import java.util.Collection;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public interface Graph {
    /**
     * 获取图的边数
     * @return
     */
    int getE();

    /**
     * 获取图的顶点数
     * @return
     */
    int getV();

    /**
     * 判断两个指定的顶点之间是否有边
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v, int w);

    /**
     * 获取指定顶点所有相邻的顶点
     * @param v
     * @return
     */
    Collection<Integer> adj(int v);

    /**
     * 获取指定顶点的度数
     * @param v
     * @return
     */
    int degree(int v);
}
