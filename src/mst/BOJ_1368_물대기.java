package mst;

import java.io.*;
import java.util.*;

public class BOJ_1368_물대기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static List<List<Edge>> graph;
    static int[] singles;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        singles = new int[n];
        int start = 0;
        for (int i = 0; i < n; i++) {
            singles[i] = Integer.parseInt(br.readLine());
            if (singles[i] < singles[start]) {
                start = i;
            }
        }
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                int w = Integer.parseInt(st.nextToken());
                if (i == j) {
                    continue;
                }
                graph.get(i).add(new Edge(j, w));
            }
        }
        int minCost = prim(start);
        System.out.println(minCost);
    }

    static int prim(int start) {
        int cost = singles[start];
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.v]) {
                continue;
            }
            cost += cur.w;
            visited[cur.v] = true;
            for (Edge edge : graph.get(cur.v)) {
                if (!visited[edge.v]) {
                    pq.add(new Edge(edge.v, Math.min(singles[edge.v], edge.w)));
                }
            }
        }
        return cost;
    }

    static class Edge implements Comparable<Edge> {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
