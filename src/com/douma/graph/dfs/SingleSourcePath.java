package com.douma.graph.dfs;

import com.douma.graph.AdjSet;
import com.douma.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class SingleSourcePath {
    private Graph g;

    private int source;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private int[] prevs;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        // 将每个顶点的前一个顶点初始化为 -1
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }
        // 深度优先遍历，这里只需要从指定的源顶点遍历就可以
        // 源顶点的前一个顶点设置为源顶点本身
        dfs(source, source);
    }

    // 递归遍历顶点 v，并且维护顶点 v 的前一个顶点的信息
    private void dfs(int v, int prev) {
        visited[v] = true;
        // 维护顶点 v 的前一个顶点
        prevs[v] = prev;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // v 是 w 的前一个顶点
                dfs(w, v);
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

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(g, 0);
        System.out.println(graphDFS.path(6));
    }
}
