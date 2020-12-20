package com.douma.graph.direct;

import com.douma.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class CycleDetection {
    private Graph g;
    private boolean hasCycle = false;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private boolean[] isOnPath;

    public CycleDetection(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }


    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else { // 否则，w 顶点已经被访问
                if (isOnPath[w]) return true;
            }
        }
        isOnPath[v] = false;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.isHasCycle());
    }
}
