import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        for (int i = 1; i <= m; ++i) {
            int u = nextInt();
            int v = nextInt();
            if (find(u) == find(v)) {
                System.out.println(i);
                return;
            }
            union(u, v);
        }
        System.out.println(0);
    }

    static int find(int u) {
        if (parent[u] == u) {
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
