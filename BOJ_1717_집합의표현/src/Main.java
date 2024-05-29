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
        parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            int cmd = nextInt();
            int a = nextInt();
            int b = nextInt();
            if (cmd == 0) {
                union(a, b);
            } else {
                if (isConnected(a, b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.println(sb);
    }

    static boolean isConnected(int u, int v) {
        return find(u) == find(v);
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

    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }
        return parent[u] = find(parent[u]);
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
