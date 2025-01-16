package mst;

import java.io.*;
import java.util.*;

public class BOJ_16398_행성연결 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static List<Edge> edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        edges = new ArrayList<>(n * (n + 1) / 2);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (j > i) {
                    edges.add(new Edge(i, j, w));
                }
            }
        }

        Collections.sort(edges);

        long minCost = kruskal();
        System.out.println(minCost);
    }

    static long kruskal() {
        int edgeCnt = 0;
        long cost = 0L;
        for (Edge e : edges) {
            int u = find(e.u);
            int v = find(e.v);
            if (u == v) {
                continue;
            }
            union(u, v);
            cost += e.w;
            ++edgeCnt;
            if (edgeCnt == n - 1) {
                break;
            }
        }
        return cost;
    }

    static int find(int v) {
        if (parents[v] == v) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u > v) {
            parents[u] = v;
        } else if (v > u) {
            parents[v] = u;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
