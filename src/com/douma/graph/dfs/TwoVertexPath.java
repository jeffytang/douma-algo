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
public class TwoVertexPath {
    private Graph g;

    private int source;
    private int target;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private int[] prevs;

    private List<Integer> res;

    public TwoVertexPath(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;

        this.res = new ArrayList<>();

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        // 将每个顶点的前一个顶点初始化为 -1
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }
        // 深度优先遍历，这里只需要从指定的源顶点遍历就可以
        // 源顶点的前一个顶点设置为源顶点本身
        dfs(source, source);

        // 计算一次
        path();
    }

    // 递归遍历顶点 v，并且维护顶点 v 的前一个顶点的信息
    private boolean dfs(int v, int prev) {
        visited[v] = true;
        // 维护顶点 v 的前一个顶点
        prevs[v] = prev;
        if (v == target) return true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // v 是 w 的前一个顶点
                // 如果已经找到了，不用再去遍历 v 的下一个相邻节点了
                if (dfs(w, v)) return true;
            }
        }
        return false;
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

    private List<Integer> path() {
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

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        TwoVertexPath graphDFS = new TwoVertexPath(g, 0, 6);
        System.out.println(graphDFS.getRes());
    }
}
