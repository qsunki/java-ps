package topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2637_장난감조립 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] inDegree = new int[n + 1];
        int[] cnts = new int[n + 1];
        Map<Integer, Integer> map = new TreeMap<>();
        cnts[n] = 1;
        List<List<Edge>> nodes = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int x = nextInt();
            int y = nextInt();
            int k = nextInt();
            inDegree[y]++;
            nodes.get(x).add(new Edge(y, k));
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (nodes.get(u).isEmpty()) {
                map.put(u, cnts[u]);
                continue;
            }
            for (Edge e : nodes.get(u)) {
                inDegree[e.to]--;
                cnts[e.to] += cnts[u] * e.cnt;
                if (inDegree[e.to] == 0) {
                    q.offer(e.to);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        System.out.println(sb);
    }

    static class Edge {
        int to;
        int cnt;

        Edge(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
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
