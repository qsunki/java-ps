package mst;

import java.io.*;
import java.util.*;

public class BOJ_13418_학교탐방하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<Edge> graph;
    static int n;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(m);
        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new Edge(u, v, w));
        }
        Collections.sort(graph);
        int min = kruskal();
        Collections.reverse(graph);
        int max = kruskal();
        System.out.println(max - min);
    }

    static int kruskal() {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        int nodeCnt = 1;
        int weight = 0;
        for (Edge e : graph) {
            int u = find(e.u);
            int v = find(e.v);
            if (u == v) {
                continue;
            }
            union(e.u, e.v);
            weight += e.w ^ 1;
            ++nodeCnt;
            if (nodeCnt == n + 1) {
                break;
            }
        }
        return weight * weight;
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
        } else if (u < v) {
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
            return Integer.compare(o.w, this.w);
        }
    }
}
