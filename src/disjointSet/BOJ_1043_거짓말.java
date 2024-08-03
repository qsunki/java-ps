package disjointSet;

import java.io.*;
import java.util.*;

public class BOJ_1043_거짓말 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, k;
    static int[] parent;
    static int[] groups;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        parent = new int[n + 1];
        groups = new int[m];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        k = nextInt();
        for (int i = 0; i < k; i++) {
            int x = nextInt();
            union(0, x);
        }
        for (int i = 0; i < m; ++i) {
            int x = nextInt();
            int first = nextInt();
            for (int j = 1; j < x; ++j) {
                int y = nextInt();
                union(first, y);
            }
            groups[i] = first;
        }
        for (int i = 0; i < m; ++i) {
            int group = find(groups[i]);
            if (group != 0) {
                ++ans;
            }
        }
        System.out.println(ans);
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
        if (u < v) {
            parent[v] = u;
        } else if (u > v) {
            parent[u] = v;
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
}
