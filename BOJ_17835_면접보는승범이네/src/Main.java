import java.io.*;
import java.util.*;

public class Main {
    static final long MAX = Long.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m, k;
    static List<List<Edge>> graph = new ArrayList<>();
    static long[] distance;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int ans1;
    static long ans2;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        distance = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();
            graph.get(v).add(new Edge(u, w));
        }
        for (int i = 1; i <= n; i++) {
            distance[i] = MAX;
        }
        for (int i = 0; i < k; i++) {
            int v = nextInt();
            distance[v] = 0;
            pq.add(new Edge(v, 0));
        }
        dijkstra();
        for (int i = 1; i <= n; i++) {
            if (distance[i] > ans2) {
                ans1 = i;
                ans2 = distance[i];
            }
        }
        sb.append(ans1).append("\n").append(ans2);
        System.out.println(sb);
    }

    static void dijkstra() {
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.w > distance[cur.v]) {
                continue;
            }
            for (Edge neighbor : graph.get(cur.v)) {
                long newDistance = distance[cur.v] + neighbor.w;
                if (distance[neighbor.v] <= newDistance) {
                    continue;
                }
                distance[neighbor.v] = newDistance;
                pq.offer(new Edge(neighbor.v, newDistance));
            }
        }
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

    static class Edge implements Comparable<Edge> {
        int v;
        long w;

        Edge(int v, long w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(w, o.w);
        }
    }
}
