package com.douma.graph.weighted;

import com.douma.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 空间复杂度：O(V + E)
public class WeightedAdjSet implements Graph {
    private int V; // 顶点的个数
    private int E; // 边的个数
    private TreeMap<Integer, Integer>[] adj; // 邻接表

    // 建图时间复杂度：O(E*logV)
    public WeightedAdjSet(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            this.adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }
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
                if (adj[a].containsKey(b)) { // O(logV)
                    throw new RuntimeException("出现了平行边，错误");
                }
                int weight = Integer.valueOf(arr[2]);
                adj[a].put(b, weight);
                adj[b].put(a, weight);
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

        return adj[v].containsKey(w);
    }
    // 获取指定边的权重值
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        return -1;
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(1)
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        return adj[v].keySet();
    }

    // 获取指定顶点的度数
    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            Map<Integer, Integer> adjMap = adj[v];
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                sb.append("(" + entry.getKey() + ", " + entry.getValue() + ")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedAdjSet adjList = new WeightedAdjSet("data/weighted-graph.txt");
        System.out.println(adjList);
    }
}
