package com.douma.graph.floodfill;

import com.douma.graph.AdjSet;
import com.douma.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class CCMaxCount {
    private Graph g;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private int maxVertexNum = 0;

    public CCMaxCount(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    public int getMaxVertexNum() {
        return maxVertexNum;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        CCMaxCount graphDFS = new CCMaxCount(g);
        System.out.println(graphDFS.getMaxVertexNum());
    }
}
