package com.douma.graph.direct;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class TopologySortBFS {
    private GraphImpl g;

    private int[] res;
    private boolean hasCycle = false;

    public TopologySortBFS(GraphImpl g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("只能对有向图进行拓扑排序");
        }
        this.g = g;

        int[] indegrees = new int[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            indegrees[v] = g.indegree(v);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.getV(); v++) {
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }
        this.res = new int[g.getV()];
        int index = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            res[index++] = v;
            for (int w : g.adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    queue.add(w);
                }
            }
        }
        if (index != g.getV()) {
            hasCycle = true;
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }

    public static void main(String[] args) {
        GraphImpl g = new GraphImpl("data/directedgraph-dfs.txt", true);
        TopologySortBFS bfs = new TopologySortBFS(g);
        System.out.println(bfs.isHasCycle());
        System.out.println(Arrays.toString(bfs.getRes()));
    }
}
