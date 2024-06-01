import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = nextInt();
        for (int i = 1; i <= t; ++i) {
            int[] total = new int[200_001];
            int[] parent = new int[200_001];
            int n = nextInt();
            int seq = 0;
            Map<String, Integer> nameMap = new HashMap<>();
            for (int j = 0; j < n; ++j) {
                String uStr = next();
                String vStr = next();
                int u, v;
                if (!nameMap.containsKey(uStr)) {
                    nameMap.put(uStr, ++seq);
                    total[seq] = 1;
                    parent[seq] = seq;
                }
                if (!nameMap.containsKey(vStr)) {
                    nameMap.put(vStr, ++seq);
                    total[seq] = 1;
                    parent[seq] = seq;
                }
                u = nameMap.get(uStr);
                v = nameMap.get(vStr);
                if (find(u, parent) != find(v, parent)) {
                    int uTotal = total[find(u, parent)];
                    int vTotal = total[find(v, parent)];
                    union(u, v, parent);
                    total[find(u, parent)] = uTotal + vTotal;
                }
                sb.append(total[find(u, parent)]).append('\n');
            }
        }
        System.out.println(sb);
    }

    static int find(int u, int[] parent) {
        if (u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u], parent);
    }

    static void union(int u, int v, int[] parent) {
        u = find(u, parent);
        v = find(v, parent);
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
