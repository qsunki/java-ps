import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static List<List<Integer>> graph;
    static int[] inDegree;
    static int[] singleCost;
    static int[] totalCost;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        inDegree = new int[n + 1];
        singleCost = new int[n + 1];
        totalCost = new int[n + 1];
        for (int u = 1; u <= n; u++) {
            singleCost[u] = nextInt();
            totalCost[u] = singleCost[u];
            while (true) {
                int v = nextInt();
                if (v == -1) {
                    break;
                }
                graph.get(v).add(u);
                inDegree[u]++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int u = 1; u <= n; u++) {
            if (inDegree[u] == 0) {
                q.add(u);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                totalCost[v] = Math.max(totalCost[v], totalCost[u] + singleCost[v]);
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            sb.append(totalCost[i]).append("\n");
        }
        System.out.println(sb);
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
