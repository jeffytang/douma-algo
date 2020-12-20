package com.douma.graph.direct;

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
public class GraphDFSR {
    private Graph g;

    private List<Integer> res;
    // 用于防止一个节点被重复访问
    private boolean[] visited;

    public GraphDFSR(Graph g) {
        this.g = g;

        if (g == null) return;

        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                dfs(v);
            }
        }
    }


    private void dfs(int v) {
        res.add(v);
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes());
    }
}
