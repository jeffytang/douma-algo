package com.douma.graph.bfs;

import com.douma.graph.AdjSet;
import com.douma.graph.Graph;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class SingleSourceShortestPath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;
    private int[] distance;

    private int source;

    public SingleSourceShortestPath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        this.distance = new int[g.getV()];
        Arrays.fill(this.prevs, -1);
        Arrays.fill(this.distance, -1);

        bfs(source);
    }

    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;
        distance[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                    // 维护顶点 w 的距离为前一个顶点的距离 +1
                    distance[w] = distance[curr] + 1;
                }
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    // O(n)
    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected(target)) {
            return res;
        }
        // 2. 根据 prevs 信息找到路径
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);

        return res;
    }

    // 返回从 source 到 target 两点之间的距离
    // O(n) -> O(1)
    public int distance(int target) {
        validateVertex(target);
        return distance[target];
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourceShortestPath graphBFS = new SingleSourceShortestPath(g, 0);
        System.out.println(graphBFS.path(6));
        System.out.println(graphBFS.distance(6));
    }
}
