import java.io.*;
import java.util.*;

public class Main {
    static final int UNREACHABLE = 100_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int v, m, ji, su;
    static int[][] graph;
    static List<Integer> candidates = new ArrayList<>();
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        v = nextInt();
        m = nextInt();
        graph = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i != j) {
                    graph[i][j] = UNREACHABLE;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int a = nextInt(), b = nextInt(), c = nextInt();
            if (graph[a][b] > c) {
                graph[a][b] = c;
                graph[b][a] = c;
            }
        }
        ji = nextInt();
        su = nextInt();
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            if (i == ji || i == su) {
                continue;
            }
            if (min == graph[ji][i] + graph[i][su]) {
                candidates.add(i);
            } else if (min > graph[ji][i] + graph[i][su]) {
                min = graph[ji][i] + graph[i][su];
                candidates.clear();
                candidates.add(i);
            }
        }
        for (int v : candidates) {
            if (graph[ji][v] <= graph[su][v]) {
                if (ans == -1) {
                    ans = v;
                } else if (graph[ji][v] < graph[ji][ans]) {
                    ans = v;
                }
            }
        }
        System.out.println(ans);
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
}
