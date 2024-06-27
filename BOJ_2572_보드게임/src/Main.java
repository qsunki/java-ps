import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static char[] cards;
    static List<List<Edge>> graph;
    static int[][] dp;
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        cards = new char[n];
        for (int i = 0; i < n; i++) {
            cards[i] = next().charAt(0);
        }
        m = nextInt();
        k = nextInt();
        graph = new ArrayList<>(m + 1);
        for (int i = 0; i <= m; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < k; i++) {
            int u = nextInt();
            int v = nextInt();
            char color = next().charAt(0);
            graph.get(u).add(new Edge(v, color));
            graph.get(v).add(new Edge(u, color));
        }
        dp = new int[m + 1][n + 1];
        visited = new boolean[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            ans = Math.max(ans, dp(i, n));
        }
        System.out.println(ans);
    }

    static int dp(int v, int idx) {
        if (visited[v][idx]) {
            return dp[v][idx];
        }
        if (idx == 0) {
            visited[v][idx] = true;
            if (v != 1) {
                return dp[v][idx] = -1;
            }
            return 0;
        }
        int max = -1;
        for (Edge edge : graph.get(v)) {
            int tmp = dp(edge.v, idx - 1);
            if (tmp == -1) {
                continue;
            }
            if (edge.color == cards[idx - 1]) {
                tmp += 10;
            }
            max = Math.max(max, tmp);
        }
        visited[v][idx] = true;
        return dp[v][idx] = max;
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
        char color;

        public Edge(int v, char color) {
            this.v = v;
            this.color = color;
        }
    }
}
