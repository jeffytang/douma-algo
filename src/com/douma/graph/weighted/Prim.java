package com.douma.graph.weighted;

import com.douma.graph.dfs.CC1;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
// 时间复杂度：O(V*V*E)
public class Prim {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // g 是连通图
        CC1 cc = new CC1(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];
        // 选择顶点 0 作为切分的一部分
        visited[0] = true;
        // 进行 v - 1 次切分，每次切分得到最短的横切边
        for (int i = 0; i < g.getV() - 1; i++) { // O(V)
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (visited[v]) {
                    for (int w : g.adj(v)) { // O(E)
                        // v-w 横切边 // 找到最小的横切边
                        if (!visited[w] && g.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
                }
            }
            result.add(minEdge);

            // 扩展切分
            int v = minEdge.getV();
            int w = minEdge.getW();
            int newV = visited[v] ? w : v;
            visited[newV] = true;
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("data/prim.txt");
        Prim prim = new Prim(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }
}
