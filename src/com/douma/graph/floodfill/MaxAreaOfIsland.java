package com.douma.graph.floodfill;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class MaxAreaOfIsland {
    private int rows;
    private int cols;

    private int[][] grid;

    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        // 1. 建图
        graph = constructGraph();

        // 2. 求解最大联通分量的顶点数
        this.visited = new boolean[graph.length];
        int res = 0;
        for (int v = 0; v < graph.length; v++) {
            int row = v / cols;
            int col = v % cols;
            if (!visited[v] && grid[row][col] == 1) {
                res = Math.max(dfs(v), res);
            }
        }
        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : graph[v]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    private Set<Integer>[] constructGraph() {
        Set<Integer>[] g = new HashSet[rows * cols];
        for (int v = 0; v < g.length; v++) {
            g[v] = new HashSet<>();
        }

        for (int v = 0; v < g.length; v++) {
            int row = v / cols;
            int col = v % cols;
            if (grid[row][col] == 1) {
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (inArea(nextRow, nextCol)
                            && grid[nextRow][nextCol] == 1) {
                        int w = nextRow * cols + nextCol;
                        g[v].add(w);
                        g[w].add(v);
                    }
                }
            }
        }
        return g;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,1,1,1,0,0,0},
                {0,1,0,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0},
        };
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
