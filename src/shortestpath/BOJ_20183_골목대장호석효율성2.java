package shortestpath;

import java.io.*;
import java.util.*;

public class BOJ_20183_골목대장호석효율성2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        int ans = dijkstra(graph, n, a, b, c);
        System.out.println(ans);
    }

    static int dijkstra(List<List<Edge>> graph, int n, int a, int b, long c) {
        int[] minCost = new int[n + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[a] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(a, 0, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > minCost[cur.node]) {
                continue;
            }

            for (Edge edge : graph.get(cur.node)) {
                int newCost = Math.max(edge.cost, minCost[cur.node]);
                if (newCost < minCost[edge.node]) {
                    long sum = cur.sum + edge.cost;
                    if (sum > c) {
                        continue;
                    }
                    minCost[edge.node] = newCost;
                    pq.add(new Edge(edge.node, newCost, sum));
                }
            }

        }
        if (minCost[b] == Integer.MAX_VALUE) {
            return -1;
        }
        return minCost[b];
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int cost;
        long sum;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        Edge(int node, int cost, long sum) {
            this.node = node;
            this.cost = cost;
            this.sum = sum;
        }

        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
