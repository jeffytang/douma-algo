package com.douma.graph.dfs;

import com.douma.graph.AdjSet;
import com.douma.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class CC1 {
    private Graph g;

    // 用于防止一个节点被重复访问
    private int[] visited;

    private int ccCount = 0;

    public CC1(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (visited[v] == -1) {
                ccCount++;
                dfs(v, ccCount);
            }
        }
    }


    private void dfs(int v, int ccId) {
        visited[v] = ccId;
        for (int w : g.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccId);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];
        // Arrays.fill(res, new ArrayList<>());
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }

        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        CC1 graphDFS = new CC1(g);
        System.out.println(Arrays.toString(graphDFS.components()));

        System.out.println(graphDFS.isConnected(0, 6));
    }
}
