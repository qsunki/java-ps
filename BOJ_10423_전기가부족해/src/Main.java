import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static List<List<Edge>> graph = new ArrayList<>();
    static List<Edge> mst = new ArrayList<>();
    static int[] plant;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        plant = new int[k];
        for (int i = 0; i < k; i++) {
            plant[i] = nextInt();
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        prim();
        System.out.println(ans);
    }

    static void prim() {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.offer(new Edge(plant[i], 0));
        }
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.v]) {
                continue;
            }
            mst.add(cur);
            visited[cur.v] = true;
            ans += cur.w;
            for (Edge neighbor : graph.get(cur.v)) {
                if (!visited[neighbor.v]) {
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
