import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static char[] type;
    static List<List<Edge>> graph = new ArrayList<>();
    static List<Edge> mst = new ArrayList<>();
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        type = new char[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            type[i] = next().toCharArray()[0];
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            int d = nextInt();
            graph.get(u).add(new Edge(v, d));
            graph.get(v).add(new Edge(u, d));
        }

        prim();
        if (mst.size() != n) {
            System.out.println(-1);
            return;
        }
        for (Edge edge : mst) {
            ans += edge.w;
        }
        System.out.println(ans);
    }

    static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.v]) {
                continue;
            }
            visited[cur.v] = true;
            mst.add(cur);
            for (Edge neighbor : graph.get(cur.v)) {
                if (!visited[neighbor.v] && type[neighbor.v] != type[cur.v]) {
                    pq.offer(neighbor);
                }
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
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }
}
