package com.douma.graph.weighted;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 时间复杂度：O(V * (V + E))
public class Dijkstra {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];
        while (true) { // O(V)
            // 1. 找到当前没有访问的最短路径节点
            int curDis = Integer.MAX_VALUE;
            int curr = -1;
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (!visited[v] && distance[v] < curDis) {
                    curDis = distance[v];
                    curr = v;
                }
            }
            if (curr == -1) break;

            // 2. 确认这个节点的最短路径就是当前大小
            visited[curr] = true;

            // 3. 根据这个节点的最短路径大小，更新其他节点的路径长度
            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                    }
                }
            }
        }
    }

    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/Dijkstra.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
    }
}
