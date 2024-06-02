import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static List<Map<Integer, Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();

        for (int i = 0; i <= n; i++) {
            graph.add(new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            graph.get(a).compute(b, (k, v) -> v == null ? c : Math.max(v, c));
            graph.get(b).compute(a, (k, v) -> v == null ? c : Math.max(v, c));
        }
        int start = nextInt();
        int end = nextInt();
        int ans = bfs(start, end);
        System.out.println(ans);
    }

    static int bfs(int start, int end) {
        int[] visited = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = 1_000_000_000;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Map.Entry<Integer, Integer> next : graph.get(u).entrySet()) {
                int min = Math.min(visited[u], next.getValue());
                if (visited[next.getKey()] >= min) {
                    continue;
                }
                visited[next.getKey()] = min;
                q.offer(next.getKey());
            }
        }
        return visited[end];
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
