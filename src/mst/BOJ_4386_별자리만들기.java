package mst;

import java.io.*;
import java.util.*;

public class BOJ_4386_별자리만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        ConstellationMaker maker = new ConstellationMaker(n);
        for (int i = 0; i < n; i++) {
            double x = nextDouble();
            double y = nextDouble();
            maker.addStar(x, y);
        }
        System.out.println(maker.getMinCost());
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static class ConstellationMaker {
        int n;
        int cnt;
        double[] xs;
        double[] ys;
        List<Edge> edges;
        int[] parents;

        public ConstellationMaker(int n) {
            this.n = n;
            xs = new double[n];
            ys = new double[n];
            edges = new ArrayList<>(n * (n - 1) / 2);
            parents = new int[n];
            for (int i = 1; i < n; i++) {
                parents[i] = i;
            }
        }

        void addStar(double x, double y) {
            int v = cnt;
            for (int u = 0; u < cnt; u++) {
                edges.add(new Edge(u, v, getDistance(x, y, xs[u], ys[u])));
            }
            xs[v] = x;
            ys[v] = y;
            ++cnt;
        }

        double getDistance(double x1, double y1, double x2, double y2) {
            return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        }

        double getMinCost() {
            Collections.sort(edges);
            int edgeCnt = 0;
            double cost = 0;
            for (Edge e : edges) {
                if (find(e.u) == find(e.v)) {
                    continue;
                }
                union(e.u, e.v);
                cost += e.w;
                edgeCnt++;
                if (edgeCnt == n - 1) {
                    break;
                }
            }
            return cost;
        }

        void union(int u, int v) {
            u = find(u);
            v = find(v);
            if (u < v) {
                parents[v] = u;
            } else if (u > v) {
                parents[u] = v;
            }
        }

        int find(int u) {
            if (u == parents[u]) {
                return u;
            }
            return parents[u] = find(parents[u]);
        }

    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;

        Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}
