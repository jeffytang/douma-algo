package com.douma.graph.direct;

import com.douma.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 空间复杂度：O(V + E)
// 支持无向无权图和有向无权图
public class GraphImpl implements Graph {
    private int V; // 顶点的个数
    private int E; // 边的个数
    private TreeSet<Integer>[] adj; // 邻接表

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

    // 建图时间复杂度：O(E*logV)
    public GraphImpl(String fileName, boolean isDirected) {
        this.isDirected = isDirected;
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            this.adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }
            this.indegrees = new int[V];
            this.outdegrees = new int[V];
            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.valueOf(arr[0]);
                validateVertex(a);
                int b = Integer.valueOf(arr[1]);
                validateVertex(b);
                // 检测自环边
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误");
                }
                // 检测平行边
                if (adj[a].contains(b)) { // O(logV)
                    throw new RuntimeException("出现了平行边，错误");
                }
                adj[a].add(b); // a -> b
                // 如果是有向图的话，统计顶点的出度和入度
                if (isDirected) {
                    outdegrees[a]++;
                    indegrees[b]++;
                }
                if (!isDirected)
                    adj[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int getV() {
        return V;
    }
    @Override
    public int getE() {
        return E;
    }

    // 判断两个指定的顶点之间是否有边
    // 时间复杂度：O(logV)
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v].contains(w);
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(1)
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        return adj[v];
    }

    // 获取指定顶点的度数
    @Override
    public int degree(int v) {
        if (isDirected) {
            throw new RuntimeException("只有无向图才可以计算顶点的度数");
        }
        return adj(v).size();
    }

    public int indegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("只有有向图才可以计算顶点的入度");
        }
        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("只有有向图才可以计算顶点的出度");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d，isDirected = %b \n", V, E, isDirected));
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl("data/graph.txt", true);
        System.out.println(graph);
    }
}
