package com.douma.graph.weighted;

import com.douma.graph.dfs.CC1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 时间复杂度：O(E*logE)
public class Prim1 {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim1(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // g 是连通图
        CC1 cc = new CC1(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];
        // 选择顶点 0 作为切分的一部分
        visited[0] = true;

        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        for (int w : g.adj(0)) {
            pq.add(new WeightedEdge(0, w, g.getWeight(0, w)));
        }
        while (!pq.isEmpty()) { // O(E)
            // 1. 拿到最小横切边
            WeightedEdge minEdge = pq.poll(); // O(logE)
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                // 不是横切边
                continue;
            }

            // 2. 加入到最小生成树中
            result.add(minEdge);

            // 3. 扩展切分
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;
            // 将新的横切边放入到优先队列
            for (int w : g.adj(newV)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newV, w, g.getWeight(newV, w)));
                }
            }
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("data/prim.txt");
        Prim1 prim = new Prim1(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }
}
