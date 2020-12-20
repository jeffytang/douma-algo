package com.douma.graph.weighted;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 时间复杂度：O(V * (logV + E))
public class Dijkstra1 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair o) {
            return dis - o.dis;
        }
    }

    public Dijkstra1(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()) { // O(V)
            // 1. 找到当前没有访问的最短路径节点
            int curr = pq.poll().v; // O(logV)
            if (visited[curr]) continue;

            // 2. 确认这个节点的最短路径就是当前大小
            visited[curr] = true;

            // 3. 根据这个节点的最短路径大小，更新其他节点的路径长度
            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                        pq.add(new Pair(w, distance[w]));
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
        Dijkstra1 dijkstra = new Dijkstra1(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
    }
}
