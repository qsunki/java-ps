import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static Node[] planets;
    static List<Edge> edges;
    static int cost;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        planets = new Node[n];
        edges = new ArrayList<>(3 * n);
        for (int i = 0; i < n; i++) {
            planets[i] = new Node(nextInt(), nextInt(), nextInt(), i + 1);
        }
        Arrays.sort(planets, Comparator.comparingInt(value -> value.x));
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, planets[i + 1].x - planets[i].x));
        }
        Arrays.sort(planets, Comparator.comparingInt(value -> value.y));
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, planets[i + 1].y - planets[i].y));
        }
        Arrays.sort(planets, Comparator.comparingInt(value -> value.z));
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, planets[i + 1].z - planets[i].z));
        }
        kruskal();
        System.out.println(cost);
    }

    static void kruskal() {
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        Collections.sort(edges);
        int cnt = 0;
        for (Edge e : edges) {
            if (find(e.u) == find(e.v)) {
                continue;
            }
            union(e.u, e.v);
            cost += e.w;
            ++cnt;
            if (cnt == n - 1) {
                return;
            }
        }
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u > v) {
            parent[u] = v;
        } else if (u < v) {
            parent[v] = u;
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
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(w, o.w);
        }
    }

    static class Node {
        int x, y, z, idx;

        Node(int x, int y, int z, int idx) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.idx = idx;
        }
    }
}
