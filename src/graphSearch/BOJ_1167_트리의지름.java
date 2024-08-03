package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ_1167_트리의지름 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int u = nextInt();
            int v = nextInt();
            while (v != -1) {
                int w = nextInt();
                graph.get(u).add(new Edge(v, w));
                graph.get(v).add(new Edge(u, w));
                v = nextInt();
            }
        }
        Edge vertextAndDistance = bfs(1);
        vertextAndDistance = bfs(vertextAndDistance.v);
        System.out.println(vertextAndDistance.w);
    }

    static Edge bfs(int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Edge> q = new ArrayDeque<>();
        q.offer(new Edge(start, 0));
        visited[start] = true;
        int farthestVertex = start;
        int maxDistance = 0;
        while (!q.isEmpty()) {
            Edge cur = q.poll();
            for (Edge edge : graph.get(cur.v)) {
                if (!visited[edge.v]) {
                    q.offer(new Edge(edge.v, cur.w + edge.w));
                    visited[edge.v] = true;
                }
            }
            if (cur.w > maxDistance) {
                maxDistance = cur.w;
                farthestVertex = cur.v;
            }
        }
        return new Edge(farthestVertex, maxDistance);
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
