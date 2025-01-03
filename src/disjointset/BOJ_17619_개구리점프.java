package disjointset;

import java.io.*;
import java.util.*;

public class BOJ_17619_개구리점프 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, q;
    static Log[] logs;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        q = nextInt();
        logs = new Log[n];
        parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int x1 = nextInt();
            int x2 = nextInt();
            int y = nextInt();
            logs[i] = new Log(x1, x2, i + 1);
        }
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        Arrays.sort(logs);
        int end = logs[0].x2;
        for (int i = 1; i < n; i++) {
            if (logs[i].x1 > end) {
                end = logs[i].x2;
                continue;
            }
            union(logs[i].num, logs[i - 1].num);
            end = Math.max(end, logs[i].x2);
        }
        for (int i = 0; i < q; i++) {
            int log1 = nextInt();
            int log2 = nextInt();
            if (find(log1) == find(log2)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int find(int u) {
        if (u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u]);
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

    static class Log implements Comparable<Log> {
        int x1;
        int x2;
        int num;

        Log(int x1, int x2, int num) {
            this.x1 = x1;
            this.x2 = x2;
            this.num = num;
        }

        @Override
        public int compareTo(Log o) {
            return Integer.compare(x1, o.x1);
        }
    }
}
