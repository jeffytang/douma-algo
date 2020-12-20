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
public class SingleSourcePath1 {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;

    private int source;
    private int target;

    public SingleSourcePath1(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);

        bfs(source);
    }

    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == target) {
                return;
            }
            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                }
            }
        }
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> path() {
        List<Integer> res = new ArrayList<>();
        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected()) {
            return res;
        }
        // 2. 根据 prevs 信息找到路径
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath1 graphBFS = new SingleSourcePath1(g, 0, 6);
        System.out.println(graphBFS.path());
    }
}
